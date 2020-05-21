package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.UserProfile.UserProfileActivity;
import com.developer.chithlal.mjc.app.engineer.HireEngineer;
import com.developer.chithlal.mjc.app.user_details.MoreDetailsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class,EngineerListModule.class,UserProfileModule.class,AddWorkModule.class,MoreDetailPresenterModule.class})
@Singleton
public interface AppComponent {

    void inject(HireEngineer hireEngineerActivity);
    void injectUserProfile(UserProfileActivity userProfileActivity);
    void injectMoreDetails(MoreDetailsActivity moreDetailsActivity);

}
