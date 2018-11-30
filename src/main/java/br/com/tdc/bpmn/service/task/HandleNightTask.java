package br.com.tdc.bpmn.service.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleNightTask implements JavaDelegate {

	private Logger log = LoggerFactory.getLogger(HandleNightTask.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		log.info("Iniciando task HandleNightTask. Variáveis presentes: " + execution.getVariables().toString());

		log.info("Finalizando task HandleNightTask. Variáveis presentes: " + execution.getVariables().toString());

	}

}
