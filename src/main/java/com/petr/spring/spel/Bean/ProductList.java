package com.petr.spring.spel.Bean;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductList {

    private final List<Product> products = new ArrayList<>() {{
        add(new Product(10,"Bread", true));
        add(new Product(1,"Meat", false));
        add(new Product(25, "Vegatebles",true));
        add(new Product(3, "MKilk",true));
    }};

    public List<Product> getProducts() {
        return products;
    }

    static class Product{

        private final int count;

        private final String type;

        private final Boolean isFit;

        public int getCount() {
            return count;
        }

        public String getType() {
            return type;
        }

        public Boolean getFit() {
            return isFit;
        }

        Product(int count, String type, Boolean isFit) {
            this.count = count;
            this.type = type;
            this.isFit = isFit;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "count=" + count +
                    ", type='" + type + '\'' +
                    ", isFit=" + isFit +
                    '}';
        }
    }
}
