package com.jane.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Janus on 2018/9/28.
 */
public class TestThreadPool {

    public static void main(String[] args) throws Exception {
        //1.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //参数为Runnable
        ThreadPoolRunnable threadPoolRunnable = new ThreadPoolRunnable();
        // 2.为线程池中的线程分配任务
//        for (int i = 0; i <10 ; i++) {
//            pool.submit(threadPoolRunnable);
//        }

        //参数为Callable
        ThreadPoolCallable threadPoolCallable = new ThreadPoolCallable();
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Future<Integer> future = pool.submit(threadPoolCallable);
            futures.add(future);
        }
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }

        //3.关闭线程池
        pool.shutdown();

    }
}

class ThreadPoolRunnable implements  Runnable{

    private int i = 0;

    @Override
    public void run() {
        while(i <= 100){
            System.out.println(Thread.currentThread().getName() + " : " + i++);
        }
    }
}

class ThreadPoolCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum =0;
        for(int i=1;i<=100;i++){
            sum+=i;
        }
        System.out.println(Thread.currentThread().getName() + " : " +sum);
        return sum;
    }
}
