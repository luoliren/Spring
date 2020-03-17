package com.itheima.test;

import com.itheima.domian.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {
    @Autowired
    private IAccountService as;
    @Test
    public void testFindAll(){

        //3.执行方法
        List<Account> accounts = as.findAllAccount();
        //4.遍历
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
    @Test
    public void testFindOne(){

        Account account = as.findAccountById(1);
        System.out.println(account);
    }
    @Test
    public void testSave(){

        Account account = new Account();
        account.setMoney(40000.0F);
        account.setName("张三");
        as.saveAccount(account);
    }
    @Test
    public void testUpdate(){

        Account account = as.findAccountById(1);
        account.setMoney(234567F);
        as.updateAccount(account);
    }
    @Test
    public void testDelete(){

        as.deleteAccount(4);
    }

}
