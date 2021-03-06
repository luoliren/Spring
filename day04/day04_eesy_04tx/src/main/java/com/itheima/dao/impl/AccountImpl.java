package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domian.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

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

    public Account findAccountByName(String accountName) {
        List<Account> accounts = super.getJdbcTemplate().query("select *from account where name =?",new BeanPropertyRowMapper<Account>(Account.class),accountName);
        if (accounts.isEmpty()) {
            return null;
        }
        if (accounts.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }

    public void updateAccount(Account account) {
        super.getJdbcTemplate().update("update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
    }
}
