package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 */
@Component("txManager")
@Aspect
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void ptl(){}
    /**
     * 开启事务
     */
    @Before("ptl()")
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e) {

        }
    }
    /**
     * 提交事务
     */
    @AfterReturning("ptl()")
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e) {

        }
    }
    /**
     * 回滚事务事务
     */
    @AfterThrowing("ptl()")
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e) {

        }
    }
    /**
     * 释放事务
     */
    @After("ptl()")
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();//还回连接池中
            connectionUtils.removeConnection();
        }catch (Exception e) {

        }
    }
    @Around("ptl()")
    public Object arroundAdvice(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{

            //1.获取参数
            Object []args = pjp.getArgs();
            //2.开启事务
            this.beginTransaction();
            //3.执行方法
            rtValue = pjp.proceed(args);
            this.commit();

            return rtValue;
        }catch (Throwable e) {
            //回滚事务
            this.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放资源
            this.release();
        }
    }
}
