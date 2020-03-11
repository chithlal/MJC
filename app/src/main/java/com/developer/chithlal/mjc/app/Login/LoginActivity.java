package com.developer.chithlal.mjc.app.Login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    //USER_TYPE
    private int USER_TYPE;

    private EditText mUserName;
    private EditText mPassword;
    private CardView mLoginButton;
    private TextView mRegister;
    private TextView mErrorText;
    private Toolbar mToolbar ;
    private CardView mCardUserName;
    private CardView mCardPassword;


    //presenter
    LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserName = findViewById(R.id.et_login_username);
        mPassword = findViewById(R.id.et_login_password);
        mLoginButton = findViewById(R.id.login_cv_login_bt);
        mErrorText = findViewById(R.id.login_tv_error_message);
        mRegister  = findViewById(R.id.login_tv_register_text);
        mToolbar = findViewById(R.id.login_toolbar);
        mCardPassword = findViewById(R.id.login_cv_password);
        mCardUserName = findViewById(R.id.login_cv_username);

        //Toolbar setup
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
        mActionBar.setDisplayHomeAsUpEnabled(true);
        //initiate presenter with view context
        mLoginPresenter = new LoginPresenter(this);

        //Listener for login action
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.tryLogin();
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(signupIntent);
            }
        });
    }

    @Override
    public LoginEvent getLoginData() {

        LoginEvent mLoginEvent = new LoginEvent();
        if (mUserName!=null&& mPassword != null){

            mLoginEvent.setUsername(mUserName.getText().toString());
            mLoginEvent.setPassword(mPassword.getText().toString());
            mLoginEvent.setLoginType(USER_TYPE);
        }


        return mLoginEvent;
    }

    @Override
    public void showValidationMessage(String userNameMessage,String passwordMessage) {
        if(!userNameMessage.equals(LoginConstants.NO_ERROR)){
            mUserName.setError(userNameMessage);


        }
        if(!passwordMessage.equals(LoginConstants.NO_ERROR)){
            mPassword.setError(passwordMessage);
            mErrorText.setText("");
            mErrorText.setText(passwordMessage);
        }

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure(String mMessage) {
        mErrorText.setText("");
        mErrorText.setText(mMessage);

    }
}
