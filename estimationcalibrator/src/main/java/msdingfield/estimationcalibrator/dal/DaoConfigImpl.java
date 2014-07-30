package msdingfield.estimationcalibrator.dal;

import java.sql.Connection;

import javax.sql.DataSource;
import javax.validation.Validator;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DaoConfigImpl implements JdbcConfig, DaoConfig {
	
	@Autowired
	private Validator validator;

	@Override
	@Bean
	public DataSource dataSource() {
		try {
			DataSource ds = dbcp();
			try (Connection conn = ds.getConnection()) {
				initDataSource().populate(conn);
			} catch (Exception e) {
	
			}
			return ds;
		} catch (Exception e) {
			throw new JdbcInitializationException("Unable to initialize JDBC DataSource pool.", e);
		}
	}

	public ResourceDatabasePopulator initDataSource() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(true);
		populator.addScript(new ClassPathResource("schema.sql"));
		return populator;
	}

	public PoolingDataSource<PoolableConnection> dbcp()
			throws ClassNotFoundException {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				"jdbc:hsqldb:estimation", null);
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, null);
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(
				poolableConnectionFactory);
		poolableConnectionFactory.setPool(connectionPool);
		PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(
				connectionPool);
		return dataSource;
	}

	@Override
	@Bean
	public NamedParameterJdbcTemplate jdbc() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	@Override
	@Bean
	public PlatformTransactionManager txMgr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Validator validator() {
		return validator;
	}
}
