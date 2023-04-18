package com.example.clientgithub.ui.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.clientgithub.ui.viewModel.dataSourse.StateData;
import com.example.clientgithub.ui.viewModel.interfacesViewModel.InterfaceViewModel;

public class ViewModel implements InterfaceViewModel {
    private final MutableLiveData<StateData> liveData = new MutableLiveData<>();


    public void send() {



    }


    @Override
    public MutableLiveData<StateData> getLiveData() {
        return liveData;
    }
}
