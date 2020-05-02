package com.developer.chithlal.mjc.app.UserProfile;

import android.content.Context;

import java.util.List;

public interface AddworkContract {
    interface View{

        void setWorkTypeOptions(List<String> workTypeOptions);
        void setMessage(String message);

    }

    interface Presenter{
        void setUi(AddworkContract.View view, Context context);
        void onSaveClicked(Work work);
    }

    interface Model{
        List<String> getWorkTypeOptions();
        void updateWork();
    }
}
