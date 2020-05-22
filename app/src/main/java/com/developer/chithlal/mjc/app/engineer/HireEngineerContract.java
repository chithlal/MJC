package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;

import java.util.List;

public interface HireEngineerContract  {

    interface View{

        void updateList(List<User> engineerList);
        void showMessage(String message);

    }
    interface Presenter{
        void setUpUi(Context context,HireEngineerContract.View view);
        void onEngineersListArrived(List<User> engineersList);
        void onEngineerListUpdateFailed(String message);


    }
    interface Model  {
        void setContext(Context context);
        void setPresenter(HireEngineerContract.Presenter presenter);
        void getEngineersList();

    }
}
