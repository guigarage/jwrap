package com.guigarage.uif.toolkit.jfx;

import com.guigarage.uif.delegates.api.AbstractProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.WritableObjectValue;

public class JavaFXProperty<T, U extends ReadOnlyProperty<T> & WritableObjectValue<T>> extends AbstractProperty<T> {

    private U internalProperty;

    public JavaFXProperty(U internalProperty) {
        this.internalProperty = internalProperty;
        internalProperty.addListener((obs, oldV, newV) -> fireValueChanged(oldV, newV));
    }

    public U getInternalProperty() {
        return internalProperty;
    }

    @Override
    public void set(T value) {
        internalProperty.set(value);
    }

    @Override
    public T get() {
        return internalProperty.get();
    }
}
