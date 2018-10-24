package com.jane.model;

public class Worker {
	
	private Integer id;
	private Integer age;
	private String name;
	
	private Pay pay;
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

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

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return "Worker [id=" + id + ", age=" + age + ", name=" + name + ", pay=" + pay + "]";
	}
	
}
