package com.developer.chithlal.mjc.app.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.developer.chithlal.mjc.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void getLoginData() {

    }

    @Override
    public void showValidationMessage(String message) {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure(String mMessage) {

    }
}
