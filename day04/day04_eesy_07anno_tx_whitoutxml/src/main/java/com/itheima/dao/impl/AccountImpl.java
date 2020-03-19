package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domian.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的持久层实现类  实现jar包中的JdbcDaoSupport 使用xml配置文件
 */
@Repository("accountDao")
public class AccountImpl implements IAccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Account findAccountById(Integer accountId) {
         List<Account> list = jdbcTemplate.query("select *from account where id =?",new BeanPropertyRowMapper<Account>(Account.class),accountId);
            return list.isEmpty()?null:list.get(0);
    }

    public Account findAccountByName(String accountName) {

        List<Account> list = jdbcTemplate.query("select *from account where name =?",new BeanPropertyRowMapper<Account>(Account.class),accountName);
        if (list.isEmpty()) {
            return null;
        }
        if (list.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return list.get(0);
    }

    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
    }
}
