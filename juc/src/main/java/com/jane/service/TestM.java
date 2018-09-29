package com.jane.service;

/**
 * Created by Janus on 2018/9/17.
 */
public class TestM {
    int count  =9;
    public void count1(){
        int count =10;
        System.out.println("count1 ="+count);
    }
    public void count2(){
        System.out.println("count2 ="+count);
    }
    public static void main(String[] args){
        TestM testM = new TestM();
        testM.count1();
        testM.count2();

    }
}
