package com.jane.controller;

import com.jane.model.Child;

/**
 * Created by Janus on 2018/9/12.
 */
public class TestVolatile {

    public static void main(String[] args){

        Child child = new Child(1,1);
        System.out.println(child.getClass());

        ThreadDemo td=new ThreadDemo();
        new Thread(td).start();

        while (true){
//            synchronized (td){
                if (td.isFlag()){
                    System.out.println("----------------------");
                    break;
                }
//            }
        }
    }
}

class ThreadDemo implements  Runnable{

    private volatile boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag=true;
        
        System.out.println("flag= "+flag);

    }

    public boolean isFlag(){
        return this.flag;
    }

    public void setFlag(boolean flag){
        this.flag=flag;
    }
}
