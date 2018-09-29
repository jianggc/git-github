package com.jane.controller;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Janus on 2018/9/13.
 */
public class TestAtomicDemo {

    public static void main(String[] args){

        AtomicDemo ad = new AtomicDemo();
        for (int i=0;i<10;i++){
            new  Thread(ad).start();
        }
    }
}

class AtomicDemo implements  Runnable{

//    private volatile int serialNum = 0;

    private AtomicInteger ai=new AtomicInteger();

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" : "+getSerialNum());

    }

    public int getSerialNum() {
//        return serialNum++;
        return ai.getAndIncrement();
    }
}