package com.example.ananops_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.RelacementOrderListUndoResult;
import com.example.ananops_android.entity.ReplacementOrder;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.ActivityManager;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
import com.example.ananops_android.view.EditTextWithDel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReplacementOrderListActivity extends AppCompatActivity {
    private ListView sortListView;
    private TextView title;
    private ImageView imageBack;
    private List<ReplacementOrder> replacementOrders = new ArrayList<>();
    private ListCommonAdapter mAdapter;
    private TextView noResult;
    private EditTextWithDel mEtSearchName;
    private Context mComtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComtext = this;
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_contacts_main);
        initViews();
        initDatas();
    }
    private void initDatas(){
        Net.instance.getRelacementOrderListUndo(Long.valueOf(SPUtils.getInstance().getString("user_id","")),SPUtils.getInstance().getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RelacementOrderListUndoResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("replacementOrderList", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(RelacementOrderListUndoResult relacementOrderListUndoResult) {
                        if (TextUtils.equals(relacementOrderListUndoResult.getCode(), "200")) {
                            replacementOrders.clear();
                            if (relacementOrderListUndoResult.getResult().size() > 0) {
                                replacementOrders.addAll(relacementOrderListUndoResult.getResult());
                                Log.v("未审核备件订单列表1", relacementOrderListUndoResult.getResult().get(0).getId() + "");
                                mAdapter.notifyDataSetChanged();//
                            } else {
                                Toast.makeText(mComtext, "无备件订单列表！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(mComtext, relacementOrderListUndoResult.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void initViews() {
        sortListView = findViewById(R.id.lv_contact);
        mEtSearchName = (EditTextWithDel) findViewById(R.id.et_search_contact);
        title = findViewById(R.id.txt_title);
        noResult = findViewById(R.id.no_result_text);
        imageBack = findViewById(R.id.img_back);
        title.setText("巡检任务子项");
        mAdapter = new ListCommonAdapter<ReplacementOrder>(mComtext, R.layout.item_project_list,replacementOrders) {
            @Override
            protected void convert(ListViewHolder viewHolder, ReplacementOrder replacementOrder, int position) {
                viewHolder.setText(R.id.Plist_name, String.valueOf(replacementOrder.getId()));//id
                viewHolder.setText(R.id.Plist_id, replacementOrder.getStatusMsg());//状态
                viewHolder.setText(R.id.Plist_type, replacementOrder.getUpdateTime());//时间
                viewHolder.setText(R.id.Plist_price, replacementOrder.getLastOperator());//人员
            }
        };
        mAdapter.notifyDataSetInvalidated();
        sortListView.setAdapter(mAdapter);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("orderId",String.valueOf(replacementOrders.get(position).getObjectId()));
                bundle.putString("replacementOrderId", String.valueOf(replacementOrders.get(position).getObjectId()));
                BaseUtils.getInstence().intent(mComtext, InspectionItemDetailActivity.class);
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
