package com.guigarage.uif.examples.ios;

import com.guigarage.uif.concurrent.ProcessChain;
import com.guigarage.uif.delegates.Button;
import com.guigarage.uif.delegates.Label;
import com.guigarage.uif.delegates.TextField;
import com.guigarage.uif.mvc.ActionMethod;
import com.guigarage.uif.mvc.ActionTrigger;
import com.guigarage.uif.mvc.ViewNode;
import org.robovm.apple.foundation.Foundation;

import javax.annotation.PostConstruct;

/**
 * Created by hendrikebbers on 15.01.15.
 */
public class IOSDemoController {

    @ViewNode
    @ActionTrigger("copy-action")
    private Button myButton;

    @ViewNode
    private TextField myTextField;

    @ViewNode
    private Label myLabel;

    @ActionMethod("copy-action")
    private void copy() {
        Foundation.log("Action");
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
        Foundation.log("INIT");
    }
}
