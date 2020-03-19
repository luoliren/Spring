package com.itheima.service;

import com.itheima.domian.Account;

/**
 * 账户的业务层接口
 */
public interface IAccountService {
    /**
     * 根据id查询账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 转账
     * @param sourceName
     * @param targetName
     * @param money
     */
    void transfer(String sourceName,String targetName, Float money);
}
