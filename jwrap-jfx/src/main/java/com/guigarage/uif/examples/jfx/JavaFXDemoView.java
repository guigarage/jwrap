package com.guigarage.uif.examples.jfx;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class JavaFXDemoView extends VBox {

    public JavaFXDemoView() {
        setSpacing(12);
        setPadding(new Insets(12));

        Button myButton = new Button("Get weather by city");
        myButton.setId("myButton");

        TextField myTextField = new TextField();
        myTextField.setId("myTextField");

        Label myLabel = new Label("Result...");
        myLabel.setId("myLabel");

        getChildren().addAll(myTextField, myButton, myLabel);

    }
}
