package msdingfield.estimationcalibrator.estimator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import msdingfield.estimationcalibrator.language.Language;
import msdingfield.estimationcalibrator.language.Languages;

import org.springframework.jdbc.core.RowMapper;

public class EstimatorRowMapper implements RowMapper<Estimator>{

	@Override
	public Estimator mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Estimator estimator = new Estimator();
		estimator.setUsername(rs.getString("username"));
		estimator.setNextQuestionId(rs.getLong("next_question_id"));
		estimator.setLanguage(Languages.languageForIsoCode(rs.getString("language")));
		return estimator;
	}

}
