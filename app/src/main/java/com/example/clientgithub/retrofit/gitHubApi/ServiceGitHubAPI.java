package com.example.clientgithub.retrofit.gitHubApi;

import com.example.clientgithub.dataSource.authenticationSource.ResponseCodeDto;
import com.example.clientgithub.dataSource.authenticationSource.ResponseTokenDto;
import com.example.clientgithub.dataSource.dataRepositorySource.ResponseRepositoryDto;
import com.example.clientgithub.dataSource.dataUserSource.ResponseUserDto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceGitHubAPI {
    @GET("/user/repos")
    Single<List<ResponseRepositoryDto>> getListRepository(@Header("Authorization") String token);

    @GET("/user")
    Single<ResponseUserDto> getUser(@Header("Authorization") String token);
}


