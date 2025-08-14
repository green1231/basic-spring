package com.petr.spring.profile;

import com.petr.spring.profile.bean.CoffeeShop;

import com.petr.spring.profile.bean.Holder;
import com.petr.spring.profile.bean.LazyBean;
import com.petr.spring.profile.config.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        CoffeeShop coffeeShop = context.getBean(CoffeeShop.class);
        coffeeShop.orderCoffee();
        coffeeShop.printShopName();

        Holder holder = context.getBean(Holder.class);
        holder.fireLazyBean();

        LazyBean lazyBean = context.getBean(LazyBean.class);
        lazyBean.fireHolderBean();
    }
}
