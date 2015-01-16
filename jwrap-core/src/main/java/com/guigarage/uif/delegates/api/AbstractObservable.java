package com.guigarage.uif.delegates.api;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public abstract class AbstractObservable<T> implements Observable<T> {

    private List<ValueChangedListener<T>> listeners;

    public AbstractObservable() {
        listeners = new CopyOnWriteArrayList<>();
    }

    @Override
    public void addListener(ValueChangedListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ValueChangedListener<T> listener) {
        listeners.remove(listener);
    }

    protected void fireValueChanged(T oldValue, T newValue) {
        for(ValueChangedListener<T> listener : listeners) {
            listener.valueChanged(oldValue, newValue);
        }
    }
}
