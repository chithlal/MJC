package com.developer.chithlal.mjc.app.work;

import android.app.Activity;
import android.content.Context;

import com.developer.chithlal.mjc.root.App;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;

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
        mAddworkModel.setPresenter(this);
        mView.setWorkTypeOptions(mAddworkModel.getWorkTypeOptions());


    }

    @Override
    public void onSaveClicked(Work work) {
        AccountManager accountManager = new AccountManager(mContext);
        String uid = accountManager.getUserId();
        work.setEngineerId(uid);
        mAddworkModel.updateWork(work);

    }

    @Override
    public void onWorkDataUploadSuccess(Work work) {
        mView.setMessage("Work added successfully!");
        mView.setWork(work);
    }

    @Override
    public void onWorkDataUploadFailed(String message) {
        mView.setMessage("Unable to add work..Please try again!");
    }
}
