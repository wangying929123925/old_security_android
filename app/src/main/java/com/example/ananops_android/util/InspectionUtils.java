package com.example.ananops_android.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.idst.nls.internal.utils.L;
import com.example.ananops_android.activity.RepairAddActivity;
import com.example.ananops_android.activity.UserMainActivity;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.InspectionItemListImcRequest;
import com.example.ananops_android.db.InspectionItemListResponse;
import com.example.ananops_android.db.InspectionListByProjectRequest;
import com.example.ananops_android.db.InspectionListResponse;
import com.example.ananops_android.db.InspectionLogResponse;
import com.example.ananops_android.db.InspectionLogsRequest;
import com.example.ananops_android.db.ProjectListResponse;
import com.example.ananops_android.entity.InspectionAddContent;
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.entity.InspectionTaskLog;
import com.example.ananops_android.entity.ProjectInfo;
import com.example.ananops_android.net.Net;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionUtils {
    public static InspectionUtils instence;
    public static InspectionUtils getInstence() {
        if (null == instence) {
            instence = new InspectionUtils();
        }
        return instence;
    }

    private InspectionUtils() {
    }

//新建获取项目巡检列表
public List<InspectionInfo> getInspectionList(final List<InspectionInfo> inspectionInfoList, Long projectId,final Context mContext) {
    if (projectId != null) {
        Net.instance.getInspectionList(projectId, SPUtils.getInstance().getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InspectionListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetInspectionList", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        Toast.makeText(mContext, "网络异常，请检查网络状态getInspectionList", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(InspectionListResponse inspectionListResponse) {
                        if (TextUtils.equals(inspectionListResponse.getCode(), "200")) {
                            inspectionInfoList.clear();
                            if (inspectionListResponse.getResult().size() > 0) {
                                for (int i = 0; i < inspectionListResponse.getResult().size(); i++) {
                                    inspectionInfoList.add(inspectionListResponse.getResult().get(i));
                                }
                                Log.v("巡检列表1", inspectionListResponse.getResult().get(0).getId() + "");
                            } else {
                                Toast.makeText(mContext, "无巡检列表！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mContext, inspectionListResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    } else {
        Toast.makeText(mContext,"请选择项目！", Toast.LENGTH_LONG).show();
    }
    return inspectionInfoList;
}

//查询 获取项目巡检列表
    public List<InspectionInfo> getInspectionListByProjectId(final List<InspectionInfo> inspectionInfoList,Long projectId,final Context mContext){
    if (projectId != null) {
        InspectionListByProjectRequest inspectionListByProjectRequest=new InspectionListByProjectRequest();
        inspectionListByProjectRequest.setProjectId(projectId);
        Net.instance.getInspectionListByProjectId(inspectionListByProjectRequest, SPUtils.getInstance().getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InspectionListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetInspection_pro", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(InspectionListResponse inspectionListResponse) {
                        if (TextUtils.equals(inspectionListResponse.getCode(), "200")) {
                            inspectionInfoList.clear();
                            if (inspectionListResponse.getResult().size() > 0) {
                                inspectionInfoList.addAll(inspectionListResponse.getResult());
                                Log.v("巡检列表1", inspectionListResponse.getResult().get(0).getId() + "");
                            } else {
                                Toast.makeText(mContext, "无巡检列表！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mContext, inspectionListResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    } else {
        Toast.makeText(mContext,"请选择项目！", Toast.LENGTH_LONG).show();
    }
    return inspectionInfoList;
}
//获取巡检详情

    //获取巡检子项
 public List<InspectionTaskItem> getInspectionTaskItems(final List<InspectionTaskItem> inspectionTaskItemList, Long inspectTaskId,final Context mContext) {
     if (inspectTaskId != null) {
         Net.instance.getInspectionItemList(inspectTaskId, SPUtils.getInstance().getString("Token", " "))
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Subscriber<InspectionItemListResponse>() {
                     @Override
                     public void onCompleted() {

                     }

                     @Override
                     public void onError(Throwable e) {
                         Log.v("ErrorGetInspectionItem", System.currentTimeMillis() + "");
                         e.printStackTrace();
                         Toast.makeText(mContext, "网络异常，请检查网络状态ErrorGetInspectionItem", Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void onNext(InspectionItemListResponse inspectionItemListResponse) {
                         if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                             inspectionTaskItemList.clear();
                             if (inspectionItemListResponse.getResult().size() > 0) {
                                 inspectionTaskItemList.addAll(inspectionItemListResponse.getResult());
                                 Log.v("巡检子项列表1", inspectionItemListResponse.getResult().get(0).getId() + "");
                             } else {
                                 Toast.makeText(mContext, "无巡检子项列表！", Toast.LENGTH_LONG).show();
                             }
                         } else {
                             Toast.makeText(mContext, inspectionItemListResponse.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }
                 });
     } else {
         Toast.makeText(mContext, "请选择巡检！", Toast.LENGTH_LONG).show();
     }
     return inspectionTaskItemList;
 }
 //查询 获取巡检子项
public List<InspectionTaskItem> getInspectionTaskItemsImc(final List<InspectionTaskItem> inspectionTaskItemList, Long inspectTaskId,final Context mContext){
    if (inspectTaskId != null) {
        InspectionItemListImcRequest inspectionItemListImcRequest=new InspectionItemListImcRequest();
        inspectionItemListImcRequest.setTaskId(inspectTaskId);
      Net.instance.getInspectionItemListImc(inspectionItemListImcRequest, SPUtils.getInstance().getString("Token", " "))
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Subscriber<InspectionItemListResponse>() {
                  @Override
                  public void onCompleted() {

                  }

                  @Override
                  public void onError(Throwable e) {
                      Log.v("ErrorGetInspectionItem", System.currentTimeMillis() + "");
                      e.printStackTrace();
                  }

                  @Override
                  public void onNext(InspectionItemListResponse inspectionItemListResponse) {
                      if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                          inspectionTaskItemList.clear();
                          if (inspectionItemListResponse.getResult().size() > 0) {
                              inspectionTaskItemList.addAll(inspectionItemListResponse.getResult());
                              Log.v("巡检子项列表1", inspectionItemListResponse.getResult().get(0).getId() + "");
                          } else {
                              Toast.makeText(mContext, "无巡检子项列表！", Toast.LENGTH_LONG).show();
                          }
                      } else {
                          Toast.makeText(mContext, inspectionItemListResponse.getMessage(), Toast.LENGTH_LONG).show();
                      }
                  }
              });

    } else {
        Toast.makeText(mContext, "无巡检！", Toast.LENGTH_LONG).show();
    }
    return inspectionTaskItemList;
}
    //获取巡检子项详情

 //获取巡检进度条
    public List<InspectionTaskLog> getInspectionLogs(final List<InspectionTaskLog>inspectionTaskLogs,Long inspectTaskId,final Context mContext) {
        if (inspectTaskId != null) {
            InspectionLogsRequest inspectionLogsRequest=new InspectionLogsRequest();
            inspectionLogsRequest.setTaskId(inspectTaskId);
            inspectionLogsRequest.setOrderBy("string");
            Net.instance.getInspectionLog(inspectionLogsRequest, SPUtils.getInstance().getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<InspectionLogResponse>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            Log.v("ErrorGetInspectionLog", System.currentTimeMillis() + "");
                            e.printStackTrace();
                        }
                        @Override
                        public void onNext(InspectionLogResponse inspectionLogResponse) {
                            if (TextUtils.equals(inspectionLogResponse.getCode(), "200")) {
                                inspectionTaskLogs.clear();
                                if (inspectionLogResponse.getResult().size() > 0) {
                                   inspectionTaskLogs.addAll(inspectionLogResponse.getResult());
                                    Log.v("巡检日志列表1", inspectionLogResponse.getResult().get(0).getId() + "");
                                } else {
                                    Toast.makeText(mContext, "无巡检日志列表！", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(mContext, inspectionLogResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(mContext, "没有巡检单号！", Toast.LENGTH_LONG).show();
        }
        return inspectionTaskLogs;
        }
//添加巡检任务
    public void addInspection(InspectionAddContent inspectionAddContent,final Context mContext) {
        Net.instance.addInspectionInfo(inspectionAddContent, SPUtils.getInstance().getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CodeMessageResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorAddInspection", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CodeMessageResponse codeMessageResponse) {
                        if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                            Toast.makeText(mContext,"提交成功！",Toast.LENGTH_SHORT).show();
                            BaseUtils.getInstence().intent(mContext, UserMainActivity.class);
                        }
                        else{
                            Toast.makeText(mContext,"提交失败！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
