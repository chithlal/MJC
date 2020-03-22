package com.developer.chithlal.mjc.app.signup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.Login.LoginConstants;
import com.developer.chithlal.mjc.app.util.Constants;


import java.util.List;

public class  SignupActivity extends AppCompatActivity implements SignUpContract.View{

    //View objects;
    private CardView mSignupButton;
    private EditText mUserName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPhone;
    private Toolbar mToolBar;
    private SignupPresenter mSignupPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //view initialization

        mSignupButton = findViewById(R.id.signup_cv_signup_bt);
        mUserName = findViewById(R.id.et_signup_username);
        mEmail = findViewById(R.id.et_signup_email);
        mPhone = findViewById(R.id.et_signup_phone);
        mPassword = findViewById(R.id.et_signup_password);
        mToolBar = findViewById(R.id.signup_toolbar);

        //intilize toolbar

        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolBar.setTitle("");
        mToolBar.setEnabled(true);
        setSupportActionBar(mToolBar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);

        mSignupPresenter = new SignupPresenter(this);


        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignupPresenter.tryRegister();

            }
        });
    }

    @Override
    public SignUpEvent getUserData() {

        SignUpEvent mSignupEvent = new SignUpEvent();
        if(mUserName!=null){
            mSignupEvent.setUsername(mUserName.getText().toString());
        }
        if(mPassword!=null){
            mSignupEvent.setPassword(mPassword.getText().toString());
        }
        if(mPhone!=null){
            mSignupEvent.setPhone(mPhone.getText().toString());
        }
        if(mEmail!=null){
            mSignupEvent.setEmail(mEmail.getText().toString());
        }
        return mSignupEvent;
    }

    @Override
    public void setError(SignUpValidationError errorMessage) {

        if(!errorMessage.getNameError().equals( Constants.NO_ERROR))
            mUserName.setError(errorMessage.getNameError());
        if(!errorMessage.getEmailError().equals( Constants.NO_ERROR))
            mEmail.setError(errorMessage.getEmailError());
        if(!errorMessage.getPhoneError().equals( Constants.NO_ERROR))
            mPhone.setError(errorMessage.getPhoneError());
        if(!errorMessage.getPasswordError().equals( Constants.NO_ERROR))
            mPassword.setError(errorMessage.getPasswordError());
    }

    @Override
    public void onRegistrationCompleted(int status) {
        finish();
    }

    @Override
    public void onRegistrationFailed(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();

    }
}
