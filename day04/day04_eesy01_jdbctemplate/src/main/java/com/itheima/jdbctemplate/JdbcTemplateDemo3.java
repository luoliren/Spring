package com.itheima.jdbctemplate;

import com.itheima.dao.IAccountDao;
import com.itheima.domian.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * jdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo3 {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao accountDao =applicationContext.getBean("accountDao",IAccountDao.class);
        Account account = accountDao.findAccountById(1);
        System.out.println(account);

    }
}
