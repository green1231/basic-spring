package com.petr.spring.spel.Bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class Shop {

    @Value("#{productList.getProducts()}")
    private List<ProductList.Product> allProducts;

    @Value("#{productList.getProducts().?[getFit()]}")
    private List<ProductList.Product> suitableProduct;

    @Value("#{productList.getProducts().?[getCount() < 10]}")
    private List<ProductList.Product> endingProduct;

    @Value("#{productList.getProducts()[2]}")
    private ProductList.Product vegetables;

    @Value("#{T(java.time.LocalTime).of(9,0)}")
    private LocalTime openTime;


    public void porintOpenTime(){
        System.out.println("Open time: "+ openTime);
    }



    public void printVegetables(){
        System.out.println("Vegetables: "+ vegetables);
    }

    public void printEndingProduct(){
        System.out.println("Ending product: "+ endingProduct);
    }

    public void printSuitableProduct(){
        System.out.println("Suitable Product: "+ suitableProduct);
    }

    public void printAllPruducts(){
        System.out.println("All products"+ allProducts);
    }
}
