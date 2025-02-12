package com.example.ananops_android.net;

import com.example.ananops_android.db.AcceptImcTaskByPrincipalRequest;
import com.example.ananops_android.db.AcceptInspectionItemRequest;
import com.example.ananops_android.db.AllAcceptedItemByMaintainerRequest;
import com.example.ananops_android.db.AllFinishedInspectionItemResponse;
import com.example.ananops_android.db.AllItemByTaskIdAndStatuRequest;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersRequest;
import com.example.ananops_android.db.AllUnDistributedWorkOrdersResponse;
import com.example.ananops_android.db.AllUnauthorizedTaskRequest;
import com.example.ananops_android.db.AllUnauthorizedTaskResponse;
import com.example.ananops_android.db.ChangeInspectionItemStatusRequest;
import com.example.ananops_android.db.ChangeInspectionStatusRequest;
import com.example.ananops_android.db.ChangePasswordRequest;
import com.example.ananops_android.db.ChangeStatusDto;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.ConfirmWorkOrderRequest;
import com.example.ananops_android.db.DeviceOrderResult;
import com.example.ananops_android.db.EngineerListByGroupIdRequest;
import com.example.ananops_android.db.EngineerListByGroupIdResponse;
import com.example.ananops_android.db.GetAllUnConfirmedWorkOrdersRequset;
import com.example.ananops_android.db.GetAllUnConfirmedWorkOrdersResponse;
import com.example.ananops_android.db.GroupIdResponse;
import com.example.ananops_android.db.InspectionCommentRequest;
import com.example.ananops_android.db.InspectionDetailResponse;
import com.example.ananops_android.db.InspectionEngineerDistributeRequest;
import com.example.ananops_android.db.InspectionItemAddRequest;
import com.example.ananops_android.db.InspectionItemDetailResponse;
import com.example.ananops_android.db.InspectionItemListImcRequest;
import com.example.ananops_android.db.InspectionItemListResponse;
import com.example.ananops_android.db.InspectionItemLogsRequest;
import com.example.ananops_android.db.InspectionItemSubmitRequest;
import com.example.ananops_android.db.InspectionListByProjectRequest;
import com.example.ananops_android.db.InspectionListByUserIdAndStatusRequest;
import com.example.ananops_android.db.InspectionListResponse;
import com.example.ananops_android.db.InspectionLogResponse;
import com.example.ananops_android.db.InspectionLogsRequest;
import com.example.ananops_android.db.InspectionPicRequest;
import com.example.ananops_android.db.InspectionPicResponse;
import com.example.ananops_android.db.InspectionQueryByStatusAndIdRequest;
import com.example.ananops_android.db.InvoiceDetailResponse;
import com.example.ananops_android.db.InvoiceListRequest;
import com.example.ananops_android.db.InvoiceListResponse;
import com.example.ananops_android.db.LoginResponse;
import com.example.ananops_android.db.MessageListRequest;
import com.example.ananops_android.db.MessageListResponse;
import com.example.ananops_android.db.MessageStatusChangeRequest;
import com.example.ananops_android.db.OrderDetailResponse;
import com.example.ananops_android.db.OrderRequest;
import com.example.ananops_android.db.OrderResponse;
import com.example.ananops_android.db.OrderTimelineResponse;
import com.example.ananops_android.db.PostResponse;
import com.example.ananops_android.db.ProjectInfoResponse;
import com.example.ananops_android.db.ProjectListResponse;
import com.example.ananops_android.db.RelacementOrderListUndoResult;
import com.example.ananops_android.db.RelacementOrderOperationRequest;
import com.example.ananops_android.db.RepairChangeDetail;
import com.example.ananops_android.db.RepairCommentRequest;
import com.example.ananops_android.db.RepairFileUrlResponse;
import com.example.ananops_android.db.RepairerListResponse;
import com.example.ananops_android.db.ReplacementListResponse;
import com.example.ananops_android.db.ReplacementOrderCreateRequest;
import com.example.ananops_android.db.TroubleTypeAndAddressListResponse;
import com.example.ananops_android.db.UnDistrbutedInspectionDetailRequest;
import com.example.ananops_android.db.UnDistrbutedInspectionDetailResponse;
import com.example.ananops_android.db.UpLoadFilesResponse;
import com.example.ananops_android.db.UserInformation;
import com.example.ananops_android.entity.InspectionAddContent;
import com.example.ananops_android.entity.InvoiceDetail;
import com.example.ananops_android.entity.RepairAddContent;
import com.example.ananops_android.util.BaseUtils;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface Net {
    public static String base_url = "https://admin.ananops.com";//公网
    // public static String base_url = "http://10.108.217.223:7979";//公网
   public static String base_url1 = "https://www.ananops.com/api/";//
    Net instance = new Retrofit.Builder()
            .baseUrl(base_url1)
            .addConverterFactory(GsonConverterFactory.create())
            //网络请求适配器，结合RXjava
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(BaseUtils.getInstence().getClient().build())
            .build()
            .create(Net.class);
//    Net instance1 = new Retrofit.Builder()
//            .baseUrl(base_url1)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            .build()
//            .create(Net.class);

    //登录
    @FormUrlEncoded
    @Headers({"Authorization: Basic YW5hbm9wcy1jbGllbnQtdWFjOmFuYW5vcHNDbGllbnRTZWNyZXQ=", "Content-Type: application/x-www-form-urlencoded"})
    @POST("uac/auth/form")
    Observable<LoginResponse> login(@Field("username") String username,
                                    @Field("password") String password,
                                    @Field("imageCode") String imageCode,
                                    @Field("grant_type") String grantType,
                                    @Field("client_id") String client_id,
                                    @Field("client_secret") String client_secret,
                                    @Header("deviceId") Long deviceId
            );

    //获取用户信息
    @POST("uac/user/queryUserInfo/{loginName}")
    Observable<UserInformation> getUserInfo(@Path("loginName") String loginName, @Header("Authorization") String postToken);
    //获取组织信息
    @POST("uac/user/getUacUserById/{userId}")
    Observable<GroupIdResponse> getGroupId(@Path("userId")Long userId,@Header("Authorization") String postToken);
   //修改密码
    @POST("uac/user/authUserModifyPwd")
    Observable<CodeMessageResponse>changePassword(@Body ChangePasswordRequest userModifyPwdDto,@Header("Authorization") String postToken);
    //获取工单列表
    @Headers("Content-Type:application/json")
    @POST("mdmc/mdmcTask/getTaskList")
    Observable<OrderResponse> getRepairList(@Body OrderRequest queryDto, @Header("Authorization") String postToken);

    //获取维修子项地址和设备列表
    @GET("mdc/dictItem/getSysDictItemList")
    Observable<TroubleTypeAndAddressListResponse> getTroubleTypeListAndAddressList(@Header("Authorization") String postToken);

    //工单填写提交
    @Headers("Content-Type:application/json")
    @POST("mdmc/mdmcTask/save")
    Observable<CodeMessageResponse> repairAddPost(@Body RepairAddContent saveTask, @Header("Authorization") String postToken);

    //维修上传图片
    @Multipart
    @POST("mdmc/mdmcTask/uploadTaskPicture")
    Observable<List<UpLoadFilesResponse>>upLoadFiles(@Query("fileType")String fileType,
                                                     @Query("filePath")String filePath,
                                                     @Query("bucketName")String bucketName,
                                                     @Part MultipartBody.Part file,
                                                     @Header("Authorization") String postToken,
                                                     @Header("deviceId") Long deviceId
                                        );
   //维修查看文件
    @GET("mdmc/mdmcTask/getPictureByTaskId")
    Observable<RepairFileUrlResponse>getFilesUrl(@Query("taskId") Long taskId, @Header("Authorization") String postToken);
    //获取验证码图片
    @POST("uac/auth/code/image")
    Observable<PostResponse> getImage(@Header("deviceId") Long deviceId);

    //改变工单状态
    @Headers("Content-Type:application/json")
    @POST("mdmc/mdmcTask/modifyTaskStatusByTaskId/{taskId}")
    Observable<CodeMessageResponse> changeStatus(@Body ChangeStatusDto modifyTaskStatus,@Path("taskId")String taskId, @Header("Authorization") String postToken);

    //获取工单进度
    @Headers("Content-Type:application/json")
    @GET("mdmc/mdmcTask/getTaskLogs/{taskId}")
    Observable<OrderTimelineResponse> getTimeLine(@Path("taskId") String orderId, @Header("Authorization") String postToken);

    //获取工单所有详情
    @Headers("Content-Type:application/json")
    @GET("mdmc/mdmcTask/getTaskDetailByTaskId")
    Observable<OrderDetailResponse> getOrderDetail(@Query("taskId") Long orderId, @Header("Authorization") String postToken);

    //修改维修信息
    @POST("mdmc/mdmcTask/modify")
    @Headers("Content-Type:application/json")
    Observable<CodeMessageResponse> postRepairDetail(@Body RepairChangeDetail modifyTask, @Header("Authorization") String postToken);

    //根据项目获取维修工程师名单
    @POST("spc/engineer/getEngineerIdListByProjectId/{projectId}")
    Observable<RepairerListResponse> getRepairerList(@Path("projectId") Long projectId, @Header("Authorization") String postToken);

    //groupId获取维修工名单
    @POST("spc/engineer/queryListByGroupId")
    Observable<EngineerListByGroupIdResponse>getRepairListByGroupId(@Body EngineerListByGroupIdRequest engineerQueryDto,@Header("Authorization") String postToken);

    //获取备件信息列表
    @GET("rdc/deviceOrder/devices")
    Observable<ReplacementListResponse>getReplacementList(@Header("Authorization") String postToken);

    //工程师提交备品备件申请
    @POST("rdc/deviceOrder/create")
    Observable<CodeMessageResponse>ReplacementOrderCreate(@Body ReplacementOrderCreateRequest createNewOrderDto,@Header("Authorization") String postToken);

    //服务商获取待处理备品备件单据列表
   @GET("rdc/deviceOrder/todo/{userId}")
   Observable<RelacementOrderListUndoResult>getRelacementOrderListUndo(@Path("userId") Long userId, @Header("Authorization") String postToken);

   //获取备品备件单据信息
   @GET("rdc/deviceOrder/all/object/{objectId}/{objectType}")
   Observable<DeviceOrderResult>getDeviceOrderInfo(@Path("objectId")Long objectId,@Path("objectType")Integer objectType,@Header("Authorization") String postToken);

   //服务商业务员处理备件申请
   @POST("rdc/deviceOrder/operation")
   Observable<CodeMessageResponse>ReplacementOrderOperation(@Body RelacementOrderOperationRequest processOrderDto,@Header("Authorization") String postToken);

    //维修维护评价提交
    @POST("mdmc/mdmcReview/save")
    Observable<CodeMessageResponse>RepairCommentAdd(@Body  RepairCommentRequest saveReview,@Header("Authorization") String postToken);

    //获取组织项目列表
    @POST("pmc/project/getProjectListByGroupId/{groupId}")
    Observable<ProjectListResponse> getProjectList(@Path("groupId") Long groupId, @Header("Authorization") String postToken);

    //新建获取根据项目获取巡检列表
    @POST("pmc/InspectDevice/getTasksByProjectId/{projectId}")
    Observable<InspectionListResponse> getInspectionList(@Path("projectId") Long projectId, @Header("Authorization") String postToken);

    //查询 根据项目ID获取巡检列表
    @POST("imc/inspectionTask/getTaskByProjectId")
    Observable<InspectionListResponse> getInspectionListByProjectId(@Body InspectionListByProjectRequest getTaskByProjectId, @Header("Authorization") String postToken);

    //新建 根据巡检ID获取巡检子项
    @POST("pmc/inspectDetail/getInspectDetailList/{inspectTaskId}")
    Observable<InspectionItemListResponse> getInspectionItemList(@Path("inspectTaskId") Long inspectTaskId, @Header("Authorization") String postToken);

    //查询 根据巡检ID获取巡检子项
    @POST("imc/inspectionItem/getAllItemListByTaskId")
    Observable<AllFinishedInspectionItemResponse> getInspectionItemListImc(@Body InspectionItemListImcRequest getAllItemByTaskId, @Header("Authorization") String postToken);

    //获取项目详情
    @POST("pmc/project/getById/{id}")
    Observable<ProjectInfoResponse> getProjectInfo(@Path("id") Long projectId, @Header("Authorization") String postToken);

    //获取巡检进度条
    @POST("imc/inspectionTask/getTaskLogs")
    Observable<InspectionLogResponse> getInspectionLog(@Body InspectionLogsRequest getTaskLogsByTaskId, @Header("Authorization") String postToken);

    //添加巡检
    @POST("imc/inspectionTask/save")
    Observable<CodeMessageResponse> addInspectionInfo(@Body InspectionAddContent saveInspectionTask, @Header("Authorization") String postToken);
   //巡检上传图片
   @Multipart
   @POST("imc/inspectionItem/uploadImcItemPicture")
   Observable<List<UpLoadFilesResponse>>upLoadFiles1(@Query("fileType")String fileType,
                                                    @Query("filePath")String filePath,
                                                    @Query("bucketName")String bucketName,
                                                    @Part MultipartBody.Part file,
                                                    @Header("Authorization") String postToken,
                                                    @Header("deviceId") Long deviceId
   );
   // 获取巡检图片
    @POST("imc/inspectionItem/getImcPicListByTaskAndItemAndStatus")
    Observable<InspectionPicResponse>getInspectionPicsUrl(@Body InspectionPicRequest imcPicQueryDto,@Header("Authorization") String postToken);

    //根据用户ID查询巡检列表
    @POST("imc/inspectionTask/getTaskByUserId")
    Observable<AllUnauthorizedTaskResponse>getInspectionTaskByUserId(@Body InspectionListByUserIdAndStatusRequest getTaskByUserId, @Header("Authorization") String postToken);

    //根据服务商ID查询列表
    @POST("imc/inspectionTask/getAllTaskByFacilitatorId")
    Observable<AllUnDistributedWorkOrdersResponse>getInspectionTaskByFacilitatorId(@Body AllUnDistributedWorkOrdersRequest getTaskByUserId, @Header("Authorization") String postToken);

 //根据状态和用户ID查询巡检列表
    @POST("imc/inspectionTask/getTaskListByUserIdAndStatus")
    Observable<AllUnDistributedWorkOrdersResponse> getInspectionTaskByUserIdAndStatus(@Body InspectionListByUserIdAndStatusRequest getTaskByUserIdAndStatus, @Header("Authorization") String postToken);

    //服务商查询未分配工程师的巡检单
    @POST("imc/inspectionTask/getAllUnDistributedTask")
    Observable<AllUnDistributedWorkOrdersResponse> getAllUnDistributedWorkOrder(@Body AllUnDistributedWorkOrdersRequest taskQueryDto, @Header("Authorization") String postToken);

    //甲方负责人查看未审核任务
    @POST("imc/inspectionTask/getAllUnauthorizedTask")
    Observable<AllUnauthorizedTaskResponse> getAllUnauthorizedTask(@Body AllUnauthorizedTaskRequest allUnauthorizedTaskRequest, @Header("Authorization") String postToken);

    //甲方负责人通过审核
    @POST("imc/inspectionTask/acceptImcTaskByPrincipal")
    Observable<CodeMessageResponse> acceptImcTaskByPrincipal(@Body AcceptImcTaskByPrincipalRequest acceptImcTaskByPrincipalRequest, @Header("Authorization") String postToken);

    //甲方负责人拒绝审核
    @POST("imc/inspectionTask/denyImcTaskByPrincipal")
    Observable<CodeMessageResponse> denyImcTaskByPrincipal(@Body AcceptImcTaskByPrincipalRequest acceptImcTaskByPrincipalRequest, @Header("Authorization") String postToken);

    //服务商查看未审批工单
    @POST("imc/inspectionTask/getAllUnConfirmedTask")
    Observable<GetAllUnConfirmedWorkOrdersResponse> getAllUnConfirmedWorkOrders(@Body GetAllUnConfirmedWorkOrdersRequset taskQueryDto, @Header("Authorization") String postToken);

    //服务商查看已完成工单
    @POST("imc/inspectionTask/getAllFinishedTaskByFacilitatorId")
    Observable<GetAllUnConfirmedWorkOrdersResponse> getAllFinishedWorkOrders(@Body GetAllUnConfirmedWorkOrdersRequset taskQueryDto, @Header("Authorization") String postToken);

    //服务商通过审核
    @POST("spc/workorder/confirmWorkOrder")
    Observable<CodeMessageResponse> confirmWorkOrder(@Body ConfirmWorkOrderRequest WorkOrderConfirmDto, @Header("Authorization") String postToken);

   //添加巡检子项项目
   @POST("imc/inspectionItem/save")
   Observable<CodeMessageResponse> inspectionItemAdd(@Body InspectionItemAddRequest saveInspectionItem, @Header("Authorization") String postToken);

   //服务商查询未分配工程师巡检单的详细信息
    @POST("spc/workorder/getSpcWorkOrderById")
    Observable<UnDistrbutedInspectionDetailResponse> getSpcWorkOrderById(@Body UnDistrbutedInspectionDetailRequest WorkOrderStatusQueryDto, @Header("Authorization") String postToken);

    //服务商为巡检子项分配工程师
    @POST("spc/workorder/distributeEngineerWithImcOrder")
    Observable<CodeMessageResponse> inspectionDistributeEngineer(@Body InspectionEngineerDistributeRequest engineerDistributeDto, @Header("Authorization") String postToken);

    //维修工程师查询未接单子项,状态2//g根据状态和ID查询单据巡检子项
    @POST("imc/inspectionItem/getItemListByMaintainerIdAndStatus")
    Observable<AllFinishedInspectionItemResponse> getInspectionItemByMaintainerIdAndStatus(@Body InspectionQueryByStatusAndIdRequest getItemByMaintainerIdAndStatus, @Header("Authorization") String postToken);

    //维修工程师查看已接单子项列表
    @POST("imc/inspectionItem/getAllAcceptedItemListByMaintainer")
    Observable<AllFinishedInspectionItemResponse> getAllAcceptedItemByMaintainer(@Body AllAcceptedItemByMaintainerRequest itemQueryDto, @Header("Authorization") String postToken);

    //维修工程师查看已完成巡检子项列表
    @POST("imc/inspectionItem/getAllFinishedImcItemByMaintainerId")
    Observable<AllFinishedInspectionItemResponse> getAllFinishedItemByMaintainer(@Body InspectionQueryByStatusAndIdRequest itemQueryDto, @Header("Authorization") String postToken);

    //维修工程师获取全部巡检子项列表
    @POST("imc/inspectionItem/getItemByMaintainerId")
    Observable<InspectionItemListResponse> getAllItemByMaintainer(@Body AllAcceptedItemByMaintainerRequest itemQueryDto, @Header("Authorization") String postToken);

   //根据巡检ID和状态查询子项列表
    @POST("imc/inspectionItem/getAllItemListByTaskIdAndStatus")
    Observable<AllFinishedInspectionItemResponse> getAllItemByTaskIdAndStatus(@Body AllItemByTaskIdAndStatuRequest getAllItemByTaskIdAndStatus, @Header("Authorization") String postToken);

    //根据子项ID查看子项日志
    @POST("imc/inspectionItem/getItemLogs")
    Observable<InspectionLogResponse> getInspectionItemLog(@Body InspectionItemLogsRequest getItemLogs, @Header("Authorization") String postToken);

    //获取子项信息
    @GET("imc/inspectionItem/getItemByItemId/{itemId}")
    Observable<InspectionItemDetailResponse> getInspectionItemDetails(@Path("itemId") Long itemId, @Header("Authorization") String postToken);

    //获取巡检信息
    @GET("imc/inspectionTask/getTaskByTaskId/{taskId}")
    Observable<InspectionDetailResponse> getInspectionDetails(@Path("taskId") Long taskId, @Header("Authorization") String postToken);

    //巡检维修工程师接单
    @POST("imc/inspectionItem/acceptItemByMaintainer")
    Observable<CodeMessageResponse> acceptItemByMaintainer(@Body AcceptInspectionItemRequest confirmImcItemDto, @Header("Authorization") String postToken);

    //巡检评价
    @POST("imc/inspectionReview/confirmRating")
    Observable<CodeMessageResponse> InspectionCommentAdd(@Body InspectionCommentRequest imcAddInspectionReviewDto, @Header("Authorization") String postToken);

    //修改巡检子项状态
    @POST("imc/inspectionItem/modifyItemStatusByItemId")
    Observable<CodeMessageResponse> modifyItemStatusByItemId(@Body ChangeInspectionItemStatusRequest modifyItemStatus, @Header("Authorization") String postToken);

    @POST("imc/inspectionTask/modifyTaskStatusByTaskId")
    Observable<CodeMessageResponse>modifyIspectionStatusByItemId(@Body ChangeInspectionStatusRequest modifyTaskStatus,@Header("Authorization") String postToken);

    //获取子项单据列表
    @POST("imc/itemInvoice/queryInvoiceList")
     Observable<InvoiceListResponse>getInvoiceList(@Body InvoiceListRequest imcInvoiceQueryDto, @Header("Authorization") String postToken);

   //获取子项单据详情
   @POST("imc/itemInvoice/queryDetailsById/{invoiceId}")
   Observable<InvoiceDetailResponse> getInvoiceDetail(@Path("invoiceId") Long invoiceId, @Header("Authorization") String postToken);

   //子项单据提交
   @POST("imc/itemInvoice/save")
   Observable<CodeMessageResponse>invoiceDetailSave(@Body InvoiceDetail formDataDto, @Header("Authorization") String postToken);

   //
    @POST("imc/inspectionItem/putResultByItemId")
    Observable<CodeMessageResponse>putInspectionItemResult(@Body InspectionItemSubmitRequest itemResultDto,@Header("Authorization") String postToken);
   //获取消息列表
    @POST("websocket/websocket/queryWebsocketMsgInfo")
    Observable<MessageListResponse> getMessageList(@Body MessageListRequest queryWebsocketMsgInfo,@Header("Authorization") String postToken);

    //修改消息状态
    @POST("websocket/websocket/changeWebsocketMsgStatus")
    Observable<CodeMessageResponse>changeMessageStatus(@Body MessageStatusChangeRequest changeWebsocketMsgStatus,@Header("Authorization") String postToken);

}
