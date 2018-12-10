package br.com.tdc.bpmn.service.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloTask implements JavaDelegate {

	private Logger log = LoggerFactory.getLogger(HelloTask.class);

	@Override
	public void execute(DelegateExecution execution) {

		log.info("Iniciando task HelloTask. Variáveis presentes: " + execution.getVariables().toString());

		execution.setVariable("hello", "Hello World!");
		execution.setVariable("name", "Rodrigo");

		String time = (String) execution.getVariable("time");
		if (!"DAY".equals(time) && !"NIGHT".equals(time)) {
			execution.setVariable("time", "DAY");
		}

		log.info("Finalizando task HelloTask. Variáveis presentes: " + execution.getVariables().toString());

	}

}
