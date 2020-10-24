package com.developer.chithlal.mjc.app.Base.ui.profile;

import android.content.Context;

import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;

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
        AccountManager accountManager = new AccountManager(mContext);
        return accountManager.getUser();
    }

    @Override
    public void logout() {

    }
}
