package br.com.tdc.bpmn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdc.bpmn.domain.Order;
import br.com.tdc.bpmn.dto.OrderDTO;
import br.com.tdc.bpmn.misc.EntityFactory;
import br.com.tdc.bpmn.service.ModelMapperService;

@RestController
@RequestMapping("/modelmapper")
public class ModelMapperController {

	@Autowired
	private ModelMapperService modelMapperService;

	@RequestMapping("/test1")
	public OrderDTO test1() {
		Order order = EntityFactory.getOrder();
		OrderDTO result = modelMapperService.getOrderDTO(order);
		return result;
	}

}
