package com.itheima.cglib;

import com.itheima.proxy.IProducer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();

        /**
         * 动态代理：
         * 特点：字节码随用随创建，随用随加载
         * 作用：不修改源码的基础上对方法增强
         *
         * 分类：
         *      基于接口的动态代理
         *      基于子类的动态代理
         *  基于子类的动态代理
         *      涉及的类：Enhancer
         *      提供者：第三方cglib库
         *    如何创建代理对象
         *      使用Ehancer类中的create方法
         *   创建代理对象的要求
         *
         *    被代理对象不能是最终类
         *      create方法参数：
         *      Class:字节码
         *          它适用于被指定代理对象的字节码
         *
         *      Callback：用于提供增强的代码
         *          他是让我们写如何代理，我们一般都是些一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的
         *          匿名内部类访问外部成员变量
         *          我们一般写的都是该接口的子接口实现类：MethodInterceptor
         */
        Producer cglib  = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过方法
             * @param proxy
             * @param method
             * @param args
             * 以上三个参数和基于接口的代理对象中的invoke方法的参数是一样的
             * @param methodProxy：当前执行放法的代理对象
             * @return
             * @throws Throwable
             */
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

                //增强的代码
                Object returnValue = null;
                //1.获取方法执行的参数
                Float money = (Float) args[0];
                //2.判断当前方法是不是销售
                if ("saleProduct".equals(method.getName())) {
                    returnValue = method.invoke(producer, money * 0.8f);
                }
                return returnValue;
            }
        });
        cglib.saleProduct(12000F);
    }
}
