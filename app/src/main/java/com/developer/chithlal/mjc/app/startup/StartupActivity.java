package com.developer.chithlal.mjc.app.startup;

import static com.developer.chithlal.mjc.app.util.Constants.INVALID_DATA_ERROR;
import static com.developer.chithlal.mjc.app.util.Constants.user_details_shared_pref_USER_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.Base.BaseActivity;
import com.developer.chithlal.mjc.app.Login.LoginActivity;
import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.util.SharedPreferenceManger;
import com.developer.chithlal.mjc.databinding.ActivityStartupBinding;
import com.developer.chithlal.mjc.root.App;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;

import javax.inject.Inject;

public class StartupActivity extends AppCompatActivity implements StartupContract.View{

    private ActivityStartupBinding mBinding;
    @Inject
    StartupContract.Presenter mPresenter;
    private String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityStartupBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        ((App)getApplication()).getAppComponent().injectStartupActivity(this);
        mPresenter.onAppStartup(this);
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        showMessage("Login to continue!");
        startActivity(intent);
        finish();
    }

    @Override
    public void directLogin(User user) {
        AccountManager accountManager = new AccountManager(this);
        accountManager.loginUser(user);
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}