package msdingfield.estimationcalibrator.estimator;

import msdingfield.estimationcalibrator.dal.DaoConfig;
import msdingfield.estimationcalibrator.language.Language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class EstimatorDaoImpl implements EstimatorDao {
	
	@Autowired
	private DaoConfig config;
	
	@Override
	public void createEstimator(Estimator estimator) {
		String sql = ""
				+ "insert into estimators (username, language, next_question_id) values (:username, :language, :nextQuestionId)";
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("username", estimator.getUsername())
			.addValue("language", estimator.getLanguage().getIsoCode())
			.addValue("nextQuestionId", estimator.getNextQuestionId());
		config.jdbc().update(sql , params);
	}



	@Override
	public Estimator loadEstimatorByUsername(final String name) {
		String sql = ""
				+ "select username, language, next_question_id as nextQuestionId from estimators where username = :username";
		SqlParameterSource namedParameters = new MapSqlParameterSource("username", name);
		try {
			Estimator estimator = config.jdbc().queryForObject(sql, namedParameters, new EstimatorRowMapper());
			return estimator;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	

	@Override
	public void setLanguage(String username, Language language) {
		String sql = "" +
			"update estimators set language = :language where username = :username";
		SqlParameterSource paramSource = new MapSqlParameterSource()
			.addValue("username", username)
			.addValue("language", language.getIsoCode());
		config.jdbc().update(sql , paramSource);
	}

}
