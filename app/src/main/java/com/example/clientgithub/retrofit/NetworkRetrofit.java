package com.example.clientgithub.retrofit;

import com.example.clientgithub.BuildConfig;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRetrofit {
    private static final String BASE_URL_API = "https://api.github.com";

    public ServiceGitHubAPI getRetrofit() {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL_API)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .client(getHttpClient())
                .build();

        return retrofit.create(ServiceGitHubAPI.class);
    }

    private static final long TIME_OUT_CONNECT = 10L;

    public static OkHttpClient getHttpClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS);
        httpClient.readTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS);

        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        httpClient.addInterceptor(chain -> {

            Request original = chain.request();
            Request.Builder request = original.newBuilder();

            request
                    //.header("Authorization", "Token " + token)
                    .header("User-Agent", "Android/" + BuildConfig.APPLICATION_ID + "/" + BuildConfig.VERSION_CODE + "/" + BuildConfig.VERSION_NAME);



            Request builder = request.method(original.method(), original.body()).build();
            return chain.proceed(builder);
        });

        return httpClient.build();
    }
}
