package com.example.ananops_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.annotation.ViewInject;
import com.example.ananops_android.db.InvoiceListRequest;
import com.example.ananops_android.db.InvoiceListResponse;
import com.example.ananops_android.entity.InvoiceItemInfo;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.AnnotationUtils;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
import com.example.ananops_android.view.EditTextWithDel;
import com.example.ananops_android.view.InspectionItemFinishWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InspectionInvoiceListActivity extends BaseActivity {
    private String inspectionItemId;
    private Context mContext;

    @ViewInject(R.id.lv_contact)
    private ListView lv_invoice_item;

    @ViewInject(R.id.txt_title)
    private TextView title;
    @ViewInject(R.id.no_result_text)
    private LinearLayout noResult;
    @ViewInject(R.id.et_search_contact)
    private EditTextWithDel mEtSearchName;
    @ViewInject(R.id.inspection_complete_button1)
    private Button inspection_complete;
    @ViewInject(R.id.img_back)
    private ImageView imageBack;
    @ViewInject(R.id.invoice_status)
    private TextView invoice_status;
    @ViewInject(R.id.invoice_switch)
    private Switch invoice_switch;
    private ListCommonAdapter mAdapter;
    private List<InvoiceItemInfo> invoiceItemInfos = new ArrayList<>();
    private InspectionItemFinishWindow itemFinishWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_invoice_list);
        mContext = this;
        AnnotationUtils.injectViews(this);
        Intent intent = getIntent();
        inspectionItemId = intent.getStringExtra("inspectionItemId");
       title.setText("巡检单据列表");
       initView();
        getInvoiceList("N");
    }
private void initView() {
    //  title.setText("巡检单据列表");
    imageBack.setOnClickListener(v -> finish());
    invoice_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
        if (isChecked) {
            invoice_status.setText("已填写");
            getInvoiceList("Y");
        } else {
            invoice_status.setText("未填写");
            getInvoiceList("N");
        }
    });
    mAdapter = new ListCommonAdapter<InvoiceItemInfo>(mContext, R.layout.item_inspection_invoice, invoiceItemInfos) {
        @Override
        protected void convert(ListViewHolder viewHolder, InvoiceItemInfo invoiceItemInfo, int position) {
            viewHolder.setText(R.id.invoice_tv1, String.valueOf(invoiceItemInfo.getId()));//名称
            viewHolder.setText(R.id.invoice_tv2, invoiceItemInfo.getPointName());//点位名称
            viewHolder.setText(R.id.invoice_tv3, invoiceItemInfo.getPointAddress());//点位地址
            viewHolder.setText(R.id.invoice_tv4, invoiceItemInfo.getInspcResult());//结论
            viewHolder.setText(R.id.invoice_tv5, invoiceItemInfo.getInspcDate());//巡检日期
            viewHolder.setText(R.id.invoice_tv_sign, String.valueOf(position+1));//巡检日期
        }
    };
 //   mAdapter.notifyDataSetInvalidated();
    lv_invoice_item.setAdapter(mAdapter);
    lv_invoice_item.setOnItemClickListener((parent, view, position, id) -> {
        String invoiceId = String.valueOf(invoiceItemInfos.get(position).getId());
        BaseUtils.getInstence().intent(mContext, InvoiceDetailActivity.class,"invoiceId",invoiceId);
    });
    mEtSearchName.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        showSearchResult(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
}

    private void listDataChanged() {
      //  mAdapter
        if (invoiceItemInfos.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
            inspection_complete.setVisibility(View.VISIBLE);
            inspection_complete.setOnClickListener(v -> {
                itemFinishWindow = new InspectionItemFinishWindow(inspectionItemId,mContext);
                itemFinishWindow.show();
              //  InspectionUtils.getInstence().changeInspectionItemStatus(4,inspectionItemId,mContext);
            });
        }else {
            noResult.setVisibility(View.GONE);
            inspection_complete.setVisibility(View.GONE);
        }
        mAdapter.notifyDataSetChanged();
    }
    private void getInvoiceList(String status) {
        InvoiceListRequest invoiceListRequest = new InvoiceListRequest();
        invoiceListRequest.setItemId(Long.valueOf(inspectionItemId));
        invoiceListRequest.setStatus(status);
        Net.instance.getInvoiceList(invoiceListRequest, SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InvoiceListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.v(TAG, "onError: invoiceItem"+invoiceItemInfos.size()+e);
                     //   initView();
                        listDataChanged();

                    }

                    @Override
                    public void onNext(InvoiceListResponse invoiceListResponse) {
                        if (TextUtils.equals(invoiceListResponse.getCode(),"200")) {
                            invoiceItemInfos.clear();
                            if (invoiceListResponse.getResult().size() > 0) {
                                invoiceItemInfos.addAll(invoiceListResponse.getResult());
                          //      Toast.makeText(mContext,"列表数量"+invoiceItemInfos.size(),Toast.LENGTH_SHORT).show();
                                Log.v(TAG, "onNext: invoiceItem"+invoiceItemInfos.size());

                            }
                        }
                    //    Log.i(TAG, "onNext: invoiceItem"+invoiceItemInfos.size());
                     //   initView();
                        listDataChanged();
                    }
                });
    }
    private void showSearchResult(String searchContent){
        List<InvoiceItemInfo> repairContentsFilterd=new ArrayList<>();
        if (TextUtils.isEmpty(searchContent)) {
            repairContentsFilterd = invoiceItemInfos;
        }else {
            repairContentsFilterd.clear();
            for (InvoiceItemInfo repairContent : invoiceItemInfos) {
                String name = repairContent.getPointName();
                Pattern p = Pattern.compile(searchContent);
                if(name!=null) {
                    Matcher m = p.matcher(name);
                    if (m.find()) {
                        repairContentsFilterd.add(repairContent);
                    }
                }

            }
        }
        mAdapter.updateList(repairContentsFilterd);
        if (repairContentsFilterd.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        invoice_switch.setChecked(false);
        getInvoiceList("N");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (itemFinishWindow != null) {
            itemFinishWindow.onDestroy();
        }
    }
}
