package com.developer.chithlal.mjc.app.Login;

import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.firebase.LoginUtil;

public class LoginModel implements LoginContract.Model {
    private LoginContract.Presenter mMLoginPresenter;

    public LoginModel(LoginContract.Presenter mLoginPresenter) {
        mMLoginPresenter = mLoginPresenter;
    }

    @Override
    public String login(LoginEvent mLoginEvent) {
        User user = new User("Chithlal");
        user.setPhone("8714236584");
        user.setUserMode(true);
        LoginUtil loginUtil = new LoginUtil(this);
        loginUtil.tryLogin(mLoginEvent);
        return "Success";
    }

    @Override
    public void onLoginSuccess(User user) {
        mMLoginPresenter.onLoginSuccess(user);
    }

    @Override
    public void onLoginFailure(String message) {
        mMLoginPresenter.onLoginFailure(message);
    }
}
