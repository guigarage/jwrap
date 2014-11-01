package com.guigarage.uif.mvc;

import com.guigarage.uif.exception.ToolkitException;

import java.awt.*;
import java.util.function.Consumer;

public interface PlatformViewHandler<V> {

    default V createView(Class<?> controllerClass) throws ToolkitException {
        try {
            return (V) Class.forName(controllerClass.getName().substring(0, controllerClass.getName().length() - "Controller".length()) + "View").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new ToolkitException("Can't create view", e);
        }
    }

    V findChildById(V parent, String id);

    void registerActionListener(Consumer<ActionEvent> listener, String actionId, Object controller);

    default boolean isViewNodeType(Class<?> type) {
        return getBasicViewClass().isAssignableFrom(type);
    }

    Class<V> getBasicViewClass();
}
