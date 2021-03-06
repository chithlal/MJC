package com.developer.chithlal.mjc.app.engineers_list;

import android.content.Context;

import com.developer.chithlal.mjc.app.firebase.DataRepository;

import java.util.List;

public class HireEngineerModel implements HireEngineerContract.Model,
        DataRepository.EngineersListUpdateListener {
    private Context mContext;
    private HireEngineerContract.Presenter mPresenter;
    private DataRepository dataRepository;
    private String mBuildingType;

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

    @Override
    public void setBuildingType(String buildingType) {

        mBuildingType = buildingType;
    }

    /* Calling DataRepository to get the list of engineers , Light weight
      Filters and sorting can do here if needed*/
    @Override
    public void getEngineersList(int pageNumber) {



            dataRepository.getAllEngineers(pageNumber,mBuildingType);



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
