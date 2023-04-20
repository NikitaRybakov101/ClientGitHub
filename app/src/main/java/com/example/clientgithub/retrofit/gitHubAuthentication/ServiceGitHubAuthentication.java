package com.example.clientgithub.retrofit.gitHubAuthentication;

import com.example.clientgithub.dataSource.authenticationSource.ResponseCodeDto;
import com.example.clientgithub.dataSource.authenticationSource.ResponseTokenDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceGitHubAuthentication {

    @POST("/login/device/code")
    Single<ResponseCodeDto> getCode(@Query("client_id") String clientId);

    @POST("/login/oauth/access_token")
    Single<ResponseTokenDto> getToken(
            @Query("client_id") String clientId,
            @Query("device_code") String device_code,
            @Query("grant_type") String grant_type
    );
}


