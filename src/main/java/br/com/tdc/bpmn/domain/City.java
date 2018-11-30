package br.com.tdc.bpmn.domain;

public class City {
    private String name;
    private String uf;

    public City() {
    }

    public City(String name, String uf) {
        super();
        this.name = name;
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "City [name=" + name + ", uf=" + uf + "]";
    }

}
