package com.petr.spring.custom_scope.scope;

import com.petr.spring.custom_scope.annotation.Restore;
import com.petr.spring.custom_scope.annotation.Save;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SnapshotRestoreScope implements Scope {
    public static final String SCOPE_NAME= "snapshotScope";

    private final Map<String, Map<Integer, Object>> beanSnapshot = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        return getScopeObject(name, objectFactory);
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return "";
    }

    private int getCurrentVersion(String beanName) {
        Map<Integer, Object> shapshots = beanSnapshot.get(beanName);
        return (shapshots != null) ? shapshots.size() : 1;
    }

    private Object cloneBean(Object bean) {
        try {
            var newBean = bean.getClass().getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(bean, newBean);
            return newBean;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);

        }
    }

    private Object createProxy(String name, Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object result = method.invoke(target, args);

                if (method.isAnnotationPresent(Restore.class)) {
                    return restoreShapshot(name, Integer.parseInt(args[0].toString()));
                }

                if (method.isAnnotationPresent(Save.class)) {
                    takeSnapshot(name, target);
                }
                return result;
            }
        });
        return enhancer.create();
    }
    private void takeSnapshot(String beanName, Object bean){
        int currentVersion = getCurrentVersion(beanName);

        Map<Integer,Object> shapshot = beanSnapshot.computeIfAbsent(beanName, k-> new HashMap<>());

        shapshot.put(currentVersion+1, createProxy(beanName, cloneBean(bean)));
    }

    private Object restoreShapshot(String beanName, int version){
        Map<Integer,Object> snapshots = beanSnapshot.get(beanName);
        return snapshots.get(version);

    }

    private Object getScopeObject(String beanName, ObjectFactory<?> objectFactory){
        Supplier<Object> objectSupplier =() -> createProxy(beanName,objectFactory.getObject());
        Map<Integer,Object> snapshots = beanSnapshot.get(beanName);
        if(snapshots != null){
            Object lastSnapshot = snapshots.get(getCurrentVersion(beanName));
            if (lastSnapshot !=null){
                return lastSnapshot;
            }
        }
        beanSnapshot.put(beanName,new HashMap<>(Map.of(getCurrentVersion(beanName),objectSupplier.get())));
        return objectSupplier.get();
    }
}


