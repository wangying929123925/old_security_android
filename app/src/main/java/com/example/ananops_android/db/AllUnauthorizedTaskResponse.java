package com.example.ananops_android.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ananops_android.entity.InspectionInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllUnauthorizedTaskResponse {

    /**
     * code : 0
     * message : string
     * result : [{"actualFinishTime":"2020-01-15T08:23:29.511Z","createdTime":"2020-01-15T08:23:29.511Z","creator":"string","creatorId":0,"days":0,"facilitatorId":0,"frequency":0,"id":0,"inspectionType":0,"lastOperator":"string","lastOperatorId":0,"location":"string","maintenanceCost":0,"orderBy":"string","pageNum":0,"pageSize":0,"principalId":0,"projectId":0,"remark":"string","scheduledStartTime":"2020-01-15T08:23:29.511Z","status":0,"taskName":"string","totalCost":0,"updateTime":"2020-01-15T08:23:29.511Z","version":0}]
     */

    private String code;
    private String message;
    private ArrayList<InspectionInfo> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<InspectionInfo> getResult() {
        return result;
    }

    public void setResult(ArrayList<InspectionInfo> result) {
        this.result = result;
    }
}
