package com.developer.chithlal.mjc.app.Base.ui.home;

import java.util.List;

public interface HomeContract {

    interface View{
        void bindAdapter(List<BuildingType> mBuildingType);
        void showError(String message);

    }

    interface Presenter{
        void setupMenu();
        void onMenuItemsAcquired(List<BuildingType> mBuildingTypes);

    }

    interface Model{
            List<BuildingType> getMenuItems();
    }

}
