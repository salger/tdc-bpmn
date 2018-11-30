package br.com.tdc.bpmn.dto;

import java.math.BigDecimal;

public class OrderItemDTO {

    private String discrimination;
    private BigDecimal count;

    public String getDiscrimination() {
        return discrimination;
    }

    public void setDiscrimination(String discrimination) {
        this.discrimination = discrimination;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderItemDTO [description=" + discrimination + ", ammount=" + count + "]";
    }

}
