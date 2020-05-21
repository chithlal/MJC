package com.developer.chithlal.mjc.app.work;

import android.app.Activity;
import android.content.Context;

import com.developer.chithlal.mjc.root.App;

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
        String uid = ((App)((Activity)(mContext)).getApplication()).getUser().getUserId();
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
