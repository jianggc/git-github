package com.jane.controller;

/**
 * Created by Janus on 2018/9/13.
 */
public class TestCompareAndSwap {

    public static void main(String[] args){
        final CompareAndSwap compareAndSwap = new CompareAndSwap();

        for (int i=0;i<10;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 获取主存值
                    int expectedValue = compareAndSwap.getValue();
                    boolean b = compareAndSwap.compareAndSet(expectedValue, (int) (Math.random() * 101));
                    System.out.println(b);

                }
            }).start();
        }

    }

}

class CompareAndSwap{

    private  int value;

    //获取主存值
    public int getValue() {
        return value;
    }

    // 比较并设置，返回主存值
    public synchronized  int compareAndSwap(int expectedValue,int newValue){
        int oldValue = value;

        //如果主存值==预估值，则进行更新
        if (oldValue==expectedValue){
            this.value=newValue;
        }
        return oldValue;
    }

    //调用设置值方法，并返回成功失败
    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        return expectedValue==compareAndSwap(expectedValue,newValue);
    }
}