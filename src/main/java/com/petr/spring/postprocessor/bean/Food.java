package com.petr.spring.postprocessor.bean;

public class Food {
    private String type;
    private String dishName;

    public Food(String type, String dishName) {
        this.type = type;
        this.dishName = dishName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Override
    public String toString() {
        return "Food{" +
                "type='" + type + '\'' +
                ", dishName='" + dishName + '\'' +
                '}';
    }
}
