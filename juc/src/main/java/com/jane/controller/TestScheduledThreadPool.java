package com.jane.controller;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Janus on 2018/9/29.
 */
public class TestScheduledThreadPool {

    public static void main(String[] args){

        System.out.println("项目启动 ： "+new Date());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        // 延迟两秒执行ScheduledThread
//        ScheduledThread scheduledThread = new ScheduledThread();
//        scheduledExecutorService.schedule(scheduledThread,2, TimeUnit.SECONDS);

        //项目启动时延迟一秒，然后以2秒为周期执行
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable();
//        scheduledExecutorService.scheduleAtFixedRate(scheduledRunnable,1,2,TimeUnit.SECONDS);
        ScheduledRunnable2 scheduledRunnable2 = new ScheduledRunnable2();
        scheduledExecutorService.scheduleAtFixedRate(scheduledRunnable2,1,2,TimeUnit.SECONDS);

        // 上一次结束后 延迟一秒，然后以2秒为周期执行
//        ScheduledRunnable2 scheduledRunnable2 = new ScheduledRunnable2();
//        scheduledExecutorService.scheduleWithFixedDelay(scheduledRunnable2,1,2,TimeUnit.SECONDS);
    }
}

class ScheduledThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int i = new Random().nextInt(100);
        System.out.println(Thread.currentThread().getName()+" : "+i+" , "+new Date());
        return i;
    }
}

class ScheduledRunnable implements  Runnable{

    @Override
    public void run() {
        int i = (int) (Math.random()*1000);
        System.out.println(Thread.currentThread().getName()+" : "+i+" , "+new Date());
    }
}

class ScheduledRunnable2 implements  Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = (int) (Math.random()*1000);
        System.out.println(Thread.currentThread().getName()+" : "+i+" , "+new Date());
    }
}