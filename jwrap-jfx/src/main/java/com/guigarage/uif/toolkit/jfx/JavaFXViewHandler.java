package com.guigarage.uif.toolkit.jfx;

import com.guigarage.uif.delegates.api.ViewNodeWrapper;
import com.guigarage.uif.mvc.ActionEvent;
import com.guigarage.uif.mvc.ActionHandler;
import com.guigarage.uif.mvc.PlatformViewHandler;
import javafx.scene.Node;
import javafx.scene.control.*;

/**
 * Created by hendrikebbers on 24.10.14.
 */
public class JavaFXViewHandler implements PlatformViewHandler<Node> {

    @Override
    public Node findChildById(Node view, String id) {
        return view.lookup("#" + id);
    }

    @Override
    public <U extends Node> ViewNodeWrapper<U> createWrapper(U component) {
        return new JavaFXNodeWrapper<>(component);
    }

    @Override
    public void registerActionListener(ActionHandler listener, String actionId, Object controller) {
        Node node = findChildByActionId(controller, actionId);
        if (node != null) {
            registerActionListener(listener, node);
        }
    }

    private void registerActionListener(ActionHandler listener, Node node) {
        if (node instanceof ButtonBase) {
            ((ButtonBase) node).setOnAction((e) -> listener.handleAction(new ActionEvent(e)));
        } else if (node instanceof TextField) {
            ((TextField) node).setOnAction((e) -> listener.handleAction(new ActionEvent(e)));
        } else {
            node.setOnMouseClicked((e) -> {
                if (e.getClickCount() > 1) {
                    listener.handleAction(new ActionEvent(e));
                }
            });
        }
    }

    @Override
    public Class<Node> getBasicViewClass() {
        return Node.class;
    }

}
