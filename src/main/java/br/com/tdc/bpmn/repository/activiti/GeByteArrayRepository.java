package br.com.tdc.bpmn.repository.activiti;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tdc.bpmn.domain.activiti.GeByteArray;

@Repository
public interface GeByteArrayRepository extends CrudRepository<GeByteArray, String> {

	List<GeByteArray> findAll();

}
