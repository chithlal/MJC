package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

public class HireEngineerPresenter implements HireEngineerContract.Presenter {

    private  Context mContext;
    private  HireEngineerContract.View mView;
    private HireEngineerContract.Model mHireEngineerModel;

    @Inject
    public HireEngineerPresenter(HireEngineerContract.Model model) {
        mHireEngineerModel = model;

    }

    @Override
    public void setUpUi(Context context,HireEngineerContract.View view) {
        mView = view;
        mContext = context;
        mHireEngineerModel.setContext(context);
        mHireEngineerModel.setPresenter(this);
        mHireEngineerModel.getEngineersList();



    }

    @Override
    public void onEngineersListArrived(List<User> engineersList) {
        mView.updateList(engineersList);

    }

    @Override
    public void onEngineerListUpdateFailed(String message) {
        mView.showMessage(message);

    }
}
