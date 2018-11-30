package br.com.tdc.bpmn.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.tdc.bpmn.domain.Order;
import br.com.tdc.bpmn.dto.OrderDTO;
import br.com.tdc.bpmn.mapper.OrderMapper;

@Service
public class ModelMapperService {

    public OrderDTO getOrderDTO(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(OrderMapper.orderDTOMapper());
        modelMapper.addMappings(OrderMapper.orderItemMapper());

        OrderDTO result = modelMapper.map(order, OrderDTO.class);
        return result;
    }

}
