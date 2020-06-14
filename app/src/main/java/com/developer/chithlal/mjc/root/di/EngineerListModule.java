package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.engineers_list.HireEngineerContract;
import com.developer.chithlal.mjc.app.engineers_list.HireEngineerModel;
import com.developer.chithlal.mjc.app.engineers_list.HireEngineerPresenter;

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
