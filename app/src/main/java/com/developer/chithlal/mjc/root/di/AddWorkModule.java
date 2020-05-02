package com.developer.chithlal.mjc.root.di;


import com.developer.chithlal.mjc.app.UserProfile.AddWorkPresenter;
import com.developer.chithlal.mjc.app.UserProfile.AddworkContract;
import com.developer.chithlal.mjc.app.UserProfile.AddworkModel;

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
