package br.com.tdc.bpmn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tdc.bpmn.domain.Function;

@Repository
public interface FunctionRepository extends CrudRepository<Function, Long> {

	List<Function> findAll();

}
