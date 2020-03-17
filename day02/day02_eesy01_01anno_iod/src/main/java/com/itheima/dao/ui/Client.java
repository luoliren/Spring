package com.itheima.dao.ui;

import com.itheima.dao.IAccountDao;

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 模拟一个表现层调用业务层
 *
 */
public class Client {

    public static void main(String[] args) {
        //1.获取核心容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //ApplicationContext ac = new FileSystemXmlApplicationContext("E:\\IDEA\\day01_eesy01_03spring\\src\\main\\resources\\bean.xml");
        //2.根据id获取Bean对象
        IAccountService as = (IAccountService)ac.getBean("accountService");
        as.saveAccount();
//        System.out.println(as);
//
//        IAccountDao iAccountDao = ac.getBean("accountDao",IAccountDao.class);
//        System.out.println(iAccountDao);
       // as.saveAccount();
    }
}
