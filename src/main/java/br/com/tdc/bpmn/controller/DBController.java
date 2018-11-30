package br.com.tdc.bpmn.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdc.bpmn.domain.Employee;
import br.com.tdc.bpmn.domain.Function;
import br.com.tdc.bpmn.domain.activiti.GeByteArray;
import br.com.tdc.bpmn.service.DBService;
import br.com.tdc.bpmn.service.exception.ServiceException;

@RestController
@RequestMapping("/db")
public class DBController {

	@Autowired
	private DBService dbService;

	@RequestMapping("/employee")
	public List<Employee> employee() {
		return dbService.getEmployees();
	}

	@RequestMapping("/function")
	public List<Function> function() {
		return dbService.getFunctions();
	}

	@RequestMapping("/geByteArray")
	public List<GeByteArray> geByteArray() {
		return dbService.geByteArrays();
	}

	@RequestMapping("/newFunction")
	public Function newFunction(@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "description", required = false) String description, HttpServletResponse response) {
		try {
			return dbService.saveFunction(id, description);
		} catch (ServiceException e) {
			return null;
		}
	}

}
