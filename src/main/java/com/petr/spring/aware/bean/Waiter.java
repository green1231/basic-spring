package com.petr.spring.aware.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Waiter implements ApplicationContextAware, BeanFactoryAware, EnvironmentAware {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    private Environment environment;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void takeOrder(String dish){
        System.out.println("Waiter take order");
     //   applicationContext.getBean(Chef.class).cook(dish);
        beanFactory.getBean(Chef.class).cook(dish);
    }

    public void greetCustomer(){
        String dayTime= environment.getProperty("daytime");
        System.out.println("Good "+ dayTime);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment =environment;

    }
}
