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
    /**
     * 获取spring的核心容器，病根据id获取对象
     * @param args
     * ApplicationContext的三个常用实现类
     * ClassPathXmlApplicationContext : 加载类路经下的 配置文件;要求配置文件必须在类路径下的，不在的话，加载不了(更常用一些)
     * FileSystemXmlApplicationContext：他可以加载磁盘任意路径下的配置文件（必须有访问权限）
     * AnnotationConfigApplicationContext：它用于读取注解创建容器的
     *
     *核心容器的两个接口引发的问题：
     * ApplicationContext：  单例对象适用
     *      它在构建核心容器时，创建的对象采取的策略时残缺哦那个立即加载的方式。也就是说，只要一读取完配置文件马上就创建配置文件中的配置对象
     *
     * BeanFactory：        多例对象使用
     * 他在构建核心容器时，创建对象采取的策略是延迟加载的方式，也就是说，什么时候根据id获取对象了，什么时候才真正创建对象。
     *
     */

    public static void main(String[] args) {
        //1.获取核心容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //ApplicationContext ac = new FileSystemXmlApplicationContext("E:\\IDEA\\day01_eesy01_03spring\\src\\main\\resources\\bean.xml");
        //2.根据id获取Bean对象
        IAccountService as = (IAccountService)ac.getBean("accountService");
        IAccountDao addo = ac.getBean("accountDao",IAccountDao.class);

        System.out.println(as);
        System.out.println(addo);
    as.saveAccount();
    }
}
