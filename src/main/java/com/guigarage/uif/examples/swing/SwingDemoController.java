package com.guigarage.uif.examples.swing;

import com.guigarage.uif.concurrent.ProcessChain;
import com.guigarage.uif.examples.service.WeatherService;
import com.guigarage.uif.mvc.ActionMethod;
import com.guigarage.uif.mvc.ActionTrigger;
import com.guigarage.uif.mvc.ViewNode;

import javax.annotation.PostConstruct;
import javax.swing.*;

public class SwingDemoController {

    @ViewNode
    @ActionTrigger("copy-action")
    private JButton myButton;

    @ViewNode
    private JTextField myTextField;

    @ViewNode
    private JLabel myLabel;

    @ActionMethod("copy-action")
    private void copy() {
        ProcessChain.create().
                addSupplierInPlatformThread(() -> myTextField.getText()).
                addFunctionInExecutor((t) -> WeatherService.getWeather(t)).
                addConsumerInPlatformThread((t) -> myLabel.setText(t)).onException((e) -> {
            myLabel.setText("Error");
            e.printStackTrace();
        }).run();
    }

    @PostConstruct
    private void init() {
        System.out.println("TADA");
    }
}
