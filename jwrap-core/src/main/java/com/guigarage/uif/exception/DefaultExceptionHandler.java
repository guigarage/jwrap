package com.guigarage.uif.exception;

import com.guigarage.uif.concurrent.UIToolkit;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultExceptionHandler implements ExceptionHandler {

    private Throwable exception;

    private static final Logger LOGGER = Logger.getLogger(DefaultExceptionHandler.class.getName());

    private static DefaultExceptionHandler defaultInstance;

    public DefaultExceptionHandler() {
    }

    public static synchronized DefaultExceptionHandler getDefaultInstance() {
        if (defaultInstance == null) {
            defaultInstance = new DefaultExceptionHandler();
        }
        return defaultInstance;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        if (UIToolkit.isToolkitThread()) {
            this.exception = exception;
        } else {
            try {
                UIToolkit.runAndWait(() -> this.exception = exception);
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.log(Level.SEVERE, "Can't handle exception in JavaFX Application Thread!", e);
                LOGGER.log(Level.SEVERE, "Initial exception: ", exception);
            }
        }
    }
}
