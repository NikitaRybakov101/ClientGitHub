package com.example.clientgithub.retrofit;

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
    @Headers({
            "Accept: application/vnd.github+json",
            "X-GitHub-Api-Version: 2022-11-28"
    })
    @GET("/user/repos")
    Single<List<ResponseRepositoryDto>> getListRepository(@Header("Authorization") String token);

    @Headers({
            "Accept: application/vnd.github+json",
            "X-GitHub-Api-Version: 2022-11-28"
    })
    @GET("/user")
    Single<ResponseUserDto> getUser(@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @POST("https://github.com/login/device/code")
    Single<ResponseCodeDto> getCode(@Query("client_id") String clientId);

    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    Single<ResponseTokenDto> getToken(
            @Query("client_id") String clientId,
            @Query("device_code") String device_code,
            @Query("grant_type") String grant_type
    );
}


