package com.example.clientgithub.di.modules;

import com.example.clientgithub.repositoryImpl.RepositoryGitHubImpl;
import com.example.clientgithub.retrofit.gitHubApi.NetworkRetrofit;
import com.example.clientgithub.retrofit.gitHubApi.ServiceGitHubAPI;
import com.example.clientgithub.retrofit.gitHubAuthentication.NetworkRetrofitAuth;
import com.example.clientgithub.retrofit.gitHubAuthentication.ServiceGitHubAuthentication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModules {

    @Provides
    @Singleton
    public ServiceGitHubAPI getServiceGitHubAPI() {
        return new NetworkRetrofit().getRetrofit();
    }

    @Provides
    @Singleton
    public ServiceGitHubAuthentication getServiceGitHubAuthentication() {
        return new NetworkRetrofitAuth().getRetrofit();
    }

    @Provides
    @Singleton
    public RepositoryGitHubImpl getRepositoryGitHubImpl(ServiceGitHubAuthentication serviceGitHubAuthentication, ServiceGitHubAPI serviceGitHubAPI) {
        return new RepositoryGitHubImpl(serviceGitHubAPI,serviceGitHubAuthentication);
    }

}
