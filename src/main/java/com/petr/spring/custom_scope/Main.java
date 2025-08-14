package com.petr.spring.custom_scope;

import com.petr.spring.custom_scope.bean.StateBean;
import com.petr.spring.custom_scope.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//        var temporalBean =context.getBean(TemporalBean.class);
//        temporalBean.printId();
//        try {
//            Thread.sleep(Duration.ofSeconds(6));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        temporalBean = context.getBean(TemporalBean.class);
//        temporalBean.printId();

        StateBean stateBean = context.getBean(StateBean.class);
        stateBean.setDate("Second Data");
        stateBean.save();
        stateBean.setDate("Last data");
        stateBean.save();
        System.out.println("Before restore "+ stateBean);
        System.out.println("Restore bean first version "+ stateBean.restore(1));

    }
}
