package com.example.aditya.projectkarma.Fragment;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.aditya.projectkarma.Activity.MapsActivity;
import com.example.aditya.projectkarma.Model.CustomerDetails;
import com.example.aditya.projectkarma.Model.UserDetails;
import com.example.aditya.projectkarma.R;
import com.example.aditya.projectkarma.UTILS.AppConstantKeys;
import com.example.aditya.projectkarma.UTILS.InputValidation;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class EntryFragment extends Fragment {
    @BindView(R.id.et_customer_name)
    EditText mCustomerNameEt;
    @BindView(R.id.et_customer_age)
    EditText mCustomerAgeEt;
    @BindView(R.id.et_dob)
    EditText mdob;
    private DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay, mHour, mMinute;
    UserDetails userDetails;
    //  List<CustomerDetails> customerDetailslist = new ArrayList<>();

    CustomerDetails customerDetails;

    public EntryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);
        ButterKnife.bind(this, view);
        init();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        return view;
    }

    private void init() {
        userDetails = (UserDetails) getArguments().get(AppConstantKeys.USERDETAILS);
        customerDetails = new CustomerDetails();
    }

    @OnClick({R.id.et_dob})
    public void submit() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mdob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }

    private boolean isInputValid() {
        if (InputValidation.validateCName(mCustomerNameEt)
                && InputValidation.validateCage(mCustomerAgeEt)
                && InputValidation.validateCdob(mdob)) {

            return true;
        }
        return false;
    }

    @OnClick({R.id.getlocation})

    public void location() {
        if (isInputValid()) {
            String c_name = mCustomerNameEt.getText().toString().trim();
            String c_age = mCustomerAgeEt.getText().toString().trim();
            String c_dob = mdob.getText().toString().trim();
            customerDetails.setUserEmail(userDetails.getUserEmail());
            customerDetails.setCustomerName(c_name);
            customerDetails.setCustomersAge(c_age);
            customerDetails.setCustomersDob(c_dob);
            Intent i = new Intent(getActivity(), MapsActivity.class);
            i.putExtra(AppConstantKeys.CUSTOMERDETAILS, customerDetails);
            startActivity(i);
        }

    }


}
