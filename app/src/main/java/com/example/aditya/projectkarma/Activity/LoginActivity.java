package com.example.aditya.projectkarma.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aditya.projectkarma.LocalDatabase.DatabaseCURDOperations;
import com.example.aditya.projectkarma.R;
import com.example.aditya.projectkarma.UTILS.AppConstantKeys;
import com.example.aditya.projectkarma.Model.UserDetails;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.SESSION;
import static com.example.aditya.projectkarma.UTILS.AppConstantKeys.SHAREDDATA;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_button)
    Button mLoginButton;
    @BindView(R.id.et_emaillogin)
    EditText mEmail;
    @BindView(R.id.et_passwordlogin)
    EditText mPassword;
    private static final String TAG = LoginActivity.class.getName();
    Context mContext;
    DatabaseCURDOperations mDatabase;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        pref = getApplicationContext().getSharedPreferences(SHAREDDATA, 0);
        mContext = getApplicationContext();
        mDatabase = new DatabaseCURDOperations(mContext);
        if (pref.getBoolean(AppConstantKeys.SESSION, false)) {
            Intent intent = new Intent(this, MainDashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SharedPreferences.Editor editor = pref.edit();
            startActivity(intent);
            finish();
        }
    }

    @OnClick({R.id.register_button})
    public void onClickSignUpTv() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @OnClick({R.id.login_button})
    public void startLogin() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (email.isEmpty()) {
            mEmail.setError("Enter Email");

        } else if (password.isEmpty()) {
            mPassword.setError("Enter Password");

        } else {
            UserDetails user = mDatabase.getUserInfo(email);
            if (user.getUserEmail().matches("")) {
                Toast.makeText(mContext, "No Record Found.Please Registered", Toast.LENGTH_SHORT).show();
            } else if (user.getUserEmail().matches(email) && user.getUserPassword().matches(password)) {
                Toast.makeText(mContext, "User Authenticated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                SharedPreferences.Editor editor = pref.edit();
                Gson gson = new Gson();
                String json = gson.toJson(user);
                editor.putBoolean(SESSION, true);
                editor.putString(AppConstantKeys.USERDETAILS, json);
                editor.apply();
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(mContext, "Incorrect Email or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
