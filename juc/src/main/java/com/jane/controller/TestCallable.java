package com.jane.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Janus on 2018/9/16.
 */
public class TestCallable {
    public static void main(String[] args){

        CallableDemo callableDemo = new CallableDemo();
        //执行Callable需要有FutureTask支持，用于接收运算结果
        FutureTask<Integer> futureTask = new FutureTask<>(callableDemo);
        new Thread(futureTask).start();

        try {
            //接收线程运算后结果
            Integer sum = futureTask.get();
            System.out.println(sum);
            //futureTask.get();执行完才能打印横线，说明
            System.out.println("--------------------------------------");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class CallableDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i=0;i<=100;i++){
            System.out.println(i);
            sum+=i;
        }
        return sum;
    }
}
