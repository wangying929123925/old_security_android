package com.example.ananops_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.ananops_android.R;
import com.example.ananops_android.activity.InspectionItemListActivity;
import com.example.ananops_android.util.BaseUtils;
import com.example.ananops_android.util.SPUtils;
import org.jetbrains.annotations.Nullable;

public class InspectionItemClassifyFragment extends Fragment implements View.OnClickListener {
    private Button btn_un_distributed;
    private Button btn_un_accept;
    private Button btn_item_doing;
    private Button btn_item_done;
    private Button btn_un_confirmed;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;
    private RelativeLayout rl4;
    private RelativeLayout rl5;
    private String inspectionId;
    private String statusDo;
    private Context mContext;

    public static InspectionItemClassifyFragment newInstance(String inspectionId,String statusDo) {
        InspectionItemClassifyFragment inspectionItemClassifyFragment = new InspectionItemClassifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("inspectionId",inspectionId);
        bundle.putString("statusDo",statusDo);
        inspectionItemClassifyFragment.setArguments(bundle);
        return inspectionItemClassifyFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, @android.support.annotation.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspection_item_classify, container, false);
        mContext = getActivity();
        Bundle bundle = getArguments();
        if (bundle != null) {
            inspectionId = bundle.getString("inspectionId");
            statusDo = bundle.getString("statusDo");
        }
        btn_un_distributed = view.findViewById(R.id.btn_un_distributed);
        btn_un_accept = view.findViewById(R.id.btn_un_accept);
        btn_item_doing = view.findViewById(R.id.btn_item_doing);
        btn_item_done = view.findViewById(R.id.btn_item_done);
        btn_un_confirmed = view.findViewById(R.id.btn_un_confirmed);
        rl1 = view.findViewById(R.id.rl1);
        rl2 = view.findViewById(R.id.rl2);
        rl3 = view.findViewById(R.id.rl3);
        rl4 = view.findViewById(R.id.rl4);
        rl5 = view.findViewById(R.id.rl5);
        setListener();
        initView();
        return view;
    }

    private void initView() {
        switch (SPUtils.getInstance(mContext).getInt("role_num",1)){
            case 1:
                rl1.setVisibility(View.INVISIBLE);
                rl1.setVisibility(View.INVISIBLE);
                rl1.setVisibility(View.INVISIBLE);
                rl1.setVisibility(View.INVISIBLE);
                rl1.setVisibility(View.INVISIBLE);
                break;
            case 3:
                rl1.setVisibility(View.INVISIBLE);
                rl1.setVisibility(View.VISIBLE);
                rl1.setVisibility(View.VISIBLE);
                rl1.setVisibility(View.INVISIBLE);
                rl1.setVisibility(View.INVISIBLE);
                break;
                default:
                    rl1.setVisibility(View.VISIBLE);
                    rl1.setVisibility(View.VISIBLE);
                    rl1.setVisibility(View.VISIBLE);
                    rl1.setVisibility(View.VISIBLE);
                    rl1.setVisibility(View.VISIBLE);
                    break;
        }
    }
    private void setListener() {
        btn_un_distributed.setOnClickListener(this);
        btn_un_accept.setOnClickListener(this);
        btn_item_doing.setOnClickListener(this);
        btn_item_done.setOnClickListener(this);
        btn_un_confirmed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_un_distributed:
                if (statusDo.equals("2-2")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("inspectionItemId", inspectionId);
                    bundle.putString("statusDo", "2-2");
                    bundle.putString("status","1");
                    BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class, bundle);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("inspectionItemId", inspectionId);
                    bundle.putString("statusDo", "no");
                    bundle.putString("status","1");
                    BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class, bundle);
                }
                break;
            case R.id.btn_un_accept:
                Bundle bundle0 = new Bundle();
                bundle0.putString("inspectionItemId", inspectionId);
                bundle0.putString("statusDo", "no");
                bundle0.putString("status","2");
                BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class, bundle0);
                break;
            case R.id.btn_item_doing:
                Bundle bundle1 = new Bundle();
                bundle1.putString("inspectionItemId", inspectionId);
                bundle1.putString("statusDo", "no");
                bundle1.putString("status","3");
                BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class, bundle1);
                break;
            case R.id.btn_item_done:
                Bundle bundle2 = new Bundle();
                bundle2.putString("inspectionItemId", inspectionId);
                bundle2.putString("statusDo", "no");
                bundle2.putString("status","4");
                BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class, bundle2);
                break;
            case R.id.btn_un_confirmed:
                Bundle bundle3 = new Bundle();
                bundle3.putString("inspectionItemId", inspectionId);
                bundle3.putString("statusDo", "no");
                bundle3.putString("status","5");
                BaseUtils.getInstence().intent(getContext(), InspectionItemListActivity.class, bundle3);
                break;
                default:
                    break;
        }
    }
}
