package com.guigarage.uif.toolkit.swing;

import com.guigarage.uif.delegates.api.AbstractObservable;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public class SwingObservable<T> extends AbstractObservable<T> {

    private Object bean;

    private PropertyDescriptor propertyDescriptor;

    public SwingObservable(Object bean, String propertyName) {
        this.bean = bean;
        try {
            propertyDescriptor = new PropertyDescriptor(propertyName, bean.getClass());
        } catch (IntrospectionException e) {
            throw new RuntimeException("TODO");
        }
    }

    @Override
    public T get() {
        try {
            return (T) propertyDescriptor.getReadMethod().invoke(bean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("TODO");
        }
    }
}
