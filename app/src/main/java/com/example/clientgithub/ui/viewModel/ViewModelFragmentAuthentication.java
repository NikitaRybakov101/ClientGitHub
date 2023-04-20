package com.example.clientgithub.ui.viewModel;

import static com.example.clientgithub.ui.viewModel.dataSourse.StateDataConst.AUTHORIZATION_ERROR;
import static com.example.clientgithub.ui.viewModel.dataSourse.StateDataConst.LOADING;
import static com.example.clientgithub.ui.viewModel.dataSourse.StateDataConst.NETWORK_ERROR;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.clientgithub.repositoryImpl.RepositoryGitHubImpl;
import com.example.clientgithub.retrofit.gitHubApi.NetworkRetrofit;
import com.example.clientgithub.retrofit.gitHubAuthentication.NetworkRetrofitAuth;
import com.example.clientgithub.ui.viewModel.dataSourse.StateData;
import com.example.clientgithub.ui.viewModel.interfacesViewModel.InterfaceFragmentViewModelAuthentication;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewModelFragmentAuthentication extends ViewModel implements InterfaceFragmentViewModelAuthentication {
    private final MutableLiveData<StateData> liveData = new MutableLiveData<>();
    private final RepositoryGitHubImpl repository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ViewModelFragmentAuthentication(RepositoryGitHubImpl repository) {
        this.repository = repository;
    }
    @Override
    public MutableLiveData<StateData> getLiveData() {
        return liveData;
    }

    @Override
    public void getCode(String clientId) {

        try {
            liveData.setValue(new StateData.Loading(LOADING));

            Disposable disposable = repository.getCode(clientId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(repository::mapResponseCodeToCode)
                    .subscribe(
                            result -> {
                                liveData.setValue(new StateData.SuccessCode(result));
                            },
                            error -> {
                                liveData.setValue(new StateData.Error(NETWORK_ERROR));
                            }
                    );
            compositeDisposable.add(disposable);

        } catch (Exception exception) {
            liveData.setValue(new StateData.Error(NETWORK_ERROR));
        }
    }

    @Override
    public void getToken(String clientId, String deviceCode, String grantType) {

        try {

            Disposable disposable = repository.getToken(clientId,deviceCode,grantType)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(repository::mapResponseTokenToToken)
                    .subscribe(
                            result -> {
                                liveData.setValue(new StateData.SuccessToken(result));
                            },
                            error -> {
                                liveData.setValue(new StateData.Error(AUTHORIZATION_ERROR));
                            }
                    );
            compositeDisposable.add(disposable);

        } catch (Exception exception) {
            liveData.setValue(new StateData.Error(NETWORK_ERROR));
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
