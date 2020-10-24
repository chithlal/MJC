package com.developer.chithlal.mjc.app.engineers_list;

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
        mHireEngineerModel.setBuildingType(mView.getBuildingType());
        mHireEngineerModel.getEngineersList(1);



    }

    @Override
    public void onEngineersListArrived(List<User> engineersList, int pageNumber) {

        mView.updateList(engineersList,pageNumber);

    }

    @Override
    public void getNextPageOfList(int pageNumber) {
            mHireEngineerModel.getEngineersList(pageNumber);
    }


    @Override
    public void onEngineerListUpdateFailed(String message) {
        mView.showMessage(message);

    }
}
