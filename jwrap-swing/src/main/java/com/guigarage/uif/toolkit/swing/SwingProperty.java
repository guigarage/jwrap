package com.guigarage.uif.toolkit.swing;

import com.guigarage.uif.delegates.api.AbstractProperty;

import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public class SwingProperty<T> extends AbstractProperty<T> implements PropertyChangeListener {

    private Container bean;

    private PropertyDescriptor propertyDescriptor;

    public SwingProperty(Container bean, String propertyName) {
        this.bean = bean;
        bean.addPropertyChangeListener(propertyName, this);
        try {
            propertyDescriptor = new PropertyDescriptor(propertyName, bean.getClass());
        } catch (IntrospectionException e) {
            throw new RuntimeException("TODO");
        }
    }

    @Override
    public void set(T value) {
        try {
            propertyDescriptor.getWriteMethod().invoke(bean, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        fireValueChanged((T) evt.getOldValue(), (T) evt.getNewValue());
    }
}
