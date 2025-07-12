package com.petr.spring.spel;

import com.petr.spring.spel.Bean.Shop;
import com.petr.spring.spel.Bean.SimpleSpelExamples;
import com.petr.spring.spel.config.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        SimpleSpelExamples simpleSpelExamples = context.getBean(SimpleSpelExamples.class);
        simpleSpelExamples.simpleEval();

        Shop shop = context.getBean(Shop.class);
        shop.printAllPruducts();
        System.out.println("_______________");
        shop.printSuitableProduct();
        System.out.println("_______________");
        shop.printEndingProduct();
        System.out.println("_______________");
        shop.printVegetables();
        System.out.println("_______________");
        shop.porintOpenTime();
        System.out.println("_______________");
        simpleSpelExamples.simpleEvaluateContextExample();
    }
}
