package com.developer.chithlal.mjc.root.di;


import com.developer.chithlal.mjc.app.work.AddWorkPresenter;
import com.developer.chithlal.mjc.app.work.AddworkContract;
import com.developer.chithlal.mjc.app.work.AddworkModel;

import dagger.Module;
import dagger.Provides;

@Module
public class AddWorkModule {

    @Provides
    public AddworkContract.Presenter providePresenter(AddworkContract.Model model){
        return new AddWorkPresenter(model);
    }

    @Provides
    public AddworkContract.Model provideModel(){
        return new AddworkModel();
    }


}
