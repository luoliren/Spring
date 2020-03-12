package com.itheima.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 *
 * Bean：在计算机英语中，有可重用组件的含义
 *
 * javaBean：用java语言编写的可重用组件
 * Javabean ！= 实体类
 *
 * 他就是创建我们的service和dao对象
 *
 * 1.需要一个配置文件，来配置我们的service和dao
 *  配置的内容：唯一标识=全限定类名（key=value）
 * 2.通过读取配置文件的内容，反射创建对象
 *
 * 我们的配置文件可以是xml也可以是properties
 */
public class BeanFactory {
    /*定义一个Properties对象*/
    private static Properties props;
    //定义一个Map,用于存放我们创建的对象，我们把它称之为容器
    private static Map<String,Object> beans;
    //使用静态代码块为Properties对象
    static {

        try {
            //实例化对象
            props = new Properties();
            //获取properties文件的流对象
            InputStream inputStream =BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(inputStream);
            //实例化容器
            beans=new HashMap<String, Object>();
            //取出配置文件中的所有的Key
            Enumeration<Object> keys = props.keys();
            //遍历枚举
            while(keys.hasMoreElements()) {
                //取出每个key
                String key = keys.nextElement().toString();
                //根据key获取values
                String beanPath = props.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                //把key和value存入容器中
                beans.put(key,value);
            }
        } catch (IOException e) {
           throw new ExceptionInInitializerError("初始化properties失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据bean的名称获取对象
     * @param beanName
     * @return
     */

    public static Object getBean(String beanName){
        return beans.get(beanName);

    }
   /* *//**
     * 根据Bean的名称获取bean对象
     * @param beanName
     * @return
     *//*
    public static Object getBean(String beanName){
        Object bean = null;

        try {
            String beanPath = props.getProperty(beanName);
            bean=Class.forName(beanPath).newInstance();//每次都会调用默认的构造函数创建对象
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bean;
    }*/

}
