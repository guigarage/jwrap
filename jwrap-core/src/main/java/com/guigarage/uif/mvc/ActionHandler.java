package com.guigarage.uif.mvc;

/**
 * Created by hendrikebbers on 16.01.15.
 */
@FunctionalInterface
public interface ActionHandler<T extends ActionEvent> {

    void handleAction(T event);

}
