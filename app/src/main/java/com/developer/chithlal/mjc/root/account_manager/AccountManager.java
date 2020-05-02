package com.developer.chithlal.mjc.root.account_manager;

import com.developer.chithlal.mjc.app.engineer.User;

public class AccountManager implements AccountManagerInterface {
    User mUser;
    public AccountManager() {
    }

    @Override
    public void loginUser(User user) {
        mUser = user;

    }

    @Override
    public User getUser() {
        return mUser;
    }

    @Override
    public void logoutUser() {
        mUser = null;

    }

    @Override
    public void saveUser(User user) {


    }

    @Override
    public void validateUser() {

    }

    @Override
    public boolean isLoggedIn() {
        return mUser != null;
    }
}
