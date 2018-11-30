package br.com.tdc.bpmn.mapper;

import org.modelmapper.PropertyMap;

import br.com.tdc.bpmn.domain.Order;
import br.com.tdc.bpmn.domain.OrderItem;
import br.com.tdc.bpmn.dto.OrderDTO;
import br.com.tdc.bpmn.dto.OrderItemDTO;

public class OrderMapper {

    public static PropertyMap<Order, OrderDTO> orderDTOMapper() {

        PropertyMap<Order, OrderDTO> orderMap = new PropertyMap<Order, OrderDTO>() {
            protected void configure() {
                map().setCustomerFirstName(source.getCustomer().getName().getFirstName());
                map().setCustomerLastName(source.getCustomer().getName().getLastName());
                map().setCity(source.getAddress().getCity().getName());
                map().setStreet(source.getAddress().getStreet());
                map().setStreetNumber(source.getAddress().getNumber());
            }
        };

        return orderMap;
    }

    public static PropertyMap<OrderItem, OrderItemDTO> orderItemMapper() {

        PropertyMap<OrderItem, OrderItemDTO> orderMap = new PropertyMap<OrderItem, OrderItemDTO>() {
            protected void configure() {
                map().setDiscrimination(source.getItem().getDescription());
                map().setCount(source.getAmmount());
            }
        };

        return orderMap;
    }

}
