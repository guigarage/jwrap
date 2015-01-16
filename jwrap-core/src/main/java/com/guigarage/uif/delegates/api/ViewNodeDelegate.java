package com.guigarage.uif.delegates.api;

/**
 * Created by hendrikebbers on 11.01.15.
 */
public interface ViewNodeDelegate<S> {

    ViewNodeWrapper<S> getUiComponentWrapper();

    default <T> Property<T> getPropertyForName(String propertyName) {
        return getUiComponentWrapper().getPropertyForName(propertyName);
    }

    default boolean isPropertySupported(String propertyName) {
        return getUiComponentWrapper().isPropertySupported(propertyName);
    }

    default S getComponent() {
        return getUiComponentWrapper().getComponent();
    }
}
