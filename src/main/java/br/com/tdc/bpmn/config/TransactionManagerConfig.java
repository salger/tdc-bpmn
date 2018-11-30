package br.com.tdc.bpmn.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfig {

	@Autowired
	@Qualifier("database")
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	@Qualifier("activiti")
	private EntityManagerFactory activitiEntityManagerFactory;

	@Bean
	@Primary
	@Qualifier("database")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	@Qualifier("activiti")
	public PlatformTransactionManager activitiTransactionManager() {
		return new JpaTransactionManager(activitiEntityManagerFactory);
	}

}
