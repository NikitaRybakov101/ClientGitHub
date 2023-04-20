package com.example.clientgithub.ui.viewModel.interfacesViewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.clientgithub.ui.viewModel.dataSourse.StateData;

public interface FragmentInterfaceViewModelRepositoryView {
    MutableLiveData<StateData> getLiveData();
    void getListRepository(String token);
    void getUser(String token);
}
