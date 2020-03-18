package com.itheima.utils;

/**
 * 记录日志的工具类
 */
public class Logger {
    /**
     * 用于打印日志，计划让其在切入点方法执行之前执行（切入点就是业务层方法）
     */
    public void printLog(){
        System.out.println("Logger类中的pringLog方法开始记录日志了。。。。");
    }
}
