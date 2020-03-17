package com.itheima.test;

import com.itheima.domian.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 */
public class AccountServiceTest {
    @Test
    public void testFindAll(){
        //1。获取容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        IAccountService as = applicationContext.getBean("accountService",IAccountService.class);
        //3.执行方法
        List<Account> accounts = as.findAllAccount();
        //4.遍历
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
    @Test
    public void testFindOne(){
        //1。获取容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        IAccountService as = applicationContext.getBean("accountService",IAccountService.class);
        Account account = as.findAccountById(1);
        System.out.println(account);
    }
    @Test
    public void testSave(){
        //1。获取容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        IAccountService as = applicationContext.getBean("accountService",IAccountService.class);
        Account account = new Account();
        account.setMoney(40000.0F);
        account.setName("张三");
        as.saveAccount(account);
    }
    @Test
    public void testUpdate(){
        //1。获取容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        IAccountService as = applicationContext.getBean("accountService",IAccountService.class);
        Account account = as.findAccountById(4);
        account.setMoney(234567F);
        as.updateAccount(account);
    }
    @Test
    public void testDelete(){
        //1。获取容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        IAccountService as = applicationContext.getBean("accountService",IAccountService.class);
        as.deleteAccount(4);
    }

}
