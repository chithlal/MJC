package com.developer.chithlal.mjc.app.Login;

import com.developer.chithlal.mjc.app.engineer.User;

public class LoginModel implements LoginContract.Model {
    private LoginContract.Presenter mMLoginPresenter;

    public LoginModel(LoginContract.Presenter mLoginPresenter) {
        mMLoginPresenter = mLoginPresenter;
    }

    @Override
    public String login(LoginEvent mLoginEvent) {
        User user = new User("Chithlal");
        user.setPhone("8714236584");
        mMLoginPresenter.onLoginSuccess(user);
        return "Success";
    }
}
