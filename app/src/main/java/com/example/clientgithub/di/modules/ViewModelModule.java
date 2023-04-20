package com.example.clientgithub.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.clientgithub.ui.viewModel.FragmentViewModelRepositoryView;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(includes = {NetworkModules.class})
abstract class ViewModelModule {

    @Binds
    protected abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @ViewModelKey(FragmentViewModelRepositoryView.class)
    @IntoMap
    protected abstract FragmentViewModelRepositoryView fragmentViewModelRepositoryView(FragmentViewModelRepositoryView fragmentViewModelRepositoryView);

    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    public @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }
}
