package com.developer.chithlal.mjc.app.Login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.developer.chithlal.mjc.R;


import com.developer.chithlal.mjc.app.Base.BaseActivity;
import com.developer.chithlal.mjc.app.IntroActivity.IntroActivity;
import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.firebase.ParseUser;
import com.developer.chithlal.mjc.app.signup.SignupActivity;
import com.developer.chithlal.mjc.app.util.ConnectivityUtil;
import com.developer.chithlal.mjc.app.util.ProgressViewUtil;
import com.developer.chithlal.mjc.root.App;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;

public class LoginActivity extends AppCompatActivity implements LoginContract.View,
        ParseUser.ParseListener {

    private static final String TAG = "LoginActivity";
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

    private ParseUser mParseUser;
    //presenter
    LoginPresenter mLoginPresenter;

    //progress view
    ProgressViewUtil mProgressViewUtil;
    private User mUser;

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

        mProgressViewUtil = new ProgressViewUtil(this);

        //Toolbar setup
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("Login");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
        mActionBar.setDisplayHomeAsUpEnabled(true);
        //initiate presenter with view context
        mLoginPresenter = new LoginPresenter(this);
        //Network status check
        ConnectivityUtil connectivityUtil =  new ConnectivityUtil(this);

        //Listener for login action
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectivityUtil.isNetworkConnected()) {
                    mProgressViewUtil.showLoading("Please wait..!");
                    mLoginPresenter.tryLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "No network Connected!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(getApplicationContext(), IntroActivity.class);
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
            mProgressViewUtil.cancel();


        }
        if(!passwordMessage.equals(LoginConstants.NO_ERROR)){
            mPassword.setError(passwordMessage);
            mErrorText.setText("");
            mErrorText.setText(passwordMessage);
            mProgressViewUtil.cancel();
        }

    }

    @Override
    public void onLoginSuccess(User user) {
        mUser = user;
        if (!user.isUserMode()){
            mParseUser = new ParseUser(user, this);
            mParseUser.parse();
            mProgressViewUtil.showSuccess("Welcome back!");


         }
        else {
            mProgressViewUtil.showSuccess("Welcome back!");
            mProgressViewUtil.cancel();
            postLogin(user);
            mProgressViewUtil.cancel();
            Log.d(TAG, "onLoginSuccess: Login success");
            Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onLoginFailure(String mMessage) {
        mProgressViewUtil.showFailure(mMessage);
        mProgressViewUtil.cancel();
        mErrorText.setText(mMessage);

    }

    @Override
    public Context getCurrentContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    void postLogin(User user){
        AccountManager accountManager = new AccountManager(this); //Setting the user  for the entire app session
        accountManager.loginUser(user);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onParsedDataArrived(User parsedUser) {
        Log.d(TAG, "onParsedDataArrived: data arrived"+parsedUser.getUserId());
        mUser = parsedUser;
        postLogin(parsedUser);
        mProgressViewUtil.cancel();
        Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
        startActivity(intent);
    }

    @Override
    public void onParseError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
       postLogin(mUser);
        mProgressViewUtil.cancel();
        Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
        startActivity(intent);
    }
}
