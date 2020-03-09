package com.developer.chithlal.mjc.app.Login;

public interface LoginContract {
    interface View{
        void getLoginData();
        void showValidationMessage(String message);
        void onLoginSuccess();
        void onLoginFailure(String mMessage);
    }

    interface Presenter{
        void tryLogin(LoginEvent mLoginEvent);
        void onLoginSuccess();
        void onLoginFailure(String message);
        void validateCred(LoginEvent mLoginEvent);
    }

    interface Model{
        String login(LoginEvent mLoginEvent);

    }

}
