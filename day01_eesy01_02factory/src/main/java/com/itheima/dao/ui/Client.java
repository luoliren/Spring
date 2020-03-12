package com.itheima.dao.ui;

import com.itheima.dao.IAccountDao;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * 模拟一个表现层调用业务层
 */
public class Client {
    public static void main(String[] args) {
      /*  IAccountService as = new AccountServiceImpl();*/
        for (int i = 0; i <5 ; i++) {
            IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(as);
        }

 //       as.saveAccount();
    }
}
