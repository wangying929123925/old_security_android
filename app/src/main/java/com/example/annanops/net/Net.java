package com.example.annanops.net;

import com.example.annanops.db.LoginRequest;
import com.example.annanops.db.LoginResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


public interface Net {
    public static String base_url = "http://111.206.133.26:8099";

    Net instance = new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(Net.class);

    @POST("/cuvrbt/login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/cuvrbt/login1")
    Observable<LoginResponse> login1(@Body LoginRequest loginRequest);
}
