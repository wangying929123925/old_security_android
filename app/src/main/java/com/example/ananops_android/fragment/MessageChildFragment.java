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

import com.example.ananops_android.R;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.MessageListRequest;
import com.example.ananops_android.db.MessageListResponse;
import com.example.ananops_android.entity.MessageContent;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessageChildFragment extends Fragment {
    private ListView mListView;
    private View mRootView;
    private Context mContext;
    private List<MessageContent> messageContents = new ArrayList<>();
    private ListCommonAdapter mAdapter;
    private String topicType;

    public static MessageChildFragment newInstance(String topicType) {
        MessageChildFragment messageChildFragment = new MessageChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("topicType",topicType);
        messageChildFragment.setArguments(bundle);
        return messageChildFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_child_message, container, false);
        mContext = getActivity();
        Bundle bundle = getArguments();
        if(bundle!=null){
            topicType = bundle.getString("topicType");}
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = mRootView.findViewById(R.id.message_list);
      //  initView();
        initDatas();
    }

    private void initDatas() {//获取列表
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
                           // messageContents=messageListResponse.getResult().getList();
                            initView();
                        }
                    }
                });
    }
    private void initView() {
        mAdapter=new ListCommonAdapter<MessageContent>(mContext,R.layout.item_user_message,messageContents) {
            @Override
            protected void convert(ListViewHolder viewHolder, MessageContent messageContent, int position) {
                viewHolder.setText(R.id.message_item_title,"您的工单状态有更新");
           //     viewHolder.setText(R.id.message_item_text, String.valueOf(messageContent.getMessageBody().getMsgBodyDto().getStatusMsg()));
                if (messageContent.getMessageTopic().equals("MDMC_TOPIC")) {
                    viewHolder.setImageResource(R.id.order_message_img,R.drawable.ic_message_orange);
                } else if (messageContent.getMessageTopic().equals("IMC_TOPIC")) {
                    viewHolder.setImageResource(R.id.order_message_img,R.drawable.ic_message);
                } else {
                    viewHolder.setImageResource(R.id.order_message_img,R.drawable.ic_message_system);
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
