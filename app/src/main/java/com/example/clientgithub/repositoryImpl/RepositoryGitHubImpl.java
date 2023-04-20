package com.example.clientgithub.repositoryImpl;

import com.example.clientgithub.dataSource.authenticationSource.Code;
import com.example.clientgithub.dataSource.authenticationSource.ResponseCodeDto;
import com.example.clientgithub.dataSource.authenticationSource.ResponseTokenDto;
import com.example.clientgithub.dataSource.authenticationSource.Token;
import com.example.clientgithub.dataSource.dataRepositorySource.Owner;
import com.example.clientgithub.dataSource.dataRepositorySource.Repository;
import com.example.clientgithub.dataSource.dataRepositorySource.ResponseRepositoryDto;
import com.example.clientgithub.dataSource.dataUserSource.ResponseUserDto;
import com.example.clientgithub.dataSource.dataUserSource.User;
import com.example.clientgithub.retrofit.gitHubApi.ServiceGitHubAPI;
import com.example.clientgithub.retrofit.gitHubAuthentication.ServiceGitHubAuthentication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Single;

public class RepositoryGitHubImpl implements InterfaceRepository {

    private final ServiceGitHubAPI serviceGitHubAPI;
    private final ServiceGitHubAuthentication serviceGitHubAuthAPI;

    public RepositoryGitHubImpl(ServiceGitHubAPI serviceGitHubAPI, ServiceGitHubAuthentication serviceGitHubAuthAPI) {
        this.serviceGitHubAPI = serviceGitHubAPI;
        this.serviceGitHubAuthAPI = serviceGitHubAuthAPI;
    }

    @Override
    public Single<List<ResponseRepositoryDto>> getListPoison(String token) {
        return serviceGitHubAPI.getListRepository(token);
    }

    @Override
    public Single<ResponseUserDto> getUser(String token) {
        return serviceGitHubAPI.getUser(token);
    }

    @Override
    public Single<ResponseCodeDto> getCode(String clientId) {
        return serviceGitHubAuthAPI.getCode(clientId);
    }

    @Override
    public Single<ResponseTokenDto> getToken(String clientId, String deviceCode, String grantType) {
        return serviceGitHubAuthAPI.getToken(clientId, deviceCode, grantType);
    }

    @Override
    public List<Repository> mapResponseToRepository(List<ResponseRepositoryDto> responseRepositoryDto) {

        if(responseRepositoryDto == null) {
            return new ArrayList<>();
        } else {
            return responseRepositoryDto
                    .stream()
                    .map( repoDto -> new Repository(
                            notNullVal(repoDto.getNameRepository()) ,
                            notNullVal(repoDto.getFullName()) ,

                            repoDto.getOwner() != null ? new Owner(
                                    notNullVal(repoDto.getOwner().getLogin()),
                                    notNullVal(repoDto.getOwner().getAvatarUrl())

                            ) : new Owner("null","null"),

                            notNullVal(repoDto.getDescription()),
                            notNullVal(repoDto.getForks_count()),
                            notNullVal(repoDto.getWatchers_count()),
                            notNullVal(repoDto.getVisibility())
                    ))
                    .collect(Collectors.toList());
        }
    }
    @Override
    public User mapResponseToUser(ResponseUserDto responseUserDto) {

        if(responseUserDto == null) {
            return new User("null","null");
        } else {
            return new User(
                    notNullVal(responseUserDto.getAvatarUrl()),
                    notNullVal( responseUserDto.getLogin())
            );
        }
    }

    @Override
    public Code mapResponseCodeToCode(ResponseCodeDto responseCodeDto) {
        return new Code(
                notNullVal(responseCodeDto.getUser_code()),
                notNullVal(responseCodeDto.getVerification_uri()),
                notNullVal(responseCodeDto.getDevice_code())
        );
    }

    @Override
    public Token mapResponseTokenToToken(ResponseTokenDto responseTokenDto) {
        return new Token(
                notNullVal(responseTokenDto.getAccess_token()),
                notNullVal(responseTokenDto.getToken_type())
        );
    }

    private String notNullVal(String value) {
        return value != null ? value : "null";
    }
}

