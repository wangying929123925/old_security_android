package com.example.ananops_android.net;

import com.example.ananops_android.db.AcceptImcTaskByPrincipalRequest;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersRequest;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersResponse;
import com.example.ananops_android.db.AllUnauthorizedTaskRequest;
import com.example.ananops_android.db.AllUnauthorizedTaskResponse;
import com.example.ananops_android.db.ChangeStatusDto;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.ConfirmWorkOrderRequest;
import com.example.ananops_android.db.InspectionItemListImcRequest;
import com.example.ananops_android.db.InspectionItemListResponse;
import com.example.ananops_android.db.InspectionListByProjectRequest;
import com.example.ananops_android.db.InspectionListResponse;
import com.example.ananops_android.db.InspectionLogResponse;
import com.example.ananops_android.db.InspectionLogsRequest;
import com.example.ananops_android.db.ProjectInfoResponse;
import com.example.ananops_android.db.ProjectListResponse;
import com.example.ananops_android.db.LoginResponse;
import com.example.ananops_android.db.OrderDetailResponse;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.db.OrderResponse;
import com.example.ananops_android.db.OrderTimelineResponse;
import com.example.ananops_android.db.PostResponse;
import com.example.ananops_android.db.RepairChangeDetail;
import com.example.ananops_android.db.UnDistrbutedInspectionDetailRequest;
import com.example.ananops_android.db.UnDistrbutedInspectionDetailResponse;
import com.example.ananops_android.db.UserInformation;
import com.example.ananops_android.db.GetAllUnConfirmedWorkOrdersRequset;
import com.example.ananops_android.db.GetAllUnConfirmedWorkOrdersResponse;
import com.example.ananops_android.entity.InspectionAddContent;
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
    //新建获取根据项目获取巡检列表
    @POST("/pmc/InspectDevice/getTasksByProjectId/{projectId}")
    Observable<InspectionListResponse>getInspectionList(@Path("projectId")Long projectId, @Header("Authorization") String postToken);
    //查询 根据项目ID获取巡检列表
    @POST("/imc/inspectionTask/getTaskByProjectId")
    Observable<InspectionListResponse>getInspectionListByProjectId(@Body InspectionListByProjectRequest getTaskByProjectId, @Header("Authorization") String postToken);
    //新建 根据巡检ID获取巡检子项
    @POST("/pmc/inspectDetail/getInspectDetailList/{inspectTaskId}")
    Observable<InspectionItemListResponse>getInspectionItemList(@Path("inspectTaskId")Long inspectTaskId,@Header("Authorization") String postToken);
    //查询 根据巡检ID获取巡检子项
    @POST("/imc/inspectionItem/getAllItemByTaskId")
    Observable<InspectionItemListResponse>getInspectionItemListImc(@Body InspectionItemListImcRequest getAllItemByTaskId, @Header("Authorization") String postToken);
    //获取项目详情
    @POST("/pmc/project/getById/{id}")
    Observable<ProjectInfoResponse> getProjectInfo(@Path("id") Long projectId, @Header("Authorization") String postToken);
    //获取巡检进度条
    @POST("/imc/inspectionTask/getTaskLogs")
    Observable<InspectionLogResponse>getInspectionLog(@Body InspectionLogsRequest getTaskLogsByTaskId, @Header("Authorization") String postToken);

    //添加巡检
    @POST("/imc/inspectionTask/save")
    Observable<CodeMessageResponse>addInspectionInfo(@Body InspectionAddContent saveInspectionTask,@Header("Authorization") String postToken);
    //甲方负责人查询未分配工程师的巡检单
    @POST("spc/workorder/getAllUnDistributedWorkOrder")
    Observable<AllUnDistributedWorkOrdersResponse>getAllUnDistributedWorkOrder(@Body AllUnDistributedWorkOrdersRequest WorkOrderStatusQueryDto, @Header("Authorization") String postToken);

    //甲方负责人查看未审核任务
    @POST("/imc/inspectionTask/getAllUnauthorizedTask")
    Observable<AllUnauthorizedTaskResponse> getAllUnauthorizedTask(@Body AllUnauthorizedTaskRequest allUnauthorizedTaskRequest, @Header("Authorization") String postToken);

    //甲方负责人通过审核
    @POST("/imc/inspectionTask/acceptImcTaskByPrincipal")
    Observable<CodeMessageResponse> acceptImcTaskByPrincipal(@Body AcceptImcTaskByPrincipalRequest acceptImcTaskByPrincipalRequest, @Header("Authorization") String postToken);

    //甲方负责人拒绝审核
    @POST("/imc/inspectionTask/denyImcTaskByPrincipal")
    Observable<CodeMessageResponse> denyImcTaskByPrincipal(@Body AcceptImcTaskByPrincipalRequest acceptImcTaskByPrincipalRequest, @Header("Authorization") String postToken);

    //服务商查看未审批工单
    @POST("/spc/workorder/getAllUnConfirmedWorkOrders")
    Observable<GetAllUnConfirmedWorkOrdersResponse> getAllUnConfirmedWorkOrders(@Body GetAllUnConfirmedWorkOrdersRequset getAllUnConfirmedWorkOrdersRequest, @Header("Authorization") String postToken);

    //服务商通过审核
    @POST("/spc/workorder/confirmWorkOrder")
    Observable<CodeMessageResponse> confirmWorkOrder(@Body ConfirmWorkOrderRequest acceptImcTaskByPrincipalRequest, @Header("Authorization") String postToken);

    //甲方负责人查询未分配工程师巡检单的详细信息
    @POST("/spc/workorder/getSpcWorkOrderById")
    Observable<UnDistrbutedInspectionDetailResponse>getSpcWorkOrderById(@Body UnDistrbutedInspectionDetailRequest WorkOrderStatusQueryDto,@Header("Authorization") String postToken);
    //为巡检子项分配工程师
    //维修工程师查询未接单子项
    //维修工程师接单
    //修改状态


}
