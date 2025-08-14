package com.petr.spring.custom_scope.proccesor;

import com.petr.spring.custom_scope.scope.SnapshotRestoreScope;
import com.petr.spring.custom_scope.scope.TemporalScope;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ScopeRegisterBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(TemporalScope.SCOPE_NAME, new TemporalScope());
        beanFactory.registerScope(SnapshotRestoreScope.SCOPE_NAME, new SnapshotRestoreScope());
    }
}
