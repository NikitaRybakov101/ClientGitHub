package com.example.clientgithub.ui.viewModel;

import static com.example.clientgithub.ui.viewModel.dataSourse.StateDataConst.LOADING;
import static com.example.clientgithub.ui.viewModel.dataSourse.StateDataConst.NETWORK_ERROR;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.clientgithub.repositoryImpl.RepositoryGitHubImpl;
import com.example.clientgithub.retrofit.gitHubApi.NetworkRetrofit;
import com.example.clientgithub.retrofit.gitHubAuthentication.NetworkRetrofitAuth;
import com.example.clientgithub.ui.viewModel.dataSourse.StateData;
import com.example.clientgithub.ui.viewModel.interfacesViewModel.FragmentInterfaceViewModelRepositoryView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FragmentViewModelRepositoryView extends ViewModel implements FragmentInterfaceViewModelRepositoryView {
    private final MutableLiveData<StateData> liveData = new MutableLiveData<>();
    private final RepositoryGitHubImpl repository = new RepositoryGitHubImpl(new NetworkRetrofit().getRetrofit(),new NetworkRetrofitAuth().getRetrofit());
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    public MutableLiveData<StateData> getLiveData() {
        return liveData;
    }
    @Override
    public void getListRepository(String token) {

        try {
            liveData.setValue(new StateData.Loading(LOADING));

            Disposable disposable = repository.getListPoison(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(repository::mapResponseToRepository)
                    .subscribe(
                            result -> {
                                liveData.setValue(new StateData.SuccessRepository(result));
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
    public void getUser(String token) {

        try {
            liveData.setValue(new StateData.Loading(LOADING));

            Disposable disposable = repository.getUser(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(repository::mapResponseToUser)
                    .subscribe(
                            result -> {
                                liveData.setValue(new StateData.SuccessUser(result));
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
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
