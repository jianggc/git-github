package com.jane.controller;


import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Janus on 2018/9/29.
 */
public class TestForkJoinPool {

    public static void main(String[] args){

        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0, 50000000000L);

        Long sum = pool.invoke(task);
        
        System.out.println(sum);
        Instant end = Instant.now();
        long millis = Duration.between(start, end).toMillis();//842-33044
        System.out.println("耗费时间为 ： "+millis);

    }

}

class ForkJoinSumCalculate extends RecursiveTask<Long>{

    private  long start;
    private  long end;

    private static  final long THURSHOLD = 10000L;

    public ForkJoinSumCalculate(long start,long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end-start;

        if (length<THURSHOLD){
            long sum = 0L;
            for(long i=start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else{
            long middle = (start+end)/2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();//进行拆分，同时压入线程队列

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
            right.fork();

            return left.join()+right.join();
        }
    }
}