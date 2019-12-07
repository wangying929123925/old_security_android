package com.example.ananops_android.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.activity.RepairAddActivity;

public class DialogUtil {
    public static DialogUtil instence;
    public static DialogUtil getInstence() {
        if (null == instence) {
            instence = new DialogUtil();
        }
        return instence;
    }

    private DialogUtil() {

    }
    public void showExitAlertDialog(View view, final Activity fromContext){
        AlertDialog.Builder builder = new AlertDialog.Builder(fromContext.getApplicationContext());
        builder.setMessage("未填完工单，确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                fromContext.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
