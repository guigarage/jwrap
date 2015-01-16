package com.guigarage.uif.toolkit.swing.example;

import com.guigarage.uif.concurrent.ProcessChain;
import com.guigarage.uif.delegates.Button;
import com.guigarage.uif.delegates.Label;
import com.guigarage.uif.delegates.TextField;
import com.guigarage.uif.mvc.ActionMethod;
import com.guigarage.uif.mvc.ActionTrigger;
import com.guigarage.uif.mvc.ViewNode;

import javax.annotation.PostConstruct;

public class SwingDemoController {

    @ViewNode
    @ActionTrigger("copy-action")
    private Button myButton;

    @ViewNode
    private TextField myTextField;

    @ViewNode
    private Label myLabel;

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