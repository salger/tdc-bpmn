package br.com.tdc.bpmn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.tdc.bpmn.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class RepositoriesConfig {

}
