package br.com.tdc.bpmn.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class DatabaseConfig {

	private static final String ENTITY_PACKAGE = "br.com.tdc.bpmn.domain";
	private static final String ACTIVITI_ENTITY_PACKAGE = "br.com.tdc.bpmn.domain.activiti";

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	@Qualifier("database")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.activityDatasource")
	@Qualifier("activiti")
	public DataSource activitiDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	@Qualifier("database")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(primaryDataSource()).packages(ENTITY_PACKAGE).persistenceUnit("database")
				.build();
	}

	@Bean
	@Qualifier("activiti")
	public LocalContainerEntityManagerFactoryBean activitiEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(activitiDataSource()).packages(ACTIVITI_ENTITY_PACKAGE).persistenceUnit("activiti")
				.build();
	}

}
