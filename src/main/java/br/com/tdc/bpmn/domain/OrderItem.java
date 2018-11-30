package br.com.tdc.bpmn.domain;

import java.math.BigDecimal;

public class OrderItem {

    private Item item;
    private BigDecimal ammount;

    public OrderItem() {
        super();
    }

    public OrderItem(Item item, BigDecimal ammount) {
        super();
        this.item = item;
        this.ammount = ammount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }

    @Override
    public String toString() {
        return "OrderItem [item=" + item + ", ammount=" + ammount + "]";
    }

}
