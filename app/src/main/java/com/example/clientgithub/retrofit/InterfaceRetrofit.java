package com.example.clientgithub.retrofit;

import com.example.clientgithub.dataSource.ResponseMy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceRetrofit {
    @GET("/api/ppp/item/")
    Call<ResponseMy> getPoison(@Query("id") String id);

    @GET("/api/ppp/index/")
    Call<List<ResponseMy>> getListPoison(
            @Query("search") String search,
            @Query("offset") int offset,
            @Query("limit") int limit
    );
}
