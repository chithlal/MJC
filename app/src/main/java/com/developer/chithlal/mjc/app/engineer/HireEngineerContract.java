package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;

import java.util.List;

public interface HireEngineerContract  {

    interface View{

        void updateList(List<Engineer> engineerList);
        void showMessage(String message);

    }
    interface Presenter{
        void setUpUi(Context context,HireEngineerContract.View view);


    }
    interface Model{
        void setContext(Context context);
        List<Engineer> getEngineersList();

    }
}
