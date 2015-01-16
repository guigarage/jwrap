package com.guigarage.uif.toolkit.jfx;

import com.guigarage.uif.delegates.api.AbstractUiComponentWrapper;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.WritableObjectValue;
import javafx.scene.Node;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public class JavaFXNodeWrapper<S extends Node> extends AbstractUiComponentWrapper<S> {

    public JavaFXNodeWrapper(S component) {
        super(component);
    }

    @Override
    public <T> JavaFXProperty<T, ?> getPropertyForName(String propertyName) {
        return internalGetPropertyForName(propertyName);
    }

    private <T, U extends ReadOnlyProperty<T> & WritableObjectValue<T>> JavaFXProperty<T, U> internalGetPropertyForName(String propertyName) {
        if (!isPropertySupported(propertyName)) {
            throw new RuntimeException("TODO");
        }
        try {
            return new JavaFXProperty<T, U>((U) getPropertyMethod(propertyName).invoke(getComponent()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("TODO", e);
        }
    }

    @Override
    public boolean isPropertySupported(String propertyName) {
        return getPropertyMethod(propertyName) != null;
    }

    private Method getPropertyMethod(String propertyName) {
        try {
            return getComponent().getClass().getMethod(propertyName + "Property");
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
