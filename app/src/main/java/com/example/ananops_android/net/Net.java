package com.example.ananops_android.net;

import com.alibaba.idst.nls.nlsclientsdk.requests.Constant;
import com.example.ananops_android.db.LoginRequest;
import com.example.ananops_android.db.LoginResponse;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.db.OrderResponse;
import com.example.ananops_android.db.PostResponse;
import com.example.ananops_android.db.UserInfo;
import com.example.ananops_android.db.UserInformation;
import com.example.ananops_android.entity.RepairAddContent;
import com.example.ananops_android.entity.UserLogin;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface Net {
    public static String base_url = "http://123.56.250.169:29995";//角色
    public static String base_url1="http://10.28.219.121:7979";//工单
    Net instance = new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(Net.class);
    Net instance1 = new Retrofit.Builder()
            .baseUrl(base_url1)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(Net.class);
    //登录
    @FormUrlEncoded
    @Headers({"Authorization: Basic YW5hbm9wcy1jbGllbnQtdWFjOmFuYW5vcHNDbGllbnRTZWNyZXQ=","Content-Type: application/x-www-form-urlencoded"})
    @POST("/uac/auth/form")
    Observable<LoginResponse> login(@Field("username")String username,
                                    @Field("password")String password,
                                    @Field("imageCode")String imageCode,
                                    @Header("deviceId")Long deviceId
                                   );

    //获取用户信息
    @POST("/uac/user/queryUserRoleInfo/{loginName}")
    Observable<UserInformation>getUserInfo(@Path("loginName")String loginName, @Header("Authorization")String postToken);

    //获取工单列表
    @GET("/ananops/api/v1/dmc/getTaskListByClassify")
    Observable<OrderResponse> getRepairList(@Query("queryDto") OrderRequest queryDto,@Header("Authorization")String postToken);
    //工单填写提交
    @Headers("Content-Type:application/json")
    @POST("/maintenanceTask/submitTask")
    Observable<PostResponse> repairAddPost(@Body RepairAddContent orderDto,@Header("Authorization")String postToken);

    //获取图片
   @POST("/uac/auth/code/image")
    Observable<PostResponse>getImage(@Header("deviceId")Long deviceId);
    //获取工单进度

    //获取工单详情

    //获取维修详情

    //获取备件信息

    //获取审核信息
}
