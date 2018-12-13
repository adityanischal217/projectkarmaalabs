package com.example.aditya.projectkarma.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aditya.projectkarma.Adapter.CustomerdataAdapter;
import com.example.aditya.projectkarma.LocalDatabase.DatabaseCURDOperations;
import com.example.aditya.projectkarma.Model.CustomerDetails;
import com.example.aditya.projectkarma.Model.UserDetails;
import com.example.aditya.projectkarma.R;
import com.example.aditya.projectkarma.UTILS.AppConstantKeys;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {
    DatabaseCURDOperations mdatabase;
    UserDetails userDetails;
    List<CustomerDetails> customerDetails;
    @BindView(R.id.rv)
    RecyclerView mRecylcerview;
    @BindView(R.id.id_tv)
    TextView mSrno;
    @BindView(R.id.name_tv)
    TextView name;
    @BindView(R.id.nodata_tv)
    TextView noDATA;
    private Context mContext;
    private Activity mActivity;

    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        ButterKnife.bind(this, view);
        init();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        return view;
    }

    private void init() {
        mContext = getContext();
        mActivity = getActivity();
        mdatabase = new DatabaseCURDOperations(getActivity());
        userDetails = (UserDetails) getArguments().get(AppConstantKeys.USERDETAILS);
        String email = userDetails.getUserEmail();
        customerDetails = mdatabase.getCustomerInfo1(email);
        if (customerDetails.size() == 0) {
            mSrno.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            noDATA.setVisibility(View.VISIBLE);

        } else {
            setRecyclerView();

        }

    }

    private void setRecyclerView() {
        CustomerdataAdapter customerDataAdapter = new CustomerdataAdapter(mContext, customerDetails);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecylcerview.setLayoutManager(layoutManager);
        mRecylcerview.setItemAnimator(new DefaultItemAnimator());
        mRecylcerview.setAdapter(customerDataAdapter);
    }
}


