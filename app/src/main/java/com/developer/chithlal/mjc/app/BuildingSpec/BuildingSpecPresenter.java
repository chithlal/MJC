package com.developer.chithlal.mjc.app.BuildingSpec;

import android.content.Context;

public class BuildingSpecPresenter implements BuildingSpecContract.Presenter {

    private final Context mContext;
    private final BuildingSpecContract.View mView;

    BuildingSpecModel mBuildingSpecModel;

    public BuildingSpecPresenter(Context context,BuildingSpecContract.View view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void setupViews() {
        mBuildingSpecModel = new BuildingSpecModel(mContext);
        getMaterials();

    }

    @Override
    public void onContinuePress() {

    }

    @Override
    public void getMaterials() {
        mView.setupMaterial(mBuildingSpecModel.getMaterials());
    }

    @Override
    public void onPlanUpload() {


    }
}
