package com.guigarage.uif.toolkit.swing.example;

import com.guigarage.uif.concurrent.UIToolkit;
import com.guigarage.uif.exception.ToolkitException;
import com.guigarage.uif.mvc.MVCGroup;
import com.guigarage.uif.toolkit.swing.SwingPlatform;
import com.guigarage.uif.toolkit.swing.SwingViewHandler;

import javax.swing.*;

public class SwingDemoMain extends JFrame {

    public SwingDemoMain() {
        MVCGroup group = new MVCGroup(SwingDemoController.class, new SwingViewHandler());
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
