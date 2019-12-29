package com.example.ananops_android.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.ananops_android.db.InspectionItemListResponse;
import com.example.ananops_android.db.InspectionListResponse;
import com.example.ananops_android.db.ProjectListResponse;
import com.example.ananops_android.entity.InspectionInfo;
import com.example.ananops_android.entity.InspectionTaskItem;
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
    /*
   获取项目信息
     */
public List<ProjectInfo> getProjectList(final List<ProjectInfo> projectInfo, Long groupId,final Context mContext){
    Net.instance.getProjectList(groupId, SPUtils.getInstance().getString("Token", " "))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ProjectListResponse>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    Log.v("ErrorGetProjectList", System.currentTimeMillis() + "");
                    e.printStackTrace();
                    Toast.makeText(mContext, "网络异常，请检查网络状态changeStatus", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(ProjectListResponse projectListResponse) {
                    if (TextUtils.equals(projectListResponse.getCode(), "200")) {
                        projectInfo.clear();
                        if (projectListResponse.getResult().size() > 0) {
                            projectInfo.addAll(projectListResponse.getResult());
                            Log.v("项目列表1", projectListResponse.getResult().get(0).getId() + "");
                        } else {
                            Toast.makeText(mContext, "无项目列表！", Toast.LENGTH_LONG).show();
                            Log.v("项目列表0", projectListResponse.getResult().size() + "");
                        }
                    } else {
                        Toast.makeText(mContext, projectListResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        return projectInfo;
}
//获取巡检列表
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
         Toast.makeText(mContext, "请选择巡检项目！", Toast.LENGTH_LONG).show();
     }
     return inspectionTaskItemList;
 }
}
