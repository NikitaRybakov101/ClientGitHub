package com.example.clientgithub.ui.viewModel.interfacesViewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.clientgithub.ui.viewModel.dataSourse.StateData;

public interface FragmentInterfaceViewModelAuthentication {

    MutableLiveData<StateData> getLiveData();
    void getCode(String clientId);
    void getToken(String clientId, String deviceCode, String grantType);
}
