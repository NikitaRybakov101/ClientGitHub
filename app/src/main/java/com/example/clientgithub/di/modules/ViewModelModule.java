package com.example.clientgithub.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.clientgithub.ui.viewModel.ViewModelFragmentAuthentication;
import com.example.clientgithub.ui.viewModel.ViewModelFragmentCommitsView;
import com.example.clientgithub.ui.viewModel.ViewModelFragmentRepositoryView;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(includes = {NetworkModules.class})
abstract class ViewModelModule {

    @Binds
    protected abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    abstract Map<Class<? extends ViewModel>, Provider<ViewModel>> bindViewModels(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap);

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFragmentRepositoryView.class)
    abstract ViewModel fragmentViewModelRepositoryView(ViewModelFragmentRepositoryView viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFragmentAuthentication.class)
    abstract ViewModel fragmentViewModelFragmentAuthentication(ViewModelFragmentAuthentication viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFragmentCommitsView.class)
    abstract ViewModel fragmentViewModelFragmentCommitsView(ViewModelFragmentCommitsView viewModel);

    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    public @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }
}

