package com.skk.emp.bean;

public class Position {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
