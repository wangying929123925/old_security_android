package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.MessageListRequest;
import com.example.ananops_android.db.MessageListResponse;
import com.example.ananops_android.entity.MessageContent;
import com.example.ananops_android.gson.MessageBodyGson;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.SPUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserMessageFragment extends Fragment  {
    private View mRootView;
    private Context mContext;
    //private MessageFragmentController controller;
    private RadioGroup rg_tab;//顶部选择框
    private ListView mListView;
    private List<MessageContent> messageContents = new ArrayList<>();
    private ListCommonAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_uer_message, container, false);
        mContext = getActivity();
        mListView = mRootView.findViewById(R.id.message_list);
        //controller = MessageFragmentController.getInstance(this, R.id.id_fragment_message);
       // controller.showFragment(0);
        rg_tab = mRootView.findViewById(R.id.rg_tab);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      //  initView();
        initDatas(null);
        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_all:
                     initDatas(null);
                        break;
                    case R.id.rb_repair:
                        //controller.showFragment(0);
                        initDatas("MDMC_TOPIC");
                        break;
                    case R.id.rb_inspection:
                        initDatas("IMC_TOPIC");
                       // controller.showFragment(1);
                        break;
                    case R.id.rb_pay:
                     //   controller.showFragment(2);
                        initDatas("PAY_TOPIC");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initDatas(String topicType) {
        //获取列表
        MessageListRequest messageListRequest = new MessageListRequest();
        messageListRequest.setMessageTopic(topicType);
        messageListRequest.setUserId(Long.valueOf(SPUtils.getInstance(mContext).getString("user_id", "")));
        Net.instance.getMessageList(messageListRequest,SPUtils.getInstance(mContext).getString("Token", " "))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("ErrorGetMessageList", System.currentTimeMillis() + "");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MessageListResponse messageListResponse) {
                        if (TextUtils.equals(messageListResponse.getCode(),"200")) {
                            //messageContents.clear();
                            messageContents = messageListResponse.getResult().getList();
//                            if (messageListResponse.getResult().getList().size() > 0) {
//                                for (String s : messageListResponse.getResult().getList()) {
//                                    Gson gson = new Gson();
//                                    MessageContent messageEntity = gson.fromJson(s, MessageContent.class);
//                                    messageContents.add(messageEntity);
//                                }
//                            }
                           initView();
                           // mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    private void initView() {
        mAdapter=new ListCommonAdapter<MessageContent>(mContext,R.layout.item_user_message,messageContents) {
            @Override
            protected void convert(ListViewHolder viewHolder, MessageContent messageContent, int position) {
                Gson gson = new Gson();
                MessageBodyGson messageEntity = gson.fromJson(messageContent.getMessageBody(), MessageBodyGson.class);
                viewHolder.setText(R.id.message_item_text, String.valueOf(messageEntity.getMsgBodyDto().getStatusMsg()));
                if (messageContent.getMessageTopic().equals("MDMC_TOPIC")) {
                    viewHolder.setImageResource(R.id.order_message_img,R.drawable.ic_message_orange);
                    viewHolder.setText(R.id.message_item_title,"您的维修维修工单状态有更新");
                } else if (messageContent.getMessageTopic().equals("IMC_TOPIC")) {
                    viewHolder.setText(R.id.message_item_title,"您的巡检工单状态有更新");
                    viewHolder.setImageResource(R.id.order_message_img,R.drawable.ic_message);
                } else {
                    viewHolder.setImageResource(R.id.order_message_img,R.drawable.ic_message_system);
                    viewHolder.setText(R.id.message_item_title,"您的支付状态有更新");
                }
            }
        };
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("messageId",String.valueOf(messageContents.get(position).getId()));
            }
        });
    }

}
