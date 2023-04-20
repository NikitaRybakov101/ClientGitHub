package com.example.clientgithub.retrofit.gitHubApi;

import com.example.clientgithub.dataSource.commitsSource.ResponseCommitDto;
import com.example.clientgithub.dataSource.dataRepositorySource.ResponseRepositoryDto;
import com.example.clientgithub.dataSource.dataUserSource.ResponseUserDto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ServiceGitHubAPI {
    @GET("/user/repos")
    Single<List<ResponseRepositoryDto>> getListRepository(@Header("Authorization") String token);

    @GET("/user")
    Single<ResponseUserDto> getUser(@Header("Authorization") String token);

    @GET("/repos/{owner}/{repo}/commits")
    Single<List<ResponseCommitDto>> getListCommits(
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Header("Authorization") String token
    );

}


