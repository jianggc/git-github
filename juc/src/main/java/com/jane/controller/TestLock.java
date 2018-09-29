package com.jane.controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Janus on 2018/9/16.
 */
public class TestLock {
    public static void main(String[] args){

        Ticket ticket = new Ticket();
        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();

    }
}
class Ticket implements  Runnable{

    private volatile int ticket = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true){
            try {
                lock.lock();
                //放大多线程问题
                Thread.sleep(200);
                if(ticket>0){
                    System.out.println(Thread.currentThread().getName()+" 完成售票，余票为："+--ticket);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //保证锁的释放
                lock.unlock();
            }
        }

    }
}
