package com.itheima.dao;

import com.itheima.domian.Account;

import javax.swing.*;

/**
 * 账户的持久层
 */
public interface IAccountDao {
    /**
     * 根据ID查询账户
     * @param accountId
     * @return
     */
    Account findAccountById (Integer accountId);

    /**
     * 根据名称查询账户
     * @param accountName
     * @return
     */
    Account findAccountByName(Spring accountName);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);
}
