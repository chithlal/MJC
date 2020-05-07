package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.Base.ui.profile.ProfileContract;
import com.developer.chithlal.mjc.app.Base.ui.profile.ProfileModel;
import com.developer.chithlal.mjc.app.Base.ui.profile.ProfilePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileSettingsModule {

    @Provides
    ProfileContract.Presenter providePresenter(ProfileContract.Model model){
        return new ProfilePresenter(model);
    }

    @Provides
    ProfileContract.Model provideModel(){
        return new ProfileModel();
    }
}

