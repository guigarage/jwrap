package com.guigarage.uif.toolkit.swing;

import com.guigarage.uif.delegates.api.ViewNodeDelegate;
import com.guigarage.uif.delegates.api.ViewNodeWrapper;
import com.guigarage.uif.mvc.ActionEvent;
import com.guigarage.uif.mvc.ActionHandler;
import com.guigarage.uif.mvc.ActionTrigger;
import com.guigarage.uif.mvc.PlatformViewHandler;
import com.guigarage.uif.util.AccessControllerUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingViewHandler implements PlatformViewHandler<Container> {

    @Override
    public <T extends Container> ViewNodeWrapper<T> createWrapper(T component) {
        return new SwingComponentWrapper<T>(component);
    }

    @Override
    public Container findChildById(Container view, String id) {
            for (Component c : view.getComponents()) {
                Container child = (Container) c;
                if (child.getName() != null && child.getName().equals(id)) {
                    return child;
                }
                Container recursiveRet = findChildById(child, id);
                if (recursiveRet != null) {
                    return recursiveRet;
                }
            }
        return null;
    }

    @Override
    public void registerActionListener(ActionHandler listener, String actionId, Object controller) {
        Component comp = findChildByActionId(controller, actionId);
        if (comp != null) {
            registerActionListener(listener, comp);
        }
    }

    private void registerActionListener(ActionHandler listener, Component component) {
        if (component instanceof AbstractButton) {
            ((AbstractButton) component).addActionListener(e -> listener.handleAction(new ActionEvent(e)));
        } else {
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        listener.handleAction(new ActionEvent(e));
                    }
                }
            });
        }
    }

    @Override
    public Class<Container> getBasicViewClass() {
        return Container.class;
    }

}
