package com.developer.chithlal.mjc.app.Login;

public class LoginModel implements LoginContract.Model {
    private LoginContract.Presenter mMLoginPresenter;

    public LoginModel(LoginContract.Presenter mLoginPresenter) {
        mMLoginPresenter = mLoginPresenter;
    }

    @Override
    public String login(LoginEvent mLoginEvent) {
        return null;
    }
}
