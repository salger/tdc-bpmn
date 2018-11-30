package br.com.tdc.bpmn.domain;

public class Item {

    private Integer id;
    private String description;

    public Item() {
        super();
    }

    public Item(Integer id, String description) {
        super();
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", description=" + description + "]";
    }

}
