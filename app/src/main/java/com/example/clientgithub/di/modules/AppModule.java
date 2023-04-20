package com.example.clientgithub.di.modules;

import android.content.Context;

import com.example.clientgithub.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModules.class,ViewModelModule.class})
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public Context app() {
        return app;
    }
}
