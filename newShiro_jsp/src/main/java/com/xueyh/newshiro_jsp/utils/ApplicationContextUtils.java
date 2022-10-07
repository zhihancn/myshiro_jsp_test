package com.xueyh.newshiro_jsp.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    //创建好的工厂 以 参数的形式回传
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context  = applicationContext;
    }

    //基于bean的名字获取工厂中的对象
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

}
