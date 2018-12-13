package com.example.aditya.projectkarma.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.aditya.projectkarma.Adapter.SliderAdapter;
import com.example.aditya.projectkarma.Fragment.EntryFragment;
import com.example.aditya.projectkarma.Fragment.ShowFragment;
import com.example.aditya.projectkarma.Model.UserDetails;
import com.example.aditya.projectkarma.R;
import com.example.aditya.projectkarma.UTILS.AppConstantKeys;
import com.example.aditya.projectkarma.UTILS.CheckPermission;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.SESSION;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.SHAREDDATA;

public class MainDashboard extends AppCompatActivity {
    private static final String TAG = "TAG";
    @BindView(R.id.toolbaar)
    Toolbar mToolbar;
    private static int NAV_ITEM_INDEX = 0;
    private Fragment fragment;
    UserDetails userDetails;
    SharedPreferences pref;
    List<Integer> imageSlider;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.indicator)
    TabLayout mIndicator;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash_board);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        // context = getApplicationContext();
        this.context = MainDashboard.this;
        CheckPermission.checkAndRequestPermissions(context);
        setToolBar();
        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        // userDetails = (UserDetails) getIntent().getSerializableExtra(AppConstantKeys.USERDETAILS);
        pref = getApplicationContext().getSharedPreferences(SHAREDDATA, 0);
        Gson gson = new Gson();
        String json = pref.getString(AppConstantKeys.USERDETAILS, "");
        userDetails = gson.fromJson(json, UserDetails.class);
        imageSlideTimer();
    }

    private void imageSlideTimer() {
        imageSlider = new ArrayList<>();
        imageSlider.add(R.drawable.karmaa);
        imageSlider.add(R.drawable.karmaimageslider);
        imageSlider.add(R.drawable.karmaa);
        mViewPager.setAdapter(new SliderAdapter(context, imageSlider));
        mIndicator.setupWithViewPager(mViewPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 3000);
    }

    private void setToolBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_enty:
                Bundle bundle = new Bundle();
                fragment = new EntryFragment();
                bundle.putSerializable(AppConstantKeys.USERDETAILS, userDetails);
                fragment.setArguments(bundle);
                openFragment(fragment);
                NAV_ITEM_INDEX = 1;
                //   adapter.notifyDataSetChanged();
                return true;
            case R.id.action_show:
                Bundle bundle1 = new Bundle();
                fragment = new ShowFragment();
                bundle1.putSerializable(AppConstantKeys.USERDETAILS, userDetails);
                fragment.setArguments(bundle1);
                openFragment(fragment);
                NAV_ITEM_INDEX = 2;
                //   adapter.notifyDataSetChanged();
                return true;
            case R.id.action_logout:
                Intent intent = new Intent(this, LoginActivity.class);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove(SESSION);
                editor.apply();
                startActivity(intent);
                finish();


                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe, fragment);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @OnClick({R.id.weburl_tv})
    public void onClickWebrl() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.karmaalab.com/"));
        startActivity(browserIntent);
    }

    @OnClick({R.id.contactme_tv})
    public void onClickOpenPhone() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:8048909264"));
        startActivity(callIntent);
    }


    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mViewPager.getCurrentItem() < imageSlider.size() - 1) {
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    } else {
                        mViewPager.setCurrentItem(0);
                    }


                }
            });
        }
    }


}
