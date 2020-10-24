package com.developer.chithlal.mjc.app.work;

import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.firebase.UpdateDataUtil;
import com.developer.chithlal.mjc.app.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class AddworkModel implements AddworkContract.Model,UpdateDataUtil.firebaseDataUpdateListener {
    private AddworkContract.Presenter mPresenter;

    @Override
    public List<String> getWorkTypeOptions() {
        List<String> workType = new ArrayList<>();
        workType.add(Constants.CONST_STRING_WRK_TYPE_MAIN_CONC);
        workType.add(Constants.CONST_STRING_WRK_TYPE_FINSHING);
        workType.add(Constants.CONST_STRING_WRK_TYPE_FULLCONSTR);

        return workType;
    }

    @Override
    public void setPresenter(AddworkContract.Presenter presenter) {

        mPresenter = presenter;
    }

    @Override
    public void updateWork(Work work) {
        UpdateDataUtil updateDataUtil = new UpdateDataUtil(this);
        updateDataUtil.uploadWorkWithPhotos(work);
    }

    @Override
    public void onUserUpdateCompleted(User user) {

    }

    @Override
    public void onWorkUpdateCompleted(Work work) {
        mPresenter.onWorkDataUploadSuccess(work);

    }

    @Override
    public void onUpdateFailed(String message) {
        mPresenter.onWorkDataUploadFailed(message);

    }

    @Override
    public void onDeleteSuccess() {

    }

    @Override
    public void onDeleteFailed(String message) {

    }
}
