package com.developer.chithlal.mjc.app.UserProfile;

import android.content.Context;

public class AddWorkPresenter implements AddworkContract.Presenter {
    AddworkContract.Model mAddworkModel;
    Context mContext;
    AddworkContract.View mView;

    public AddWorkPresenter(AddworkContract.Model addworkModel) {
        mAddworkModel = addworkModel;
    }

    @Override
    public void setUi(AddworkContract.View view, Context context) {
        mView= view;
        mContext = context;
        mView.setWorkTypeOptions(mAddworkModel.getWorkTypeOptions());


    }

    @Override
    public void onSaveClicked(Work work) {

    }
}
