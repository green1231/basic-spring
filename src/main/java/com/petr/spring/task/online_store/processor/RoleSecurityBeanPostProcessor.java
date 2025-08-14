package com.petr.spring.task.online_store.processor;

import com.petr.spring.task.online_store.bean.RequireRole;
import com.petr.spring.task.online_store.service.OrderOperations;
import com.petr.spring.task.online_store.service.SessionService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class RoleSecurityBeanPostProcessor implements BeanPostProcessor {

    private final SessionService sessionService;

    public RoleSecurityBeanPostProcessor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // По‑простому: защищаем только сервисы заказов через их интерфейс
        if (!(bean instanceof OrderOperations)) {
            return bean;
        }

        Class<?> beanClass = bean.getClass();
        Class<?>[] interfaces = beanClass.getInterfaces();
        if (interfaces.length == 0) {
            return bean;
        }

        return Proxy.newProxyInstance(
                beanClass.getClassLoader(),
                interfaces,
                (proxy, method, args) -> {
                    Method targetMethod = beanClass.getMethod(method.getName(), method.getParameterTypes());
                    if (targetMethod.isAnnotationPresent(RequireRole.class)) {
                        RequireRole requireRole = targetMethod.getAnnotation(RequireRole.class);
                        if (!sessionService.currentUserHasRole(requireRole.value())) {
                            throw new IllegalStateException("Insufficient rights. Required role: " + requireRole.value());
                        }
                    }
                    return method.invoke(bean, args);
                }
        );
    }
}


