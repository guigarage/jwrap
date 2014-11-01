package com.guigarage.uif.mvc;

/**
 * Created by hendrikebbers on 24.10.14.
 */
public class ActionEvent<T> {

    private T platformEvent;

    public ActionEvent(T platformEvent) {
        this.platformEvent = platformEvent;
    }

    public T getPlatformEvent() {
        return platformEvent;
    }
}
