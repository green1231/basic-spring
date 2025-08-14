package com.petr.spring.custom_scope.bean;

import com.petr.spring.custom_scope.annotation.Restore;
import com.petr.spring.custom_scope.annotation.Save;

public class StateBean {
    private String date;

    public StateBean(){}

    public StateBean(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Restore
    public StateBean restore(int version){
        return null;

    }

    @Save
    public void save(){

    }

    @Override
    public String toString() {
        return "StateBean{" +
                "date='" + date + '\'' +
                '}';
    }
}
