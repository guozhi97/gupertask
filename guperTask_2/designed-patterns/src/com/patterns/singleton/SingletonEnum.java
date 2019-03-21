package com.patterns.singleton;

public enum SingletonEnum {
    INSTANCE;
    private int i=0;
    public void doSomething(){
        System.out.println("do sth i:"+i++);
    }
}
