package com.example.aditya.projectkarma.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aditya.projectkarma.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.CUSTOMER_AGE;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.CUSTOMER_DOB;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.CUSTOMER_NAME;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.LATITUTE;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.LONGITUTE;

public class CustomerdetailsActivity extends AppCompatActivity {
    String name, age, dob, lat, longt;
    @BindView(R.id.cnamevalue_tv)
    TextView mName;
    @BindView(R.id.cagevalue_tv)
    TextView mAge;
    @BindView(R.id.cdobvalue_tv)
    TextView mDob;
    @BindView(R.id.clongvalue_tv)
    TextView mLAT;
    @BindView(R.id.latvalue_tv)
    TextView mLONG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdetails);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        name = getIntent().getStringExtra(CUSTOMER_NAME);
        age = getIntent().getStringExtra(CUSTOMER_AGE);
        dob = getIntent().getStringExtra(CUSTOMER_DOB);
        lat = getIntent().getStringExtra(LATITUTE);
        longt = getIntent().getStringExtra(LONGITUTE);
        mName.setText(name);
        mAge.setText(age);
        mDob.setText(dob);
        mLAT.setText(lat);
        mLONG.setText(longt);

    }
}
