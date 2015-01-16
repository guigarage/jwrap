package com.guigarage.uif.mvc;

import com.guigarage.uif.delegates.api.ViewNodeDelegate;
import com.guigarage.uif.delegates.api.ViewNodeWrapper;
import com.guigarage.uif.exception.ToolkitException;
import com.guigarage.uif.util.AccessControllerUtils;

import java.lang.reflect.Field;

public interface PlatformViewHandler<V> {

    default V createView(Class<?> controllerClass) throws ToolkitException {
        try {
            return (V) Class.forName(controllerClass.getName().substring(0, controllerClass.getName().length() - "Controller".length()) + "View").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new ToolkitException("Can't create view", e);
        }
    }

    default V findChildByActionId(Object controller, String id) {
        for (Field field : AccessControllerUtils.getInheritedDeclaredFields(controller.getClass())) {
            ActionTrigger trigger = field.getAnnotation(ActionTrigger.class);
            if (field.isAnnotationPresent(ActionTrigger.class) && field.getAnnotation(ActionTrigger.class).value().equals(id)) {
                if(isViewNodeType(field.getType())) {
                    return AccessControllerUtils.getPrivileged(field, controller);
                }
                if(isViewNodeDelegate(field.getType())) {
                    return ((ViewNodeDelegate<V>)AccessControllerUtils.getPrivileged(field, controller)).getComponent();
                }
            }
        }
        return null;
    }

    <U extends V> ViewNodeWrapper<U> createWrapper(U component);

    <U extends V, W extends V> U findChildById(W parent, String id);

    void registerActionListener(ActionHandler listener, String actionId, Object controller);

    default boolean isViewNodeType(Class<?> type) {
        return getBasicViewClass().isAssignableFrom(type);
    }

    default boolean isViewNodeDelegate(Class<?> type) {
        return ViewNodeDelegate.class.isAssignableFrom(type);
    }


    Class<V> getBasicViewClass();
}
