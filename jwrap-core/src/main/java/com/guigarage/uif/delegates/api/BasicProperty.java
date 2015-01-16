package com.guigarage.uif.delegates.api;

/**
 * Created by hendrikebbers on 14.01.15.
 */
public class BasicProperty<T> extends AbstractProperty<T> {

    private T value;

    @Override
    public void set(T value) {
        T oldValue = this.value;
        this.value = value;
        fireValueChanged(oldValue, this.value);
    }

    @Override
    public T get() {
        return value;
    }

}
