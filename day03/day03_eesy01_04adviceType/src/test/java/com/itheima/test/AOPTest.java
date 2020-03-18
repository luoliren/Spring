package com.itheima.test;

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试AOP的配置
 */
public class AOPTest {
    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        IAccountService ac = applicationContext.getBean("accountService", IAccountService.class);
        //3.执行方法
        ac.saveAccount();
      //  ac.updateAccount(1);
      //  ac.delectAccount();
    }
}
