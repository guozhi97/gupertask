package com.patterns.singleton;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class SingletonInnerTest {

    @Test
    void getInstance() {
        //Destroy the singleton pattern by Serialization
        SingletonInner inner=SingletonInner.getInstance();
        SingletonInner inner1=null;

        try {
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("b.bin"));
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("b.bin"));
            out.writeObject(inner);
            out.flush();
            inner1=(SingletonInner) in.readObject();
            System.out.println(inner==inner1);
            System.out.println(inner+"-"+inner1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        //destroy the singleton by reflection mode
        SingletonDCL instance1=SingletonDCL.getInstance();
        SingletonDCL instance2=null;
        try {
            Constructor<SingletonDCL> constructor=SingletonDCL.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            instance2=constructor.newInstance();
            System.out.println("two instance equal :"+(instance1==instance2));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInner(){
        //reflection attack
        SingletonInner inner1=SingletonInner.getInstance();
        SingletonInner inner2=null;
        try{
            Constructor<SingletonInner> constructor = SingletonInner.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            inner2=constructor.newInstance();
            System.out.println("two instance equal :"+(inner1==inner2));
        }catch (NoSuchMethodException e ){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }

}