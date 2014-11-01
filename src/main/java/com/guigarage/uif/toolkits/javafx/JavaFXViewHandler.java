package com.guigarage.uif.toolkits.javafx;

import com.guigarage.uif.mvc.ActionEvent;
import com.guigarage.uif.mvc.ActionTrigger;
import com.guigarage.uif.mvc.PlatformViewHandler;
import com.guigarage.uif.util.AccessControllerUtils;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.lang.reflect.Field;
import java.util.function.Consumer;

/**
 * Created by hendrikebbers on 24.10.14.
 */
public class JavaFXViewHandler  implements PlatformViewHandler<Node> {

    @Override
    public Node findChildById(Node view, String id) {
        return view.lookup("#" + id);
    }

    private Node findChildByActionId(Object controller, String id) {
        for (Field field : AccessControllerUtils.getInheritedDeclaredFields(controller.getClass())) {
            if (field.isAnnotationPresent(ActionTrigger.class) && field.getAnnotation(ActionTrigger.class).value().equals(id) && isViewNodeType(field.getType())) {
                return AccessControllerUtils.getPrivileged(field, controller);
            }
        }
        return null;
    }

    @Override
    public void registerActionListener(Consumer<ActionEvent> listener, String actionId, Object controller) {
        Node node = findChildByActionId(controller, actionId);
        if (node != null) {
            registerActionListener(listener, node);
        }
    }

    private void registerActionListener(Consumer<ActionEvent> listener, Node node) {
        if (node instanceof ButtonBase) {
            ((ButtonBase) node).setOnAction((e) -> listener.accept(new ActionEvent(e)));
        } else if (node instanceof TextField) {
            ((TextField) node).setOnAction((e) -> listener.accept(new ActionEvent(e)));
        } else {
            node.setOnMouseClicked((e) -> {
                if (e.getClickCount() > 1) {
                    listener.accept(new ActionEvent(e));
                }
            });
        }
    }

    @Override
    public Class<Node> getBasicViewClass() {
        return Node.class;
    }

}
