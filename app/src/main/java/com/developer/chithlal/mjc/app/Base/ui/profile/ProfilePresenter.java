package com.developer.chithlal.mjc.app.Base.ui.profile;

import android.content.Context;

public class ProfilePresenter implements ProfileContract.Presenter {
    ProfileContract.Model mModel;
    ProfileContract.View mView;
    Context mContext;
    public ProfilePresenter(ProfileContract.Model model) {

        mModel = model;
    }

    @Override
    public void setupUI(ProfileContract.View view) {
        mView = view;
        mModel.setContext(mContext);
        mView.setupUserDetails(mModel.getUserDetails());

    }

    @Override
    public void setContext(Context context) {
        mContext = context;

    }
}
