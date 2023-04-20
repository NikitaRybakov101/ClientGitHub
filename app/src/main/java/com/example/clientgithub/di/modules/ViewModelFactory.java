package com.example.clientgithub.di.modules;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import java.util.Map;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mViewModels;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels) {
        mViewModels = viewModels;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> viewModelProvider = mViewModels.get(modelClass);
        if (viewModelProvider == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : mViewModels.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    viewModelProvider = entry.getValue();
                    break;
                }
            }
        }
        if (viewModelProvider == null) {
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass);
        }
        try {
            return (T) viewModelProvider.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
