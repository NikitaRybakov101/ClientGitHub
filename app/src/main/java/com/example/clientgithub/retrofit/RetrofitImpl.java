package com.example.clientgithub.retrofit;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImpl {
    private static final String BASE_URL_API = "url";

    public InterfaceRetrofit getRetrofit() {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        return retrofit.create(InterfaceRetrofit.class);
    }
}
