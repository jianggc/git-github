package com.jane.controller;

/**
 * Created by Janus on 2018/9/16.
 */
public class TestProductConsumer {
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
    //进货
    public  synchronized void product(){
        while(product>=1){
            System.out.println(Thread.currentThread().getName()+"产品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        else{
            System.out.println(Thread.currentThread().getName()+" :当前有 "+(++product));
            this.notifyAll();
//        }
    }
    //卖货
    public synchronized void sale(){
        while(product<=0){
            System.out.println(Thread.currentThread().getName()+"缺货！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        else{
            System.out.println(Thread.currentThread().getName()+" :余剩有 "+(--product));
            this.notifyAll();
//        }
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