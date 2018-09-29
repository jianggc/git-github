package com.jane.controller;

/**
 * Created by Janus on 2018/9/16.
 */
public class TestRunnable {

    public static void main(String[] args){

        RunnableDemo rd = new RunnableDemo();
        new Thread(rd).start();
        new Thread(rd).start();
    }
}

class  RunnableDemo implements  Runnable{

    private static int num = 0;

    private Object object = new Object();

    @Override
    public void run() {
        while (true){
            synchronized (object){
                this.notify();
                try {
                    Thread.currentThread().sleep(100);
                    if (num<=100){
                        System.out.println(Thread.currentThread().getName() + ":"+ num);
                        num++;
                        this.wait();
                    }else{
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
