package com.developer.chithlal.mjc.app.startup;

import android.content.Context;

import com.developer.chithlal.mjc.app.engineers_list.User;

public interface StartupContract {
    interface View{
        void navigateToLogin();
        void directLogin(User user);
        void showMessage(String message);
        Context getViewContext();
    }

    interface Presenter{
        void onAppStartup(StartupContract.View view);
        void loginWithLastUser(User user);
        void onPastUserNotAvailable();
        void passMessage(String message);
    }

    interface Model{
        void setupUserForLogin();
        void setPresenter(StartupContract.Presenter presenter);
        void setContext(Context context);

    }
}
