package com.guigarage.uif.examples.swing;

import javax.swing.*;
import java.awt.*;

public class SwingDemoView extends JPanel {

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
}
