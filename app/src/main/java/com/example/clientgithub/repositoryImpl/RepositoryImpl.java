package com.example.clientgithub.repositoryImpl;

import com.example.clientgithub.dataSource.ResponseMy;
import com.example.clientgithub.retrofit.InterfaceRetrofit;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class RepositoryImpl implements InterfaceRepository {

    private final InterfaceRetrofit retrofitInterface;

    public RepositoryImpl(InterfaceRetrofit interfaceRetrofit) {
        this.retrofitInterface = interfaceRetrofit;
    }

    @Override
    public List<ResponseMy> getListPoison(String search, int offset, int pageSize) throws IOException {
        Response<List<ResponseMy>> responseListPoison = retrofitInterface.getListPoison(search,offset,pageSize).execute();

        if (responseListPoison.isSuccessful() && responseListPoison.body() != null) {
           return responseListPoison.body();
        } else {
           return null;
        }
    }

    @Override
    public ResponseMy getPoison(String idPoison) throws IOException {
        Response<ResponseMy> responsePoison = retrofitInterface.getPoison(idPoison).execute();

         if (responsePoison.isSuccessful() && responsePoison.body() != null) {
            return responsePoison.body();
        } else {
             return null;
        }
    }

}
