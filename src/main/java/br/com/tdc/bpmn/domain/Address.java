package br.com.tdc.bpmn.domain;

public class Address {

    private City city;
    private String street;
    private Integer number;

    public Address() {
        super();
    }

    public Address(City city, String street, Integer number) {
        super();
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ", street=" + street + ", number=" + number + "]";
    }

}
