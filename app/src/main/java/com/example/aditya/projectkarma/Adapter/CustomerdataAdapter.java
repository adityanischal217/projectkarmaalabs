package com.example.aditya.projectkarma.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aditya.projectkarma.Activity.CustomerdetailsActivity;
import com.example.aditya.projectkarma.Model.CustomerDetails;
import com.example.aditya.projectkarma.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.CUSTOMER_AGE;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.CUSTOMER_DOB;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.CUSTOMER_NAME;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.LATITUTE;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.LONGITUTE;


public class CustomerdataAdapter extends RecyclerView.Adapter<CustomerdataAdapter.OrderDetailItemHolder> {


    private static final String CURRENT_TAG = CustomerdataAdapter.class.getName();
    Context mContext;
    List<CustomerDetails> list;


    public CustomerdataAdapter(Context mContext, List<CustomerDetails> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public CustomerdataAdapter.OrderDetailItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutadapter, parent, false);
        return new OrderDetailItemHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final CustomerdataAdapter.OrderDetailItemHolder holder, int position) {
        String name = list.get(position).getCustomerName();
        holder.mId.setText(position + 1 + ".");
        holder.mName.setText(name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderDetailItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_tv)
        TextView mName;
        @BindView(R.id.id_tv)
        TextView mId;

        public OrderDetailItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }

        @OnClick({R.id.linearlayout})
        public void onpartUrlClick() {
            int position = getAdapterPosition();
            String name = list.get(position).getCustomerName();
            String age = list.get(position).getCustomersAge();
            String dob = list.get(position).getCustomersDob();
            String lat = list.get(position).getCustLat();
            String lon = list.get(position).getCustLong();
            Intent intent = new Intent(mContext, CustomerdetailsActivity.class);
            intent.putExtra(CUSTOMER_NAME, name);
            intent.putExtra(CUSTOMER_AGE, age);
            intent.putExtra(CUSTOMER_DOB, dob);
            intent.putExtra(LATITUTE, lat);
            intent.putExtra(LONGITUTE, lon);
            mContext.startActivity(intent);


        }
    }


}