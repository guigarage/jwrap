package com.guigarage.uif.delegates.api;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public abstract class AbstractProperty<T> extends AbstractObservable<T> implements Property<T>,ValueChangedListener<T> {

    private List<Observable<T>> boundToObservables;

    public AbstractProperty() {
        boundToObservables = new CopyOnWriteArrayList<>();
    }

    @Override
    public void bind(Observable<T> observable) {
        boundToObservables.add(observable);
        observable.addListener(this);
    }

    @Override
    public void unbind() {
        for(Observable<T> observable : boundToObservables) {
            observable.removeListener(this);
        }
        boundToObservables.clear();
    }

    @Override
    public void valueChanged(T oldValue, T newValue) {
        set(newValue);
    }
}
