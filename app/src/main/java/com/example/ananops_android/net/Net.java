package com.example.ananops_android.net;

import com.example.ananops_android.db.LoginRequest;
import com.example.ananops_android.db.LoginResponse;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.db.OrderResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface Net {
    public static String base_url = "http://111.206.133.26:8099";
    public static String base_url1="http://10.112.9.107:8300";
    Net instance = new Retrofit.Builder()
            .baseUrl(base_url1)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(Net.class);

    @POST("/cuvrbt/login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/ananops/api/v1/dmc/getTaskListByStatus")
    Observable<OrderResponse> login1(@Query("status") int status );
}
