package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;

public class HireEngineerPresenter implements HireEngineerContract.Presenter {

    private final Context mContext;
    private final HireEngineerContract.View mView;
    private HireEngineerModel mHireEngineerModel;

    public HireEngineerPresenter(Context context,HireEngineerContract.View view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void setUpUi() {
        mHireEngineerModel = new HireEngineerModel(mContext);
        mView.updateList(mHireEngineerModel.getEngineersList());



    }
}
