package com.hzb.interview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DesignMode {
    public static void  main(String []args){
        ProxyMode.run();
    }
}



/**
 * 动态代理模式
 * 利用java.lang.reflect.Proxy类和java.lang.reflect.InvocationHandler接口定义代理类的实现
 * 代理对象的生成过程由Proxy类的newProxyInstance方法实现
 */
class ProxyMode{

    public static void run(){
        Service serviceimpi = new ServiceImpi();
        dynamicProxyMode dynamic = new dynamicProxyMode(serviceimpi);
        Service service = (Service) dynamic.getPorxy();
        service.Add();
    }

    interface Service{
        public void Add();
    }

    static class ServiceImpi implements Service{

        public void Add() {
            System.out.println("this is Add() Service");
        }
    }
    static class dynamicProxyMode implements  InvocationHandler{

        private Object target;

        public dynamicProxyMode( Object target){
            this.target = target;
        }

       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("--before---");
            Object result = method.invoke(target, args);
            System.out.println("--after--");
           return result;
       }

       //生成代理对象
       public Object getPorxy(){
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Class<?>[] interfaces = target.getClass().getInterfaces();
            return Proxy.newProxyInstance(loader,  interfaces, this);
       }
   }

}