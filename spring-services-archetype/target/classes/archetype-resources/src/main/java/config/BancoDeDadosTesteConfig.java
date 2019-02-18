#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import oracle.jdbc.pool.OracleDataSource;


@Configuration
public class BancoDeDadosTesteConfig {

	@Bean
	@Profile("test")
	public DataSource dataSource() throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setUser("gestoc");
		dataSource.setPassword("gestoc01");
		dataSource.setURL("jdbc:oracle:thin:@10.21.1.1:1521:treino");
		return dataSource;
	}

	// configure entityManagerFactory
	// configure transactionManager
	// configure additional Hibernate properties
}