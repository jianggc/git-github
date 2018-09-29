package com.jane.controller;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Janus on 2018/9/16.
 */
public class TestCountDownLatch {

    public static void main(String[] args){

        final CountDownLatch latch = new CountDownLatch(5);

        LatchDemo latchDemo = new LatchDemo(latch);

        long begin = System.currentTimeMillis();

        for (int i = 0; i <5 ; i++) {
            new Thread(latchDemo).start();
        }
        try {
            //多线程运行结束前一直等待
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        
        System.out.println("耗费时间："+(end-begin));

    }
}

class LatchDemo implements  Runnable{

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch){
        this.latch=latch;
    }
    public LatchDemo(){
        super();
    }

    @Override
    public void run() {
        //当前对象唯一，使用当前对象加锁，避免多线程问题
        synchronized (this){
            try {
                for (int i = 0; i < 50000; i++) {
                    if (i%2==0){
                        System.out.println(i);
                    }
                }
            }finally {
                //保证肯定执行
                latch.countDown();
            }
        }
    }
}