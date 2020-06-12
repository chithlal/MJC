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
    private DataRepository dataRepository;

    public HireEngineerModel(){
         dataRepository = new DataRepository(this);
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
    public void getEngineersList(int pageNumber) {


            dataRepository.getAllEngineers(pageNumber);



    }

    @Override
    public void onEngineersListUpdated(List<User> engineersList,int pageNumber) {

        mPresenter.onEngineersListArrived(engineersList,pageNumber);
    }

    @Override
    public void onEngineersListUpdateFailed(String message) {
        mPresenter.onEngineerListUpdateFailed(message);
    }
}
