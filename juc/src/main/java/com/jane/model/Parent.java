package com.jane.model;

/**
 * Created by Janus on 2018/9/12.
 */
public class Parent {

    private Integer id;
    private Integer age;

    public Parent(){
        super();
    }

    public  Parent(Integer id,Integer age){
        this.id=id;
        this.age=age;
    }

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
    
    private void method(){
        System.out.println("Parent method...");
    }

    private class childP{
        int expectedModCount=1;

        public int getExpectedModCount() {
            return expectedModCount;
        }

        public void setExpectedModCount(int expectedModCount) {
            this.expectedModCount = expectedModCount;
        }
    }

    public childP getChildP(){
        return new childP();
    }

    public int add(int i){
        childP childP = getChildP();
        childP.expectedModCount++;
        return i;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}
