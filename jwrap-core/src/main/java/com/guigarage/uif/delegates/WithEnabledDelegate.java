package com.guigarage.uif.delegates;

import com.guigarage.uif.delegates.api.ViewNodeDelegate;
import com.guigarage.uif.delegates.api.Property;

/**
 * Created by hendrikebbers on 12.01.15.
 */
public interface WithEnabledDelegate<S> extends ViewNodeDelegate<S> {

    default Property<Boolean> getEnabledProperty() {
        return getUiComponentWrapper().getPropertyForName("enabled");
    }

    default boolean isEnabled() {
        return getEnabledProperty().get();
    }

    default void setEnabled(boolean visible) {
        getEnabledProperty().set(visible);
    }

    default boolean isEnabledSupported() {
        return getUiComponentWrapper().isPropertySupported("enabled");
    }
}
