package com.example.clientgithub.ui.viewModel.interfacesViewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.clientgithub.ui.viewModel.dataSourse.StateData;

public interface InterfaceViewModel {
    MutableLiveData<StateData> getLiveData();
}
