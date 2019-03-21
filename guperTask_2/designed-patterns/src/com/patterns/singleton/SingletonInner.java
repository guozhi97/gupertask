package com.patterns.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class SingletonInner implements Serializable {
    private static final long serialVersionUID = 894161327962133854L;
    private SingletonInner(){}
    public static SingletonInner getInstance(){
        return SingletonHolder.instance;
    }
    private static class SingletonHolder{
        private static final SingletonInner instance=new SingletonInner();
    }

    private Object readResolve() throws ObjectStreamException{
        return SingletonHolder.instance;
    }
}
