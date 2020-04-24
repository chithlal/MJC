package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.engineer.HireEngineer;
import com.developer.chithlal.mjc.app.engineer.HireEngineerContract;
import com.developer.chithlal.mjc.app.engineer.HireEngineerModel;
import com.developer.chithlal.mjc.app.engineer.HireEngineerPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EngineerListModule {

    @Provides
    HireEngineerContract.Presenter providePresenter(HireEngineerContract.Model model){
        return new HireEngineerPresenter(model);
    }

    @Provides
    HireEngineerContract.Model provideModel(){
        return new HireEngineerModel();
    }
}
