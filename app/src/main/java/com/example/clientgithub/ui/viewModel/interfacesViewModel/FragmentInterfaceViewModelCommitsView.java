package com.example.clientgithub.ui.viewModel.interfacesViewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.clientgithub.ui.viewModel.dataSourse.StateData;

public interface FragmentInterfaceViewModelCommitsView {
    MutableLiveData<StateData> getLiveData();

    void getListCommits(String owner, String token, String nameRepo);
}
