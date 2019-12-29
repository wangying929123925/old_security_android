package com.example.ananops_android.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.ananops_android.db.ProjectListResponse;
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
                        }
                    } else {
                        Toast.makeText(mContext, projectListResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        return projectInfo;
}

}
