package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domian.Account;
import com.itheima.service.IAccountService;


/**
 * 账户的业务层实现类
 * 事务的控制 都在业务层
 */
public class IAccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;


    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }




    public Account findAccountById(Integer accountId) {

          return accountDao.findAccountById(accountId);


    }

    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("trans----");
        //2.执行操作
        //2.1.根据名称查询转出账户
        Account source =accountDao.findAccountByName(sourceName);
        //2.2.根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3.转出账户减钱
        source.setMoney(source.getMoney()-money);
        //2.4.转入账户加钱
        target.setMoney(target.getMoney()+money);
        //2.5.更新转出账户
        accountDao.updateAccount(source);

        //2.6.更新转入账户
        accountDao.updateAccount(target);

    }



    public void updateAccount(Account account) {

           accountDao.updateAccount(account);

    }


}
