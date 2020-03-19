package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domian.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;

/**
 * 账户的持久层实现类  实现jar包中的JdbcDaoSupport 使用xml配置文件
 */
public class AccountImpl extends JdbcDaoSupport implements IAccountDao {



    public Account findAccountById(Integer accountId) {
         List<Account> list = super.getJdbcTemplate().query("select *from account where id =?",new BeanPropertyRowMapper<Account>(Account.class),accountId);
            return list.isEmpty()?null:list.get(0);
    }

    public Account findAccountByName(Spring accountName) {

        List<Account> list = super.getJdbcTemplate().query("select *from account where name =?",new BeanPropertyRowMapper<Account>(Account.class),accountName);
        if (list.isEmpty()) {
            return null;
        }
        if (list.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return list.get(0);
    }

    public void updateAccount(Account account) {
        super.getJdbcTemplate().update("update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
    }
}
