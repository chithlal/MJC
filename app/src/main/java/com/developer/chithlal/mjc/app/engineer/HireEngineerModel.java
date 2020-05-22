package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;

import com.developer.chithlal.mjc.app.firebase.DataRepository;
import com.developer.chithlal.mjc.app.work.Work;

import java.util.ArrayList;
import java.util.List;

public class HireEngineerModel implements HireEngineerContract.Model,
        DataRepository.EngineersListUpdateListener {
    private Context mContext;
    private HireEngineerContract.Presenter mPresenter;

    public HireEngineerModel(){
    }


    @Override
    public void setContext(Context context) {

            mContext = context;
    }

    @Override
    public void setPresenter(HireEngineerContract.Presenter presenter) {

        mPresenter = presenter;
    }

    /* Calling DataRepository to get the list of engineers , Light weight
      Filters and sorting can do here if needed*/
    @Override
    public void getEngineersList() {

        DataRepository dataRepository = new DataRepository(this);
            dataRepository.getAllEngineers();



    }

    @Override
    public void onEngineersListUpdated(List<User> engineersList) {

        mPresenter.onEngineersListArrived(engineersList);
    }

    @Override
    public void onEngineersListUpdateFailed(String message) {
        mPresenter.onEngineerListUpdateFailed(message);
    }
}
