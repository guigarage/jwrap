package com.guigarage.uif.delegates.api;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public interface Property<T> extends Observable<T> {

    void bind(Observable<T> observable);

    void unbind();

    void set(T value);
}
