package com.jane.controller;

import com.jane.model.Parent;

/**
 * Created by Janus on 2018/9/13.
 */
public class TestParent {

    public static void main(String[] args){

        Parent parent = new Parent();
        parent.setAge(10);
        changeP(parent);
        System.out.println(parent);

        String str="hello";
        changeStr(str);
        System.out.println(str);

    }

    public  static String changeStr(String str){
        System.out.println("changeStr-before:"+str);
        str="welcome";
        System.out.println("changeStr-after:"+str);
        return  str;
    }

    public  static Parent changeP(Parent parent){
        parent.setAge(11);
        return  parent;
    }
}
