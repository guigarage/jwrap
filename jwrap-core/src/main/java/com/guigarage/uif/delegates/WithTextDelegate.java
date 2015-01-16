package com.guigarage.uif.delegates;

import com.guigarage.uif.delegates.api.ViewNodeDelegate;
import com.guigarage.uif.delegates.api.Property;

/**
 * Created by hendrikebbers on 11.01.15.
 */
public interface WithTextDelegate<S> extends ViewNodeDelegate<S> {

    default Property<String> getTextProperty() {
        return getUiComponentWrapper().getPropertyForName("text");
    }

    default String getText() {
        return getTextProperty().get();
    }

    default void setText(String text) {
        getTextProperty().set(text);
    }

    default boolean isTextSupported() {
        return getUiComponentWrapper().isPropertySupported("text");
    }

}
