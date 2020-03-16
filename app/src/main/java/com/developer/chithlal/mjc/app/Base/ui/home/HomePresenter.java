package com.developer.chithlal.mjc.app.Base.ui.home;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mMView;
    private HomeModel mHomeModel;

    public HomePresenter(HomeContract.View mView) {
        mMView = mView;
    }

    @Override
    public void setupMenu() {
        mHomeModel = new HomeModel(this);
        mMView.bindAdapter(mHomeModel.getMenuItems());


    }

    @Override
    public void onMenuItemsAcquired(List<BuildingType> mBuildingTypes) {

    }
}
