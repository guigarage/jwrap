package com.guigarage.uif.toolkit.jfx;

import com.guigarage.uif.delegates.api.AbstractObservable;
import javafx.beans.value.ObservableValue;

public class JavaFXObservable<T> extends AbstractObservable<T> {

    private ObservableValue<T> internalObservable;

    public JavaFXObservable(ObservableValue<T> internalObservable) {
        this.internalObservable = internalObservable;
    }

    @Override
    public T get() {
        return internalObservable.getValue();
    }

    public ObservableValue<T> getInternalObservable() {
        return internalObservable;
    }
}
