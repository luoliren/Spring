package com.itheima.jdbctemplate;

import com.itheima.domian.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * CRUD
 */
public class jdbcTempLateDemo2 {
    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        JdbcTemplate jt = applicationContext.getBean("jdbcTemplate",JdbcTemplate.class);
        //3.执行操作
        jt.execute("insert into account (name ,money) values ('ccc',2000)");
        //保存
        jt.update("insert into account(name ,money) values (?,?)", "eee",3333);
        //删除
        jt.update("delete from account where id = ?",8);
        //更新
        jt.update("update account set name = ?,money = ? where id = ?","test","2343",5);
        //查询所有
        //List<Account> accounts = jt.query("select *from account where money > ?",new AccountRowMapper() ,1000f);
  /*          List<Account> accounts = jt.query("select *from account where money > ?",new BeanPropertyRowMapper<Account>(Account.class),1000);
        for (Account account :accounts){
            System.out.println(account);
        }*/
        //查询一个
        List<Account> accounts = jt.query("select *from account where id > ?",new BeanPropertyRowMapper<Account>(Account.class),1);
        System.out.println(accounts.isEmpty()?"没有内容":accounts.get(0));
        //查询返回一行一列（使用聚合函数，但不加group 不要子句）
        Long count = jt.queryForObject("select count (*)from account where money > ?", Long.class/*写的是返回的类型*/, 1000f);
        System.out.println(count );

    }
}

/**
 * 定义account的封装策略
 */
class AccountRowMapper implements RowMapper<Account> {
    /**
     * 把结果集的数据封装到Account中，然后spring把每个Account加到集合中
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}