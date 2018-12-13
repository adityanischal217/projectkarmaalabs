package com.example.aditya.projectkarma.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aditya.projectkarma.UTILS.InputValidation;
import com.example.aditya.projectkarma.LocalDatabase.DatabaseCURDOperations;
import com.example.aditya.projectkarma.R;
import com.example.aditya.projectkarma.Model.UserDetails;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {
    @BindView(R.id.et_name)
    EditText mName;
    @BindView(R.id.et_email)
    EditText mEmail;
    @BindView(R.id.et_phonenumeber)
    EditText mPhoneNumber;
    @BindView(R.id.et_password)
    EditText mPassword;
    @BindView(R.id.et_repassword)
    EditText mRePassword;
    @BindView(R.id.btn_signup)
    Button mSignup;
    @BindView(R.id.tv_loginhere)
    TextView mLogin;
    private static final String LOG_TAG = SignupActivity.class.getName();
    Context mContext;
    DatabaseCURDOperations mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mContext = getApplicationContext();
        mDatabase = new DatabaseCURDOperations(mContext);
    }


    private boolean isInputValid() {
        if (InputValidation.validateName(mName)
                && InputValidation.validateEmail(mEmail)
                && InputValidation.validateMobile(mPhoneNumber)
                && InputValidation.validateEmail(mEmail)
                && InputValidation.validatePassword(mPassword)) {
            String pass = mPassword.getText().toString();
            String rePass = mRePassword.getText().toString();
            if (!pass.equalsIgnoreCase(rePass)) {
                mRePassword.setError("Password and RePassword must be same");
            } else {
                return true;
            }
        }
        return false;
    }

    @OnClick({R.id.btn_signup})
    public void startLogin() {
        if (isInputValid()) {
            String Name = mName.getText().toString().trim();
            String email = mEmail.getText().toString().trim();
            String phonenumber = mPhoneNumber.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            UserDetails userDetails = new UserDetails(Name, email, password, phonenumber);
            mDatabase.insertUserInfo(userDetails);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setCancelable(false);
            dialog.setMessage("User Registered Successfully.Please login to continue..");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
            dialog.show();

        }
    }

    @OnClick({R.id.tv_loginhere})
    public void onClickLoginTv() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                SignupActivity.this);
        alertDialog2.setMessage(R.string.signupback);
        alertDialog2.setPositiveButton(R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        alertDialog2.setNegativeButton(R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        alertDialog2.show();
    }

}
