package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ananops_android.R;
import com.example.ananops_android.activity.MessageDetailActivity;
import com.example.ananops_android.adapter.ListCommonAdapter;
import com.example.ananops_android.adapter.ListViewHolder;
import com.example.ananops_android.db.CodeMessageResponse;
import com.example.ananops_android.db.MessageListRequest;
import com.example.ananops_android.db.MessageListResponse;
import com.example.ananops_android.db.MessageStatusChangeRequest;
import com.example.ananops_android.entity.MessageContent;
import com.example.ananops_android.gson.MessageBodyGson;
import com.example.ananops_android.net.Net;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UserMessageFragment extends LazyFragment2  {
    private View mRootView;
    private Context mContext;
    //private MessageFragmentController controller;
    private RadioGroup rg_tab;//顶部选择框
    private ListView mListView;
    private List<MessageContent> messageContents = new ArrayList<>();
    private ListCommonAdapter mAdapter;
    private LinearLayout noResult;
    private SmartRefreshLayout mRefreshLayout;
    private String messageType;
    private int curPage = 1;//当前页
    private int pageSize = 10;
    //用于记录当前是何种状态，在请求完数据之后根据不同的状态进行不同的操作
    private static final int STATE_INIT = 0;
    private static final int STATE_REFRESH = 1;
    private static final int STATE_LOAD_MORE = 2;
    //用于记录当前的状态
    private int curState = STATE_INIT;
    //用于记录总页数，在上拉的时候判断还有没有更多数据
    private int totalPage = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

     //   return mRootView;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_uer_message;
    }

    @Override
    protected void initView(View view) {
      //  mRootView = inflater.inflate(R.layout.activity_uer_message, container, false);
        mContext = getActivity();
        mListView = view.findViewById(R.id.message_list);
        //controller = MessageFragmentController.getInstance(this, R.id.id_fragment_message);
        // controller.showFragment(0);
        rg_tab = view.findViewById(R.id.rg_tab);
        noResult = view.findViewById(R.id.no_result_text);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        initView();
        rg_tab.setOnCheckedChangeListener((group, checkedId) -> {
            curPage=1;
            curState = STATE_INIT;
            switch (checkedId) {
                case R.id.rb_all:
                    messageType = null;
                    initDatas(null);
                    break;
                case R.id.rb_repair:
                    //controller.showFragment(0);
                    messageType = "MDMC_TOPIC";
                    initDatas("MDMC_TOPIC");
                    break;
                case R.id.rb_inspection:
                    messageType = "IMC_TOPIC";
                    initDatas("IMC_TOPIC");
                    // controller.showFragment(1);
                    break;
                case R.id.rb_pay:
                    //   controller.showFragment(2);
                    messageType = "PAY_TOPIC";
                    initDatas("PAY_TOPIC");
                    break;
                default:
                    break;
            }
        });
        //越界回弹
        mRefreshLayout.setEnableOverScrollBounce(false);
        //在刷新或者加载的时候不允许操作视图
        mRefreshLayout.setDisableContentWhenRefresh(true);
        mRefreshLayout.setDisableContentWhenLoading(true);
        //监听列表在滚动到底部时触发加载事件（默认true）
        mRefreshLayout.setEnableAutoLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                curPage = 1;
                curState = STATE_REFRESH;
                initDatas(messageType);
                mRefreshLayout.finishRefresh();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                curPage++;
                curState = STATE_LOAD_MORE;
                //  Log.i(TAG, "onLoadMore: curPage"+curPage);
                // Log.i(TAG, "onLoadMore: totalPage"+totalPage);
                if (curPage <= totalPage) {
                    initDatas(messageType);
                    //  more
                } else {
                    Toast.makeText(mContext, "没有更多啦O(∩_∩)O", Toast.LENGTH_SHORT).show();
                }
                mRefreshLayout.finishLoadMore();
            }
        });

    }

    @Override
    protected void onFragmentFirstVisible() {
        curPage=1;
        initDatas(null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();
        curPage=1;
        initDatas(null);
    }

    @Override
    protected void onFragmentPause() {
        super.onFragmentPause();
    }

    private void initDatas(String topicType) {
        //获取列表
        MessageListRequest messageListRequest = new MessageListRequest();
        messageListRequest.setMessageTopic(topicType);
        messageListRequest.setStatus(null);
        messageListRequest.setPageNum(curPage);
        messageListRequest.setPageSize(pageSize);
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
                    //    notifylistDataChanged();
                    }

                    @Override
                    public void onNext(MessageListResponse messageListResponse) {
                        totalPage=messageListResponse.getResult().getPages();
                        if (TextUtils.equals(messageListResponse.getCode(),"200")) {
                            List<MessageContent> messageContentsAdd = new ArrayList<>();
                          // messageContents.clear();
                            messageContentsAdd.addAll(messageListResponse.getResult().getList());
                            notifylistDataChanged(messageContentsAdd);
//                           if(messageListResponse.getResult().getList().size()>0){
//                               messageContents.addAll(messageListResponse.getResult().getList());
//                           }
                         //   messageContents = messageListResponse.getResult().getList();
//                            if (messageListResponse.getResult().getList().size() > 0) {
//                                for (String s : messageListResponse.getResult().getList()) {
//                                    Gson gson = new Gson();
//                                    MessageContent messageEntity = gson.fromJson(s, MessageContent.class);
//                                    messageContents.add(messageEntity);
//                                }
//                            }
                        //   initView();
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
                    viewHolder.setText(R.id.message_item_title,"维修维护消息");
                } else if (messageContent.getMessageTopic().equals("IMC_TOPIC")) {
                    viewHolder.setText(R.id.message_item_title,"巡检消息");
                    viewHolder.setImageResource(R.id.order_message_img,R.drawable.ic_message);
                } else {
                    viewHolder.setImageResource(R.id.order_message_img,R.drawable.ic_message_system);
                    viewHolder.setText(R.id.message_item_title,"支付消息");
                }
                if (messageContent.getStatus() == 0) {
                    viewHolder.setText(R.id.message_item_status, "未读");
                    viewHolder.setTextColor(R.id.message_item_status,getResources().getColor(R.color.red));
                } else {
                    viewHolder.setText(R.id.message_item_status, "已读");
                    viewHolder.setTextColor(R.id.message_item_status,getResources().getColor(R.color.blue));

                }
            }
        };
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageContent messageContent = messageContents.get(position);
                Gson gson = new Gson();
                MessageBodyGson messageEntity = gson.fromJson(messageContent.getMessageBody(), MessageBodyGson.class);
                MessageStatusChangeRequest messageStatusChangeRequest = new MessageStatusChangeRequest();
                messageStatusChangeRequest.setStatus(1);
                messageStatusChangeRequest.setMessageId(messageContent.getId());
                Net.instance.changeMessageStatus(messageStatusChangeRequest,SPUtils.getInstance(mContext).getString("Token"," "))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<CodeMessageResponse>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.v("UserMessageFragment","修改消息状态失败");
                                Bundle bundle = new Bundle();
                                bundle.putString("messageType",messageContent.getMessageTopic());
                                bundle.putString("messageContent",messageEntity.getMsgBodyDto().getStatusMsg());
                                bundle.putString("orderId",messageEntity.getMsgBodyDto().getTaskId());
                                BaseUtils.getInstence().intent(mContext, MessageDetailActivity.class,bundle);
                            }

                            @Override
                            public void onNext(CodeMessageResponse codeMessageResponse) {
                                if (TextUtils.equals(codeMessageResponse.getCode(), "200")) {
                                    Log.v("UserMessageFragment","修改消息状态成功");
                                }
                                Bundle bundle = new Bundle();
                                bundle.putString("messageType",messageContent.getMessageTopic());
                                bundle.putString("messageContent",messageEntity.getMsgBodyDto().getStatusMsg());
                                bundle.putString("orderId",messageEntity.getMsgBodyDto().getTaskId());
                                BaseUtils.getInstence().intent(mContext, MessageDetailActivity.class,bundle);
                            }
                        });


            }
        });
    }
    private void notifylistDataChanged(List<MessageContent> messageContentsAdd) {
        switch (curState) {
            case STATE_INIT:
                messageContents.clear();
                messageContents.addAll(messageContentsAdd);
                mAdapter.notifyDataSetChanged();
                break;
            case STATE_LOAD_MORE:
                int lastPosition = messageContents.size();
                messageContents.addAll(lastPosition,messageContentsAdd);
                mAdapter.notifyDataSetChanged();
                break;
            case STATE_REFRESH:
                messageContents.clear();
                messageContents.addAll(messageContentsAdd);
                mAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        if (messageContents.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
        }
        mAdapter.notifyDataSetChanged();
    }
}
