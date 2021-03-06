package com.developer.chithlal.mjc.app.Login;

import android.content.Context;

import com.developer.chithlal.mjc.app.engineers_list.User;

public interface LoginContract {
    interface View{
        LoginEvent getLoginData();
        void showValidationMessage(String userNameMessage,String passwordMessage);
        void onLoginSuccess(User user);
        void onLoginFailure(String mMessage);
        Context getCurrentContext();
        void showMessage(String message);
    }

    interface Presenter{
        void tryLogin();
        void onLoginSuccess(User user);
        void onLoginFailure(String message);
        int validateCred(LoginEvent mLoginEvent);

    }

    interface Model {
        String login(LoginEvent mLoginEvent);
        void onLoginSuccess(User user);
        void onLoginFailure(String message);

    }

}
