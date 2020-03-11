package com.developer.chithlal.mjc.app.Login;

public interface LoginContract {
    interface View{
        LoginEvent getLoginData();
        void showValidationMessage(String userNameMessage,String passwordMessage);
        void onLoginSuccess();
        void onLoginFailure(String mMessage);
    }

    interface Presenter{
        void tryLogin();
        void onLoginSuccess();
        void onLoginFailure(String message);
        int validateCred(LoginEvent mLoginEvent);
    }

    interface Model{
        String login(LoginEvent mLoginEvent);

    }

}
