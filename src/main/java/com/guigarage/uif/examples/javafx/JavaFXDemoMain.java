package com.guigarage.uif.examples.javafx;

import com.guigarage.uif.concurrent.UIToolkit;
import com.guigarage.uif.exception.ToolkitException;
import com.guigarage.uif.mvc.MVCGroup;
import com.guigarage.uif.toolkits.javafx.JavaFXPlatform;
import com.guigarage.uif.toolkits.javafx.JavaFXViewHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by hendrikebbers on 25.10.14.
 */
public class JavaFXDemoMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        UIToolkit.setPlatform(JavaFXPlatform.getInstance());

        MVCGroup group = new MVCGroup(JavaFXDemoController.class, new JavaFXViewHandler());
        try {
            group.create();
            primaryStage.setScene(new Scene((javafx.scene.Parent) group.getView()));
        } catch (ToolkitException e) {
            e.printStackTrace();
        }
        primaryStage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
