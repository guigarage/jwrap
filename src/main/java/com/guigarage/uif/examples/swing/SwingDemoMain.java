package com.guigarage.uif.examples.swing;

import com.guigarage.uif.concurrent.UIToolkit;
import com.guigarage.uif.exception.ToolkitException;
import com.guigarage.uif.mvc.MVCGroup;
import com.guigarage.uif.toolkits.swing.SwingPlatform;

import javax.swing.*;

public class SwingDemoMain extends JFrame {

    public SwingDemoMain() {
        MVCGroup group = new MVCGroup(SwingDemoController.class);
        try {
            group.create();
            setContentPane((java.awt.Container) group.getView());
        } catch (ToolkitException e) {
            e.printStackTrace();
        }
        pack();
        setVisible(true);
    }

    public static void main(String... args) {
        UIToolkit.setPlatform(SwingPlatform.getInstance());
        UIToolkit.runLater(() -> new SwingDemoMain());
    }

}
