package com.guigarage.uif.delegates;

import com.guigarage.uif.delegates.api.Property;
import com.guigarage.uif.delegates.api.ViewNodeDelegate;

import java.util.function.Supplier;

/**
 * Created by hendrikebbers on 15.01.15.
 */
public interface WithOnAction<S> extends ViewNodeDelegate<S> {

    default void setOnAction(Supplier<ActionEvent> handler) {
        getOnActionProperty().set(handler);
    }

    default Supplier<ActionEvent> getOnAction() {
        return getOnActionProperty().get();
    }

    default Property<Supplier<ActionEvent>> getOnActionProperty() {
        return getPropertyForName("onAction");
    }

    default boolean isOnActionSupported() {
        return isPropertySupported("onAction");
    }
}
