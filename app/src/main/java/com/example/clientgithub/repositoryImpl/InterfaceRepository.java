package com.example.clientgithub.repositoryImpl;

import com.example.clientgithub.dataSource.authenticationSource.Code;
import com.example.clientgithub.dataSource.authenticationSource.ResponseCodeDto;
import com.example.clientgithub.dataSource.authenticationSource.ResponseTokenDto;
import com.example.clientgithub.dataSource.authenticationSource.Token;
import com.example.clientgithub.dataSource.commitsSource.Commit;
import com.example.clientgithub.dataSource.commitsSource.ResponseCommitDto;
import com.example.clientgithub.dataSource.dataRepositorySource.Repository;
import com.example.clientgithub.dataSource.dataRepositorySource.ResponseRepositoryDto;
import com.example.clientgithub.dataSource.dataUserSource.ResponseUserDto;
import com.example.clientgithub.dataSource.dataUserSource.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface InterfaceRepository {
    Single<List<ResponseRepositoryDto>> getListRepositories(String token);
    Single<ResponseUserDto> getUser(String token);
    Single<ResponseCodeDto> getCode(String client_id);

    Single<ResponseTokenDto> getToken(String clientId, String deviceCode, String grantType);

    Single<List<ResponseCommitDto>> getListCommits(String owner, String token, String nameRepo);

    List<Repository> mapResponseToRepository(List<ResponseRepositoryDto> responseRepositoryDto);

    User mapResponseToUser(ResponseUserDto responseUserDto);

    Code mapResponseCodeToCode(ResponseCodeDto responseCodeDto);

    Token mapResponseTokenToToken(ResponseTokenDto responseTokenDto);

    List<Commit> mapResponseCommitToCommit(List<ResponseCommitDto> responseCommitDtoList);
}
