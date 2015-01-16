package com.guigarage.uif.examples.ios;

import com.guigarage.uif.toolkit.ios.IOSViewNodeRegistry;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.uikit.*;

/**
 * Created by hendrikebbers on 15.01.15.
 */
public class IOSDemoView extends UIView {

    public IOSDemoView() {
        Foundation.log("Creating View...");

        UITextField textfield = new UITextField();
        textfield.setFrame(new CGRect(65.0f, 20.0f, 191.0f, 37.0f));
        textfield.setBackgroundColor(UIColor.white());
        addSubview(textfield);
        IOSViewNodeRegistry.getInstance().register(this, textfield, "myTextField");

        UIButton button = UIButton.create(UIButtonType.RoundedRect);
        button.setBackgroundColor(UIColor.white());
        button.setFrame(new CGRect(115.0f, 80.0f, 91.0f, 37.0f));
        button.setTitle("Click me!", UIControlState.Normal);
        addSubview(button);
        IOSViewNodeRegistry.getInstance().register(this, button, "myButton");

        UILabel label = new UILabel();
        label.setTextAlignment(NSTextAlignment.Center);
        label.setFrame(new CGRect(65.0f, 140.0f, 191.0f, 37.0f));
        addSubview(label);
        IOSViewNodeRegistry.getInstance().register(this, label, "myLabel");
    }
}
