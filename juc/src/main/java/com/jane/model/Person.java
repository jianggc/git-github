package com.jane.model;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by Janus on 2018/9/18.
 */
public class Person implements InitializingBean,DisposableBean {
    private String name;
    private  Integer age=1;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("Person(String name, Integer age) constructor"+this);
    }
    public Person() {
        super();
        System.out.println("Person() constructor"+age);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void init(){
        System.out.println("-----Person.init()-----"+this);
    }
    public void cleanUp(){
        System.out.println("-----Person.cleanUp()-----"+this);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("-----InitializingBean.afterPropertiesSet()-----"+this);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("-----DisposableBean.destroy()-----"+this);
    }

    //对象创建并赋值之后调用
    @PostConstruct
    public void init2(){
        System.out.println("-----@PostConstruct-----"+this);
    }

    //容器移除对象之前
    @PreDestroy
    public void destory2(){
        System.out.println("-----@PreDestroy-----"+this);
    }
}
