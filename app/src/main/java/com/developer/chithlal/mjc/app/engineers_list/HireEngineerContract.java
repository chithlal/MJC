package com.developer.chithlal.mjc.app.engineers_list;

import android.content.Context;

import java.util.List;

public interface HireEngineerContract  {

    interface View{

        void updateList(List<User> engineerList,int pageNumber);
        void showMessage(String message);
        String getBuildingType();

    }
    interface Presenter{
        void setUpUi(Context context,HireEngineerContract.View view);
        void onEngineersListArrived(List<User> engineersList,int pageNumber);
        void getNextPageOfList(int pageNumber);
        void onEngineerListUpdateFailed(String message);



    }
    interface Model  {
        void setContext(Context context);
        void setPresenter(HireEngineerContract.Presenter presenter);
        void setBuildingType(String buildingType);
        void getEngineersList(int pageNumber);

    }
}
