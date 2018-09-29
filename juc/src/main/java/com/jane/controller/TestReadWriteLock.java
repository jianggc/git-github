package com.jane.controller;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Janus on 2018/9/20.
 */
public class TestReadWriteLock {

    public static void main(String[] args){
        ReadWriteLockDemo rwd = new ReadWriteLockDemo();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rwd.get();
                }
            }).start();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                rwd.set((int)(Math.random()*101));
            }
        },"Write").start();
    }
}

class ReadWriteLockDemo{
    private int number = 0;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //读
    public void get(){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" : "+number);
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
    //写
    public void set(int number){
        readWriteLock.writeLock().lock();
        try {
            this.number = number;
            System.out.println(Thread.currentThread().getName()+" : "+number);
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
