package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.UserProfile.UserProfileContract;
import com.developer.chithlal.mjc.app.UserProfile.UserProfileModel;
import com.developer.chithlal.mjc.app.UserProfile.UserProfilePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class UserProfileModule {

    @Provides
    UserProfileContract.Presenter providePresenter(UserProfileContract.Model userProfileModel){
        return new UserProfilePresenter(userProfileModel);
    }

    @Provides
    UserProfileContract.Model provideModel(){
        return new UserProfileModel();
    }
}
