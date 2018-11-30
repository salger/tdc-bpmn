package br.com.tdc.bpmn.domain;

public class Customer {

    private Integer reference;
    private Name name;

    public Customer() {
        super();
    }

    public Customer(Integer reference, Name name) {
        super();
        this.reference = reference;
        this.name = name;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer [reference=" + reference + ", name=" + name + "]";
    }

}
