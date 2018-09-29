package com.jane.controller;

/**
 * Created by Janus on 2018/9/17.
 */
public class TestSwapThread {
    public static void main(String[] args){

        final Object obj1 = new Object();
        final Object obj2 = new Object();
        final Object obj3 = new Object();
        SwapThread swapThread = new SwapThread(obj1,obj2);
        SwapThread swapThread2 = new SwapThread(obj3,obj1);
        SwapThread swapThread3 = new SwapThread(obj2,obj3);
        swapThread.setName("A");
        swapThread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        swapThread2.setName("B");
        swapThread2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        swapThread3.setName("C");
        swapThread3.start();
    }
}

class SwapThread extends  Thread{

    private Object obj1 = null;
    private Object obj2 = null;

    public SwapThread(Object obj1,Object obj2){
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        synchronized (obj1){
            System.out.println(Thread.currentThread().getName()+"准备进入第二层");
            synchronized (obj2){
                for (int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+" : "+i);
                }
                obj2.notifyAll();
            }
            obj1.notifyAll();
            System.out.println(Thread.currentThread().getName()+" : end");
        }
    }
}
