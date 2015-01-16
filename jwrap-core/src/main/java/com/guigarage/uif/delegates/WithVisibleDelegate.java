package com.guigarage.uif.delegates;

import com.guigarage.uif.delegates.api.ViewNodeDelegate;
import com.guigarage.uif.delegates.api.Property;

/**
 * Created by hendrikebbers on 11.01.15.
 */
public interface WithVisibleDelegate<S> extends ViewNodeDelegate<S> {

    default Property<Boolean> getVisibleProperty() {
        return getUiComponentWrapper().getPropertyForName("visible");
    }

    default boolean isVisible() {
        return getVisibleProperty().get();
    }

    default void setVisible(boolean visible) {
        getVisibleProperty().set(visible);
    }

    default boolean isVisibleSupported() {
        return getUiComponentWrapper().isPropertySupported("visible");
    }
}
