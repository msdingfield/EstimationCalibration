package msdingfield.estimationcalibrator.dal;

import java.sql.SQLException;

import javax.sql.DataSource;

public interface JdbcConfig {

	DataSource dataSource() throws ClassNotFoundException,
			SQLException;

}