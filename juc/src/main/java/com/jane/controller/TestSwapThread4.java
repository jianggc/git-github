package com.jane.controller;

/**
 * Created by Janus on 2018/9/17.
 */
public class TestSwapThread4 {
    public static void main(String[] args){

        final Object obj1 = new Object();
        final Object obj2 = new Object();
        final Object obj3 = new Object();
        SwapThread4 swapThread = new SwapThread4(obj1,obj2);
        SwapThread4 swapThread2 = new SwapThread4(obj2,obj3);
        SwapThread4 swapThread3 = new SwapThread4(obj3,obj1);
        swapThread.setName("A");
        swapThread.start();

        swapThread2.setName("B");
        swapThread2.start();

        swapThread3.setName("C");
        swapThread3.start();

    }
}

class SwapThread4 extends  Thread{

    private Object obj1 = null;
    private Object obj2 = null;

    public SwapThread4(Object obj1,Object obj2){
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            synchronized (obj1){
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName()+" : "+i);
                    obj2.notifyAll();
                }
                try {
                    //避免程序不能正常结束
                    if (i==9){
                        obj1.notifyAll();
                    }else{
                        obj1.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
