package com.patterns.singleton;

import java.io.Serializable;

public class SingletonDCL implements Serializable {
    private static SingletonDCL instance ;
    private static boolean sure=false;
    private SingletonDCL(){
        synchronized(SingletonDCL.class){
            if (true){
                throw new RuntimeException("build instance repeat");
            }
            sure=true;
        }
    }
    public static SingletonDCL getInstance(){
        if (instance==null){
            synchronized (SingletonDCL.class){
                if (instance==null){
                    instance=new SingletonDCL();
                }
            }
        }
        return instance;
    }
}
