package com.jane.controller;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Janus on 2018/9/17.
 */
public class TestSwapThread3 {
    public static void main(String[] args){
        Lock lock = new ReentrantLock();
        SwapThread3 swapThread3 = new SwapThread3();
        new Thread(swapThread3,"A").start();

        new Thread(swapThread3,"B").start();
        new Thread(swapThread3,"C").start();

    }
}

class SwapThread3 implements Runnable{

    private Lock lock =new ReentrantLock() ;
    private int number =1;

    @Override
    public void run() {
        int count =0;
        while (count<10){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" : "+count );
                count++;
            }finally {
                lock.unlock();
            }
        }
    }
}
