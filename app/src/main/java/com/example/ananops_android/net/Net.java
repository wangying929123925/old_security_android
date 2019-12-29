package com.example.ananops_android.net;

import com.example.ananops_android.db.ChangeStatusDto;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.InspectionItemListResponse;
import com.example.ananops_android.db.InspectionListResponse;
import com.example.ananops_android.db.ProjectListResponse;
import com.example.ananops_android.db.LoginResponse;
import com.example.ananops_android.db.OrderDetailResponse;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.db.OrderResponse;
import com.example.ananops_android.db.OrderTimelineResponse;
import com.example.ananops_android.db.PostResponse;
import com.example.ananops_android.db.RepairChangeDetail;
import com.example.ananops_android.db.UserInformation;
import com.example.ananops_android.entity.RepairAddContent;

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
import rx.Observable;

public interface Net {
    public static String base_url = "http://www.ananops.com:29995";//公网
    // public static String base_url = "http://10.108.217.223:7979";//公网
    public static String base_url1 = "http://10.28.219.121:7979";//
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
    @Headers({"Authorization: Basic YW5hbm9wcy1jbGllbnQtdWFjOmFuYW5vcHNDbGllbnRTZWNyZXQ=", "Content-Type: application/x-www-form-urlencoded"})
    @POST("/uac/auth/form")
    Observable<LoginResponse> login(@Field("username") String username,
                                    @Field("password") String password,
                                    @Field("imageCode") String imageCode,
                                    @Field("grant_type") String grantType,
                                    @Field("client_id") String client_id,
                                    @Field("client_secret") String client_secret,
                                    @Header("deviceId") Long deviceId
    );

    //获取用户信息
    @POST("/uac/user/queryUserInfo/{loginName}")
    Observable<UserInformation> getUserInfo(@Path("loginName") String loginName, @Header("Authorization") String postToken);

    //获取工单列表
    @Headers("Content-Type:application/json")
    @POST("/mdmc/mdmcTask/getTaskListByIdAndStatus")
    Observable<OrderResponse> getRepairList(@Body OrderRequest queryDto, @Header("Authorization") String postToken);

    //工单填写提交
    @Headers("Content-Type:application/json")
    @POST("/mdmc/mdmcTask/save")
    Observable<CodeMessageResponse> repairAddPost(@Body RepairAddContent MdmcAddTaskDto, @Header("Authorization") String postToken);

    //获取图片
    @POST("/uac/auth/code/image")
    Observable<PostResponse> getImage(@Header("deviceId") Long deviceId);

    //改变工单状态
    @Headers("Content-Type:application/json")
    @POST("/mdmc/mdmcTask/modifyTaskStatusByTaskId/{taskId}")
    Observable<CodeMessageResponse> changeStatus(@Body ChangeStatusDto modifyTaskStatus, @Header("Authorization") String postToken);

    //获取工单进度
    @Headers("Content-Type:application/json")
    @GET("/mdmc/mdmcTask/getTaskLogs/{taskId}")
    Observable<OrderTimelineResponse> getTimeLine(@Path("taskId") String orderId, @Header("Authorization") String postToken);

    //获取工单所有详情
    @Headers("Content-Type:application/json")
    @GET("/mdmc/mdmcTask/getTaskByTaskId/{taskId}")
    Observable<OrderDetailResponse> getOrderDetail(@Path("taskId") String orderId, @Header("Authorization") String postToken);

    //填写维修信息

    @POST("/mdmc/mdmcTask/modify")
    @Headers("Content-Type:application/json")
    Observable<CodeMessageResponse> postRepairDetail(@Body RepairChangeDetail modifyTask, @Header("Authorization") String postToken);

    //获取备件信息
    //获取组织项目列表
    @POST("/pmc/project/getProjectListByGroupId/{groupId}")
    Observable<ProjectListResponse> getProjectList(@Path("groupId") Long groupId, @Header("Authorization") String postToken);
    //获取根据项目获取巡检列表
    @POST("/pmc/InspectDevice/getTasksByProjectId/{projectId}")
    Observable<InspectionListResponse>getInspectionList(@Path("projectId")Long projectId, @Header("Authorization") String postToken);
    //获取巡检ID获取巡检子项
    @POST("/pmc/inspectDetail/getInspectDetailList/{inspectTaskId}")
    Observable<InspectionItemListResponse>getInspectionItemList(@Path("inspectTaskId")Long inspectTaskId,@Header("Authorization") String postToken);
}
