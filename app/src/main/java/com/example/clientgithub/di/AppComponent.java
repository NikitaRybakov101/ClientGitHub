package com.example.clientgithub.di;

import android.app.Application;

import com.example.clientgithub.app.App;
import com.example.clientgithub.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = { AndroidSupportInjectionModule.class, AndroidInjectionModule.class, AppModule.class})
@Singleton
public interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application app);

        Builder appModule(AppModule appModule);

        AppComponent build();
    }
    void inject(App app);
}

