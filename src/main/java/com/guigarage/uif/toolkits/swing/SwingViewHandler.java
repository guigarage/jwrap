package com.guigarage.uif.toolkits.swing;

import com.guigarage.uif.mvc.ActionEvent;
import com.guigarage.uif.mvc.ActionTrigger;
import com.guigarage.uif.mvc.PlatformViewHandler;
import com.guigarage.uif.util.AccessControllerUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.function.Consumer;

public class SwingViewHandler implements PlatformViewHandler<Component> {

    @Override
    public Component findChildById(Component view, String id) {
        if (view instanceof Container) {
            for (Component child : ((Container) view).getComponents()) {
                if (child.getName() != null && child.getName().equals(id)) {
                    return child;
                }
                Component recursiveRet = findChildById(child, id);
                if (recursiveRet != null) {
                    return recursiveRet;
                }
            }
        }
        return null;
    }

    private Component findChildByActionId(Object controller, String id) {
        for (Field field : AccessControllerUtils.getInheritedDeclaredFields(controller.getClass())) {
            if (field.isAnnotationPresent(ActionTrigger.class) && field.getAnnotation(ActionTrigger.class).value().equals(id) && isViewNodeType(field.getType())) {
                return AccessControllerUtils.getPrivileged(field, controller);
            }
        }
        return null;
    }

    @Override
    public void registerActionListener(Consumer<ActionEvent> listener, String actionId, Object controller) {
        Component comp = findChildByActionId(controller, actionId);
        if (comp != null) {
            registerActionListener(listener, comp);
        }
    }

    private void registerActionListener(Consumer<ActionEvent> listener, Component component) {
        if (component instanceof AbstractButton) {
            ((AbstractButton) component).addActionListener(e -> listener.accept(new ActionEvent(e)));
        } else {
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        listener.accept(new ActionEvent(e));
                    }
                }
            });
        }
    }

    @Override
    public Class<Component> getBasicViewClass() {
        return Component.class;
    }

}
