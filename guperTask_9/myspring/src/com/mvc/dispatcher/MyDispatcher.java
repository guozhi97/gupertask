package com.mvc.dispatcher;

import com.mvc.annotation.MyAntowired;
import com.mvc.annotation.MyController;
import com.mvc.annotation.MyRequestMapping;
import com.mvc.annotation.MyService;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class MyDispatcher extends HttpServlet {

    //与配置文件有关：spring.properties
    private Properties properties = new Properties();
    //所有扫描文件的名称
    private List<String> classNames = new ArrayList<>();
    //ioc容器 beanName 对应的实例
    private Map<String,Object> ioc = new HashMap<>();
    //处理器映射容器
    private Map<String, Method> handerMapping = new HashMap<>();

     @Override
    public void init(ServletConfig config){
         //load configuration file
         loadConfigurationFile(config.getInitParameter("namespace"));
         System.out.println("one:"+ config.getInitParameter("namespace"));

         //Scaning related classes
         scanner((String)properties.get("scanPackage"));

         //initialize instances of related classes and place them in the IOC container
         initInstance();

         //complete dependency injection
         dependencyInjection();

         //complete mapper container
         initHanderMapper();

         System.out.println("complete myspring initialize");
     }

    private void initHanderMapper() {
         if (ioc.isEmpty()){
            return;
        }
         for (Map.Entry<String,Object> entry : ioc.entrySet()){
             Class<?> cla = entry.getValue().getClass();
             if (!cla.isAnnotationPresent(MyController.class)){ continue;}
             String baseUrl="";
             if (cla.isAnnotationPresent(MyRequestMapping.class)){
                 MyRequestMapping myRequestMapping=cla.getAnnotation(MyRequestMapping.class);
                 baseUrl=myRequestMapping.value();
             }
             Method[] methods=cla.getMethods();
             for (Method method:methods){
                 if (!method.isAnnotationPresent(MyRequestMapping.class)){continue;}
                 MyRequestMapping myRequestMapping=method.getAnnotation(MyRequestMapping.class);
                 String url=("/"+baseUrl+"/"+myRequestMapping.value()).replaceAll("/+","/");
                 handerMapping.put(url,method);
                 System.out.println("mapped: "+url+","+method);
             }
         }
    }

    private void dependencyInjection() {
         if (ioc.isEmpty()){
             return;
         }
         for (Map.Entry<String,Object> entry:ioc.entrySet()){
             Field[] fields = entry.getValue().getClass().getDeclaredFields();
             for (Field field : fields){
                     if (!field.isAnnotationPresent(MyAntowired.class)){continue;}
                     MyAntowired myAntowired = field.getAnnotation(MyAntowired.class);
                     String beanName = myAntowired.value().trim();
                     if (beanName.equals("")){
                         beanName = field.getType().getName();
                     }
                     field.setAccessible(true);
                     try {
                         field.set(entry.getValue(),ioc.get(beanName));
                     } catch (IllegalAccessException e) {
                         e.printStackTrace();
                     }
             }
         }
    }

    private void initInstance() {
         if (classNames.isEmpty()){return;}
         classNames.forEach(className -> {
             try{
                 Class<?> cla = Class.forName(className);
                 if (cla.isAnnotationPresent(MyController.class)){
                     String beanName = toFirstLowerCase(cla.getSimpleName());
                     ioc.put(beanName,cla.newInstance());
                 }
                 else if (cla.isAnnotationPresent(MyService.class)){
                     String beanName = toFirstLowerCase(cla.getSimpleName());
                     MyService service = cla.getAnnotation(MyService.class);
                     if (!service.value().equals("")){
                         beanName = service.value();
                     }
                     Object instance = cla.newInstance();
                     ioc.put(beanName,instance);
                     for (Class<?> inface : cla.getInterfaces()){
                         if (ioc.containsKey("")){
                             throw new RuntimeException("this beanName is exists");
                         }
                         ioc.put(inface.getName(),instance);
                     }
                 }
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             } catch (IllegalAccessException e) {
                 e.printStackTrace();
             } catch (InstantiationException e) {
                 e.printStackTrace();
             }

         });
    }

    private String toFirstLowerCase(String simpleName) {
         char [] chars = simpleName.toCharArray();
         chars[0] += 32;
         return String.valueOf(chars);
    }

    private void scanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        System.out.println("scan url: "+url.toString());

        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()){
            if (file.isDirectory()){
                scanner(scanPackage+"."+file.getName());
            }
            else {
                if (!file.getName().endsWith(".class")){continue;}
                String className = (scanPackage+"."+file.getName()).replace(".class","");
                classNames.add(className);
            }
        }
    }

    private void loadConfigurationFile(String namespace) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(namespace);
        try{
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
