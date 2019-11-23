package com.example.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.R;
import com.example.weather.activity.ContactPersonActivity;

public class ContactDetailFragment extends Fragment implements View.OnClickListener {
    private TextView ID;
    private TextView DEPART;
    private TextView PHONE_NUMBER;
    private ContactPersonActivity contactPersonActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contact_detail1,container,false);
        PHONE_NUMBER=view.findViewById(R.id.contact_detail_phone);
        DEPART=view.findViewById(R.id.contact_detail_depart);
       ID=view.findViewById(R.id.contact_detail_id);
     contactPersonActivity=(ContactPersonActivity) getActivity();
        //ID.setText("2019140282");
        ID.setText(contactPersonActivity.contact_id);
        DEPART.setText(contactPersonActivity.depart);
       PHONE_NUMBER.setText(contactPersonActivity.contact_phone);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnListener();
    }

    private void setOnListener() {
        PHONE_NUMBER.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contact_detail_phone:
                call();
        }
    }

    private void call() {
        Toast.makeText(getContext(),"you called phone number",Toast.LENGTH_LONG);
    }
}
