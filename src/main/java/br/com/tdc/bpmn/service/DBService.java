package br.com.tdc.bpmn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tdc.bpmn.domain.Employee;
import br.com.tdc.bpmn.domain.Function;
import br.com.tdc.bpmn.domain.activiti.GeByteArray;
import br.com.tdc.bpmn.repository.EmployeeRepository;
import br.com.tdc.bpmn.repository.FunctionRepository;
import br.com.tdc.bpmn.repository.activiti.GeByteArrayRepository;
import br.com.tdc.bpmn.service.exception.ServiceException;

@Service
@Transactional(readOnly = true, transactionManager = "database")
public class DBService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private FunctionRepository functionRepository;

	@Autowired
	private GeByteArrayRepository geByteArrayRepository;

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public List<Function> getFunctions() {
		return functionRepository.findAll();
	}

	public List<GeByteArray> geByteArrays() {
		return geByteArrayRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, transactionManager = "database")
	public Function saveFunction(Long id, String description) throws ServiceException {

		Function function = new Function(id);
		function.setDescription(description);
		function = functionRepository.save(function);

		return function;
	}

}
