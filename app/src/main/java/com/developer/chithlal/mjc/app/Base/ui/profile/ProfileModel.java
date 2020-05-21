package com.developer.chithlal.mjc.app.Base.ui.profile;

import android.app.Activity;
import android.content.Context;

import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.root.App;

public class ProfileModel implements ProfileContract.Model {
    private Context mContext;

    public ProfileModel() {
    }
    @Override
    public void setContext(Context context){

        mContext = context;
    }

    @Override
    public User getUserDetails() {
        User user = ((App)((Activity)mContext).getApplication()).getUser();
        return user;
    }

    @Override
    public void logout() {

    }
}
