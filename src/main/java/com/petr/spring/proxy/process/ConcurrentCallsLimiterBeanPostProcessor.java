package com.petr.spring.proxy.process;

import com.petr.spring.proxy.bean.IWaiter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Semaphore;

@Component
public class ConcurrentCallsLimiterBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (bean instanceof IWaiter) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {

                private final Semaphore semaphore = new Semaphore(1);

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    try {
                        System.out.println("Acqure..." + semaphore.availablePermits());
                        semaphore.acquire();
                        return method.invoke(bean, args);
                    } finally {
                        semaphore.release();
                        System.out.println("Release...");
                    }
                }
            });
        }
        return bean;
    }
}
