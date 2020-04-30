package com.jegan.androidtutz.contactmanager;

import android.app.Application;

import com.jegan.androidtutz.contactmanager.di.ApplicationModule;
import com.jegan.androidtutz.contactmanager.di.ContactAppComponent;
import com.jegan.androidtutz.contactmanager.di.DaggerContactAppComponent;


public class App extends Application {
    private static App app;
    private ContactAppComponent contactAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        contactAppComponent = DaggerContactAppComponent.builder()
        .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static App getApp() {
        return app;
    }

    public ContactAppComponent getContactAppComponent() {
        return contactAppComponent;
    }
}
