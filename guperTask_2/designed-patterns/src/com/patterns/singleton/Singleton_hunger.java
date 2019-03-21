package com.patterns.singleton;

public class Singleton_hunger {
    private  static final Singleton_hunger instance = new Singleton_hunger();
    private Singleton_hunger(){
        System.out.print("construction Singleton hunger");
    }
    public static Singleton_hunger getInstance(){
        return instance;
    }

}
