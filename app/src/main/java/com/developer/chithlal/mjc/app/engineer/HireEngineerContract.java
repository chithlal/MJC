package com.developer.chithlal.mjc.app.engineer;

import java.util.List;

public interface HireEngineerContract  {

    interface View{

        void updateList(List<Engineer> engineerList);
        void showMessage(String message);

    }
    interface Presenter{
        void setUpUi();


    }
    interface Model{

        List<Engineer> getEngineersList();

    }
}
