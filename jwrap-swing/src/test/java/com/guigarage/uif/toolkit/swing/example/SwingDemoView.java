package com.guigarage.uif.toolkit.swing.example;

import com.guigarage.uif.delegates.api.ViewDelegate;
import com.guigarage.uif.delegates.api.ViewNodeDelegate;

import javax.swing.*;
import java.awt.*;

public class SwingDemoView extends JPanel implements ViewDelegate<Container> {

    public SwingDemoView() {
        setLayout(new BorderLayout());

        JButton myButton = new JButton("Get weather by city");
        myButton.setName("myButton");

        JTextField myTextField = new JTextField();
        myTextField.setName("myTextField");

        JLabel myLabel = new JLabel("Result...");
        myLabel.setName("myLabel");

        add(myTextField, BorderLayout.NORTH);
        add(myButton, BorderLayout.CENTER);
        add(myLabel, BorderLayout.SOUTH);
    }

    @Override
    public Container getRootNode() {
        return this;
    }

    @Override
    public <T extends ViewNodeDelegate<Container>> T getViewNode(String id) {
        return null;
    }
}
