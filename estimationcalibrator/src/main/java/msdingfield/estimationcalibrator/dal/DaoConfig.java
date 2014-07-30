package msdingfield.estimationcalibrator.dal;

import javax.validation.Validator;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

public interface DaoConfig {
	NamedParameterJdbcTemplate jdbc();
	PlatformTransactionManager txMgr();
	Validator validator();
}
