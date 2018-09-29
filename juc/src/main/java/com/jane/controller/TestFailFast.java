package com.jane.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Janus on 2018/9/13.
 */
public class TestFailFast {
    public static void main(String[] args){

        FailFast failFast = new FailFast();
//        for (int i = 0; i < 100; i++) {
            new Thread(failFast).start();
//        }

    }
}

class FailFast implements  Runnable{

    private List list=new ArrayList();
//    private CopyOnWriteArrayList list=new CopyOnWriteArrayList ();


    {
        for (int i=0;i<100;i++){
            list.add(i);
        }
    }

    @Override
    public void run() {
//        synchronized (list){
        ListIterator iterator = list.listIterator();
        System.out.println(iterator.getClass());
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        list.add("AA");
//        }

    }
}
