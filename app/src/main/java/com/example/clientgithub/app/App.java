package com.example.clientgithub.app;
import android.app.Application;

import com.example.clientgithub.di.DaggerAppComponent;
import com.example.clientgithub.di.AppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;


public class App extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDagger();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    private void setupDagger() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);
    }
}