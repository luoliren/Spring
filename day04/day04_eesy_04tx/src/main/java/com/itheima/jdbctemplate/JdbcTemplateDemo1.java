package com.itheima.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.DriverManager;

/**
 * jdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo1{
    public static void main(String[] args) {
        //准备数据源，spring的内部数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql:///eesy01");
        ds.setUsername("root");
        ds.setPassword("990305");

        //1.创建JDBcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();
        //给jt设置数据源
        jt.setDataSource(ds);
        //2.执行操作
        jt.execute("insert into account (name ,money) values ('ccc',1000)");
    }
}
