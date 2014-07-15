package msdingfield.estimationcalibrator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class JdbcConfig {

	@Bean
	public DataSource dataSource() throws ClassNotFoundException, SQLException {
		DataSource ds = dbcp();
		try (Connection conn = ds.getConnection())
		{
			initDataSource().populate(conn);
		} catch (Exception e) {
			
		}
		return ds;
	}
	
	public ResourceDatabasePopulator initDataSource() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("schema.sql"));
		return populator;
	}
	
	public PoolingDataSource<PoolableConnection> dbcp() throws ClassNotFoundException {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:hsqldb:estimation",null);
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
		poolableConnectionFactory.setPool(connectionPool);
		PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);
		return dataSource;
	}
}
