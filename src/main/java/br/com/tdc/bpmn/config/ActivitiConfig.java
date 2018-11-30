package br.com.tdc.bpmn.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ActivitiConfig {

    private static final Logger log = LoggerFactory.getLogger(ActivitiConfig.class);

    private static final String[] BPM_FILE_PATHS = { "classpath:/processes/" };
    private static final String[] BPM_FILE_PATTERNS = { "**.bpmn20.xml", "**.bpmn" };

    @Autowired
    private ResourcePatternResolver resourceLoader;

    @Autowired
    @Qualifier("activiti")
    private DataSource activitiDataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration() throws IOException {
        SpringProcessEngineConfiguration result = new SpringProcessEngineConfiguration();
        result.setDataSource(activitiDataSource);
        result.setDatabaseSchemaUpdate("true");
        result.setTransactionManager(transactionManager);
        result.setJobExecutorActivate(false);
        List<Resource> lResources = getProcessResources();
        Resource[] resources = lResources.toArray(new Resource[lResources.size()]);
        result.setDeploymentResources(resources);

        return result;
    }

    private List<Resource> getProcessResources() throws IOException {
        List<Resource> result = new ArrayList<Resource>();
        for (String bpmFilePath : BPM_FILE_PATHS) {
            for (String bpmFilePattern : BPM_FILE_PATTERNS) {
                String path = bpmFilePath + bpmFilePattern;
                log.info("Tentando localizar BPM´s para deployment no caminho [" + path + "]");
                Resource[] resources = this.resourceLoader.getResources(path);
                if (resources != null && resources.length > 0) {
                    for (Resource resource : resources) {
                        log.info("Localizado o arquivo BPM [" + resource.getFile().getAbsolutePath()
                                + "] para deployment");
                        result.add(resource);
                    }
                }
            }
        }

        if (result.isEmpty()) {
            log.info(String.format("Nenhuma definicao de processo encontrada para deployment"));
        }

        return result;
    }

}
