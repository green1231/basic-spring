package com.petr.spring.lifecycle.bean;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

@Component
public class CoffeeShop {
    private final ObjectProvider<Coffee> coffeeObjectProvider;

    private final Map<String,Ingredient> ingredients;

    public CoffeeShop(ObjectProvider<Coffee> coffeeObjectProvider, Map<String, Ingredient> ingredients) {
        this.coffeeObjectProvider = coffeeObjectProvider;
        this.ingredients = ingredients;
    }

    @PostConstruct
    public void openShop() {
        System.out.println("Shop is opened");
    }

    public void makeCoffee() {
        System.out.println("Making coffee");
    }

    public void makeCoffee(String type) {
        Coffee coffee = coffeeObjectProvider.getObject(type);
        System.out.println("Make coffee: " + coffee);
    }

    public void makeCoffee(String type, String ingredient){
        Coffee coffee = coffeeObjectProvider.getObject(type);
        System.out.println("Making coffee waith ingredient: "+ ingredients.get(ingredient) + coffee);
    }

    @PreDestroy
    public void closeShop() {
        System.out.println("Shop is closed");
    }

}
