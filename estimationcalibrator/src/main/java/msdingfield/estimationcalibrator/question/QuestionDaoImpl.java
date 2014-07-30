package msdingfield.estimationcalibrator.question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import msdingfield.estimationcalibrator.dal.DaoConfig;
import msdingfield.estimationcalibrator.language.Languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class QuestionDaoImpl implements QuestionDao {

	@Autowired
	private DaoConfig config;
	
	@Override
	public List<Question> getQuestionsForOwner(String username) {
		final String sql = ""
				+ "select * from questions where username = :username";
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("username", username);
		final List<Question> questions = config.jdbc().query(sql, params, new RowMapper<Question>(){
	
			@Override
			public Question mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				final Question question = new Question();
				question.setExactAnswer(rs.getDouble("answer"));
				question.setLanguage(Languages.languageForIsoCode(rs.getString("language")));
				question.setOwnerUsername(rs.getString("username"));
				question.setProse(rs.getString("prose"));
				question.setQuestionId(rs.getString("question_id"));
				return question;
			}});
		return questions;
	}

	@Override
	public Question save(Question question) {
		if (question.getQuestionId() == null) {
			final Question newQuestion = new Question(question);
			newQuestion.setQuestionId(UUID.randomUUID().toString());
			final String sql = ""
					+ "insert into questions(question_id, prose, answer, language, username) values (:questionId, :prose, :answer, :language, :username)";
			config.jdbc().update(sql, new QuestionSqlParameterSource(newQuestion));
			return newQuestion;
		} else {
			final String sql = ""
					+ "update questions "
					+ "set prose = :prose, answer = :answer, language = :language "
					+ "where question_id = :questionId";
			config.jdbc().update(sql, new QuestionSqlParameterSource(question));
			return question;
		}
	}

	public static final class QuestionSqlParameterSource implements
			SqlParameterSource {
		private final Question newQuestion;
		final String[] keys = new String[] {"answer", "language", "prose", "questionId", "username"};

		private QuestionSqlParameterSource(Question newQuestion) {
			this.newQuestion = newQuestion;
		}

		@Override
		public boolean hasValue(String paramName) {
			int idx = Arrays.binarySearch(keys, 0, keys.length, paramName);
			return idx >= 0;
		}

		@Override
		public Object getValue(String paramName)
				throws IllegalArgumentException {
			switch(paramName) {
			case "answer": return newQuestion.getExactAnswer();
			case "language": return newQuestion.getLanguage().getIsoCode();
			case "prose": return newQuestion.getProse();
			case "questionId": return newQuestion.getQuestionId();
			case "username": return newQuestion.getOwnerUsername();
			}
			throw new IllegalArgumentException();
		}

		@Override
		public int getSqlType(String paramName) {
			switch(paramName) {
			case "answer": return java.sql.Types.NUMERIC;
			case "language": return java.sql.Types.VARCHAR;
			case "prose": return java.sql.Types.VARCHAR;
			case "questionId": return java.sql.Types.VARCHAR;
			case "username": return java.sql.Types.VARCHAR;
			}
			return 0;
		}

		@Override
		public String getTypeName(String paramName) {
			return null;
		}
	}

	@Override
	public void remove(String questionId) {
		final String sql = ""
				+ "delete from questions where question_id = :questionId";
		final SqlParameterSource params = new MapSqlParameterSource()
			.addValue("questionId", questionId);
		config.jdbc().update(sql, params);
	}

}
