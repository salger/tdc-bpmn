package br.com.tdc.bpmn.domain;

import java.util.List;

public class Order {

    private Customer customer;
    private Address address;
    private List<OrderItem> items;

    public Order() {
        super();
    }

    public Order(Customer customer, Address address, List<OrderItem> items) {
        super();
        this.customer = customer;
        this.address = address;
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order [customer=" + customer + ", address=" + address + "]";
    }

}
