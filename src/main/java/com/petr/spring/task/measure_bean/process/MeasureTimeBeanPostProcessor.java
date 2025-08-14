package com.petr.spring.task.measure_bean.process;

import com.petr.spring.task.measure_bean.bean.MeasureTime;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MeasureTimeBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // Проверяем, есть ли в бине методы с аннотацией @MeasureTime
        boolean hasMeasureTimeMethod = false;
        for (Method method : bean.getClass().getMethods()) {
            if (method.isAnnotationPresent(MeasureTime.class)) {
                hasMeasureTimeMethod = true;
                break;
            }
        }

        if (!hasMeasureTimeMethod) {
            return bean;
        }

        // Создаем прокси только для бинов с аннотированными методами
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            Method originalMethod = obj.getClass().getSuperclass().getMethod(method.getName(), method.getParameterTypes());

            if (originalMethod.isAnnotationPresent(MeasureTime.class)) {
                long start = System.currentTimeMillis();
                Object result = proxy.invokeSuper(obj, args);
                System.out.printf("%s executed in %dms%n", method.getName(), System.currentTimeMillis() - start);
                return result;
            }
            return proxy.invokeSuper(obj, args);
        });

        return enhancer.create();
    }

}
