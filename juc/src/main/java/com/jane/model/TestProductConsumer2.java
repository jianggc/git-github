package com.jane.model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Janus on 2018/9/17.
 */
public class TestProductConsumer2 {
    public static void main(String[] args){

        Clerk clerk = new Clerk();
        Product product = new Product(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(product,"生产者A").start();
        new Thread(consumer,"消费者B").start();
        new Thread(product,"生产者C").start();
        new Thread(consumer,"消费者D").start();

    }
}

//店员
class Clerk{

    private int product=0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //进货
    public  void product(){
        lock.lock();
        try {
            while(product>=1){
                System.out.println(Thread.currentThread().getName()+"产品已满");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" :当前有 "+(++product));
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }
    //卖货
    public void sale(){
        lock.lock();
        try{
            while(product<=0){
                System.out.println(Thread.currentThread().getName()+"缺货！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" :余剩有 "+(--product));
            condition.signalAll();
        }finally{
            lock.unlock();
        }

    }
}

class Product implements  Runnable{

    private Clerk clerk;

    public Product(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for(int i = 0;i<20;i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.product();
        }
    }
}

class Consumer implements  Runnable{

    private Clerk clerk;

    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for(int i = 0;i<20;i++){
            clerk.sale();
        }
    }
}
