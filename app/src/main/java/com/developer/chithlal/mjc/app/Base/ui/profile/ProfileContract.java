package com.developer.chithlal.mjc.app.Base.ui.profile;

import android.content.Context;

import com.developer.chithlal.mjc.app.engineer.User;

public interface ProfileContract {

    interface View{
        void setupUserDetails(User user);
        void showMessages(String message);

    }

    interface Presenter{

        void setupUI(ProfileContract.View view);
        void setContext(Context context);

    }

    interface Model{
        User getUserDetails();
        void setContext(Context context);
        void logout();
    }
}
