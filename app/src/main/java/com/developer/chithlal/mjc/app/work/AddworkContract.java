package com.developer.chithlal.mjc.app.work;

import android.content.Context;

import java.util.List;

public interface AddworkContract {
    interface View{

        void setWorkTypeOptions(List<String> workTypeOptions);
        void setMessage(String message);
        void setWork(Work work);

    }

    interface Presenter{
        void setUi(AddworkContract.View view, Context context);
        void onSaveClicked(Work work);
        void onWorkDataUploadSuccess(Work work);
        void onWorkDataUploadFailed(String message);
    }

    interface Model {
        List<String> getWorkTypeOptions();
        void setPresenter(AddworkContract.Presenter presenter);
        void updateWork(Work work);
    }
}
