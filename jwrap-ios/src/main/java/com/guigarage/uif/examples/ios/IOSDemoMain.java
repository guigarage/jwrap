package com.guigarage.uif.examples.ios;

import com.android.okhttp.HttpHandler;
import com.android.org.conscrypt.OpenSSLSocketFactoryImpl;
import com.guigarage.uif.concurrent.UIToolkit;
import com.guigarage.uif.exception.ToolkitException;
import com.guigarage.uif.mvc.MVCGroup;
import com.guigarage.uif.toolkit.ios.IOSPlatform;
import com.guigarage.uif.toolkit.ios.IOSViewHandler;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.*;

public class IOSDemoMain extends UIApplicationDelegateAdapter {

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {

        //TODO: Hack damit Class.forName() funktioniert
        IOSDemoView.class.getName();
        HttpHandler.class.getName();
        OpenSSLSocketFactoryImpl.class.getName();

        UIToolkit.setPlatform(new IOSPlatform());
        MVCGroup<IOSDemoController, UIView> group = new MVCGroup<>(IOSDemoController.class, new IOSViewHandler());
        try {
            group.create();
        } catch (ToolkitException e) {
            e.printStackTrace();
        }
        UIWindow window = new UIWindow(UIScreen.getMainScreen().getBounds());
        window.setBackgroundColor(UIColor.lightGray());
        window.addSubview(group.getView());
        group.getView().setFrame(UIScreen.getMainScreen().getBounds());
        window.makeKeyAndVisible();

        return true;
    }

    public static void main(String[] args) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(args, null, IOSDemoMain.class);
        pool.close();
    }
}