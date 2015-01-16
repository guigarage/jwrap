package com.guigarage.uif.delegates.api;

/**
 * Created by hendrikebbers on 11.01.15.
 */
public interface Observable<T> {

    T get();

    void addListener(ValueChangedListener<T> listener);

    void removeListener(ValueChangedListener<T> listener);
}
