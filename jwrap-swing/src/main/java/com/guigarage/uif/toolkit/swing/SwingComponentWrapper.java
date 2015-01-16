package com.guigarage.uif.toolkit.swing;

import com.guigarage.uif.delegates.api.AbstractUiComponentWrapper;
import com.guigarage.uif.delegates.api.Property;

import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public class SwingComponentWrapper<S extends Container> extends AbstractUiComponentWrapper<S> {

    public SwingComponentWrapper(S component) {
        super(component);
    }

    @Override
    public <T> Property<T> getPropertyForName(String propertyName) {
        return new SwingProperty<T>(getComponent(), propertyName);
    }

    @Override
    public boolean isPropertySupported(String propertyName) {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, getComponent().getClass());
        } catch (IntrospectionException e) {
            throw new RuntimeException("TODO");
        }
        return false;
    }
}
