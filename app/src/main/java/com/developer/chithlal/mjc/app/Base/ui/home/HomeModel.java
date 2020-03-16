package com.developer.chithlal.mjc.app.Base.ui.home;

import com.developer.chithlal.mjc.R;

import java.util.ArrayList;
import java.util.List;

public class HomeModel implements HomeContract.Model {
    private HomeContract.Presenter mMPresenter;

    public HomeModel(HomeContract.Presenter mPresenter) {
        mMPresenter = mPresenter;
    }

    @Override
    public List<BuildingType> getMenuItems() {

        List<BuildingType> mListBuildingType = new ArrayList<>();
        mListBuildingType.add(new BuildingType("Home", R.drawable.home_image));
        mListBuildingType.add(new BuildingType("Auditorium", R.drawable.auditorium));
        mListBuildingType.add(new BuildingType("Bridge",R.drawable.bridge));
        mListBuildingType.add(new BuildingType("Shopping Mall", R.drawable.shopping_mall));
        mListBuildingType.add(new BuildingType("Tunnel",R.drawable.tunne));
        mListBuildingType.add(new BuildingType("Stadium",R.drawable.stadium));

        return mListBuildingType;
    }
}
