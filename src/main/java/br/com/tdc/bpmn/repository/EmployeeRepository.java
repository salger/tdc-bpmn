package br.com.tdc.bpmn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tdc.bpmn.domain.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findAll();

}
