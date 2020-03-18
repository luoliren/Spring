package com.itheima.service;

/**
 * 账户的业务接口
 */
public interface IAccountService {
    /**
     * 模拟保存账户
     */
    void saveAccount();

    /**
     * 模拟更新账户
     */
    void updateAccount(int i);

    /**
     * 删除账户
     */
    int delectAccount();
}
