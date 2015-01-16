package com.guigarage.uif.delegates.api;

/**
 * Created by hendrikebbers on 11.01.15.
 */
public interface ViewNodeWrapper<S> {

    <T> Property<T> getPropertyForName(String propertyName);

    boolean isPropertySupported(String propertyName);

    S getComponent();
}
