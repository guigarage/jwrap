package com.guigarage.uif.mvc;

import com.guigarage.uif.concurrent.Async;
import com.guigarage.uif.concurrent.ProcessChain;
import com.guigarage.uif.exception.DefaultExceptionHandler;
import com.guigarage.uif.exception.ExceptionHandler;
import com.guigarage.uif.exception.ToolkitException;
import com.guigarage.uif.toolkits.swing.SwingViewHandler;
import com.guigarage.uif.util.AccessControllerUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;

public class MVCGroup<C, V> {

    private PlatformViewHandler viewHandler;

    private V view;

    private Class<C> controllerClass;

    private C controller;

    private ExceptionHandler exceptionHandler;

    public MVCGroup(Class<C> controllerClass) {
        this(controllerClass, new SwingViewHandler());
    }

    public MVCGroup(Class<C> controllerClass, PlatformViewHandler viewHandler) {
        this.controllerClass = controllerClass;
        exceptionHandler = DefaultExceptionHandler.getDefaultInstance();
        this.viewHandler = viewHandler;
    }

    public void create() throws ToolkitException {
        try {
            controller = controllerClass.newInstance();
            createView();
            injectViewNodes();
            createActionBinding();
            callPostConstruct();
        } catch (Exception e) {
            throw new ToolkitException(e);
        }
    }

    private void createActionBinding() {
        for (Method method : AccessControllerUtils.getInheritedDeclaredMethods(controllerClass)) {
            if (method.isAnnotationPresent(ActionMethod.class)) {
                ActionMethod actionAnnotation = method.getAnnotation(ActionMethod.class);
                Consumer<ActionEvent> actionLister = null;
                if (method.isAnnotationPresent(Async.class)) {
                    actionLister = a -> {
                        ProcessChain.create().addRunnableInExecutor(() -> {
                            AccessControllerUtils.callPrivileged(method, controller);
                        }).onException(exceptionHandler).run();
                    };
                } else {
                    actionLister = (a) -> {
                        AccessControllerUtils.callPrivileged(method, controller);
                    };
                }
                registerActionListener(actionLister, actionAnnotation.value());
            }
        }
    }

    public void registerActionListener(Consumer<ActionEvent> listener, String actionId) {
        viewHandler.registerActionListener(listener, actionId, controller);
    }

    private void createView() throws ToolkitException {
        view = (V) viewHandler.createView(controllerClass);
    }

    private void callPostConstruct() throws InvocationTargetException, IllegalAccessException {
        for (final Method method : controllerClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(controller);
            }
        }
    }

    public Object findViewNode(String id) {
        return viewHandler.findChildById(view, id);
    }

    private void injectViewNodes() {
        List<Field> fields = AccessControllerUtils.getInheritedDeclaredFields(controllerClass);
        for (Field field : fields) {
            if (field.getAnnotation(ViewNode.class) != null) {
                String id = field.getName();
                if (field.getAnnotation(ViewNode.class).value() != null && !field.getAnnotation(ViewNode.class).value().isEmpty()) {
                    id = field.getAnnotation(ViewNode.class).value();
                }
                if (AccessControllerUtils.getPrivileged(field, controller) == null) {
                    if (viewHandler.isViewNodeType(field.getType())) {
                        Object toInject = findViewNode(id);
                        if (toInject != null) {
                            AccessControllerUtils.setPrivileged(field, controller, toInject);
                        }
                    }
                }
            }
        }
    }

    public V getView() {
        return view;
    }

    public C getController() {
        return controller;
    }
}
