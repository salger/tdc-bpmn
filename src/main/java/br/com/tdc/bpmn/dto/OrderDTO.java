package br.com.tdc.bpmn.dto;

import java.util.List;

public class OrderDTO {

    private String customerFirstName;
    private String customerLastName;
    private String city;
    private String street;
    private Integer streetNumber;
    private List<OrderItemDTO> items;

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderDTO [customerFirstName=" + customerFirstName + ", customerLastName=" + customerLastName + ", city="
                + city + ", street=" + street + ", streetNumber=" + streetNumber + ", items=" + items + "]";
    }

}
