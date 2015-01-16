package com.guigarage.uif.delegates.api;

/**
 * Created by hendrikebbers on 14.01.15.
 */
@FunctionalInterface
public interface ValueChangedListener<T> {

    void valueChanged(T oldValue, T newValue);
}
