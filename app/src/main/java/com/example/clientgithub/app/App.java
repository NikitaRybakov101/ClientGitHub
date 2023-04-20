package com.example.clientgithub.app;

import android.app.Application;

import com.example.clientgithub.di.DaggerInterfaceAppComponent;
import com.example.clientgithub.di.modules.AppModule;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerInterfaceAppComponent.builder()
                .application(this)
                .appModule(new AppModule(this))
                .build()
                .inject(this);
    }
}