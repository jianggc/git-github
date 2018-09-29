package com.jane.model;

/**
 * Created by Janus on 2018/9/12.
 */
public class Child extends  Parent {

    private Integer id;
    private String name;

    public Child(Integer id, Integer age) {
        super(id, age);
    }

    public Child(Integer id, Integer age, String name) {
        super(id, age);
        this.id = id;
        this.name = name;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
