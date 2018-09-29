package com.jane.controller;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Janus on 2018/9/17.
 */
public class TestSwapThread2 {

    public static void main(String[] args){
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        SwapThread2 swapThread = new SwapThread2(lock,conditionA,conditionB);
        SwapThread2 swapThread2 = new SwapThread2(lock,conditionB,conditionC);
        SwapThread2 swapThread3 = new SwapThread2(lock,conditionC,conditionA);

        swapThread.setName("A");
        swapThread.start();

        swapThread2.setName("B");
        swapThread2.start();

        swapThread3.setName("C");
        swapThread3.start();

    }
}

class SwapThread2 extends Thread{

    private Lock lock ;
    private Condition condition1;
    private Condition condition2 ;

    public SwapThread2(Lock lock,Condition condition1,Condition condition2){
        this.lock = lock;
        this.condition1=condition1;
        this.condition2=condition2;
    }


    @Override
    public void run() {
        lock.lock();
        try {
            int count =0;
            while (count<10){
                condition2.signal();
                System.out.println(Thread.currentThread().getName()+" : "+count );
                count++;
                if (count==10){
                    condition1.signalAll();
                }else{
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }finally {
            lock.unlock();
        }
    }
}
