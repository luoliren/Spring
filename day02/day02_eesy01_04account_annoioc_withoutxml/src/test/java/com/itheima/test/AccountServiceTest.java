package com.itheima.test;

import com.itheima.domian.Account;
import com.itheima.service.IAccountService;
import config.SpringConfiguration;
import config.jdbcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 * 1.应用程序的入口
 *  main方法
 * 2.junit单元猜测是中，没有main方法也能执行
 * junit集成了一个main方法
 * 该方法就会判断当前的 测试类中哪些方法有@Test注解
 * junit就会让test注解的方法执行
 * 3.junit不会管我们是否采用spring框架
 *  在执行测试方法时，junit根本不知道我们是不是采用了spring框架
 *  所以也就不会为我们队去配置文件/配置类创建spring核心容器
 *  4.由以上三点可知：
 *      当测试方法执行时，没有Ioc容器，就算写了@Autowried注解，也无法实现注入
 *
 *
 *  spring整合junit的配置
 *  1.导入spring整合junit的jar包（坐标）
 *  2.spring使用junit提供的一个注解把原有的main方法替换了，替换成了spring提供的
 *  @Runwith
 *  3.告知spring运行器，spring和ioc创建是基于xml还是注解的，并说明位置
 * @ContextConfiguration
 *      locations:指定xml文件位置，加上classpath关键字，表示在类路径下
 *      classes：指定注解类的所在位置
 *  d当我么使用spring 5.x版本的时候，要求junit的jar必须是4.12及以上的版本
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)/*获取容器*/
public class AccountServiceTest {

    @Autowired
    private IAccountService as = null;

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
