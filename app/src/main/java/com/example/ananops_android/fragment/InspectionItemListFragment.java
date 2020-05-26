package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.InspectionItemDetailActivity;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.AllFinishedInspectionItemResponse;
import com.example.ananops_android.db.AllItemByTaskIdAndStatuRequest;
import com.example.ananops_android.db.InspectionItemListImcRequest;
import com.example.ananops_android.entity.InspectionTaskItem;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionItemListFragment extends Fragment {
    private ListView lv_task_items;
    private List<InspectionTaskItem> inspectionTaskItems = new ArrayList<>();
    private static String inspectionId;
    private String statusDo;
    private Context mContext;
    private ListCommonAdapter mAdapter;
    private LinearLayout noResult;
    private RadioGroup rg_tab;

    //Fragment中构造方法中不能传递参数
    //通过下面的方式 能将参数传进InspectionFragment中
    public static InspectionItemListFragment newInstance(String id,String statusDo) {
        InspectionItemListFragment inspectionItemListFragment = new InspectionItemListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("statusDo",statusDo);
        inspectionItemListFragment.setArguments(bundle);
        return inspectionItemListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspection_list, container, false);
       lv_task_items = view.findViewById(R.id.lv_inspection_list);
        noResult = view.findViewById(R.id.no_result_text);
        mContext = getContext();
        Bundle bundle = getArguments();
        if(bundle!=null){
        inspectionId = bundle.getString("id");}
        statusDo = bundle.getString("statusDo");
        rg_tab = view.findViewById(R.id.fragment_tab);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();
        getAllInspectionItemList();
        rg_tab.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.inspection_rb1:
                    getAllInspectionItemList();
                    break;
                case R.id.inspection_rb2:
                    getInspectionItemListByStatus("1");
                    break;
                case R.id.inspection_rb3:
                    getInspectionItemListByStatus("2");
                    break;
                case R.id.inspection_rb4:
                    getInspectionItemListByStatus("3");
                    break;
                case R.id.inspection_rb5:
                    getInspectionItemListByStatus("4");
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initListView() {
        mAdapter = new ListCommonAdapter<InspectionTaskItem>(mContext, R.layout.item_project_list, inspectionTaskItems) {
            @Override
            protected void convert(ListViewHolder viewHolder, InspectionTaskItem inspectionTaskItem, int position) {
                if (inspectionTaskItem.getItemName() == null) {
                    viewHolder.setText(R.id.Plist_name, "--");
                }else {
                    viewHolder.setText(R.id.Plist_name, inspectionTaskItem.getItemName()); //名称}}
                }
                viewHolder.setText(R.id.Plist_id, "子项编号："+inspectionTaskItem.getId());//id
                viewHolder.setText(R.id.Plist_type, "点位数量："+inspectionTaskItem.getDescription());//类型
                viewHolder.setText(R.id.Plist_price, "点位位置："+inspectionTaskItem.getUpdateTime());//价格
            }

        };
        mAdapter.notifyDataSetInvalidated();
        lv_task_items.setAdapter(mAdapter);
        lv_task_items.setOnItemClickListener((parent, view, position, id) -> {
            //进入任务子项
            Bundle bundle = new Bundle();

            int status = inspectionTaskItems.get(position).getStatus();
           if(statusDo.equals("2-2")&&status==1){
               bundle.putString("inspectionItemId",String.valueOf(inspectionTaskItems.get(position).getId()));
               bundle.putString("statusDo",statusDo);
           }else {
               bundle.putString("inspectionItemId",String.valueOf(inspectionTaskItems.get(position).getId()));
               bundle.putString("statusDo","no");
           }
          //
            // bundle.putString("projectId","1");
            BaseUtils.getInstence().intent(mContext, InspectionItemDetailActivity.class,bundle);
        });
    }

    private void notifylistDataChanged() {
        if (inspectionTaskItems.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
        }
        mAdapter.notifyDataSetChanged();
    }
    private void getAllInspectionItemList() {
        if (inspectionId != null) {
            InspectionItemListImcRequest inspectionItemListImcRequest=new InspectionItemListImcRequest();
            inspectionItemListImcRequest.setTaskId(Long.parseLong(inspectionId));
            Net.instance.getInspectionItemListImc(inspectionItemListImcRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<AllFinishedInspectionItemResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("ErrorGetInspectionItem", System.currentTimeMillis() + "");
                            e.printStackTrace();
                            notifylistDataChanged();
                        }

                        @Override
                        public void onNext(AllFinishedInspectionItemResponse inspectionItemListResponse) {
                            if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                                inspectionTaskItems.clear();
                                if (inspectionItemListResponse.getResult().getList().size() > 0) {
                                    inspectionTaskItems.addAll(inspectionItemListResponse.getResult().getList());
                                }
                            }
                            notifylistDataChanged();
                        }
                    });

        }
    }
    private void getInspectionItemListByStatus(String status) {
        AllItemByTaskIdAndStatuRequest allItemByTaskIdAndStatuRequest = new AllItemByTaskIdAndStatuRequest();
        allItemByTaskIdAndStatuRequest.setStatus(status);
        allItemByTaskIdAndStatuRequest.setTaskId(Long.valueOf(inspectionId));
        Net.instance.getAllItemByTaskIdAndStatus(allItemByTaskIdAndStatuRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AllFinishedInspectionItemResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorInspectionListTime", System.currentTimeMillis() + "");
                        e.printStackTrace();
                        notifylistDataChanged();
                    }

                    @Override
                    public void onNext(AllFinishedInspectionItemResponse inspectionItemListResponse) {
                        if (TextUtils.equals(inspectionItemListResponse.getCode(), "200")) {
                            inspectionTaskItems.clear();
                            if (inspectionItemListResponse.getResult().getList().size() > 0) {
                                inspectionTaskItems.addAll(inspectionItemListResponse.getResult().getList());
                          //      Log.v("巡检子项列表1", inspectionItemListResponse.getResult().get(0).getId() + "");
                            }
                            notifylistDataChanged();
                        }
                    }
                });
    }

}

