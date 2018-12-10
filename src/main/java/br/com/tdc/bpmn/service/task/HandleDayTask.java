package br.com.tdc.bpmn.service.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleDayTask implements JavaDelegate {

	private Logger log = LoggerFactory.getLogger(HandleDayTask.class);

	@Override
	public void execute(DelegateExecution execution) {

		log.info("Iniciando task HandleDayTask. Variáveis presentes: " + execution.getVariables().toString());

		log.info("Finalizando task HandleDayTask. Variáveis presentes: " + execution.getVariables().toString());

	}

}
