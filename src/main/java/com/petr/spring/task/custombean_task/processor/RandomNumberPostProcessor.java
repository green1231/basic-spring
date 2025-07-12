package com.petr.spring.task.custombean_task.processor;

import com.petr.spring.task.custombean_task.bean.RandomNumber;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Random;

@Component
public class RandomNumberPostProcessor implements BeanPostProcessor {
    private final Random random = new Random();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(RandomNumber.class) && field.getType() == int.class) {
                try {
                    field.setAccessible(true);
                    int value = random.nextInt(1000);
                    field.setInt(bean, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException( field.getName(), e);
                }
            }
        }
        return bean;
    }
}