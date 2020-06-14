package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.UserProfile.UserProfileActivity;
import com.developer.chithlal.mjc.app.engineers_list.EngineersListActivity;
import com.developer.chithlal.mjc.app.startup.StartupActivity;
import com.developer.chithlal.mjc.app.user_details.MoreDetailsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class,EngineerListModule.class,UserProfileModule.class,AddWorkModule.class,
        MoreDetailPresenterModule.class,StartupActivityModule.class})
@Singleton
public interface AppComponent {

    void inject(EngineersListActivity engineersListActivityActivity);
    void injectUserProfile(UserProfileActivity userProfileActivity);
    void injectMoreDetails(MoreDetailsActivity moreDetailsActivity);
    void injectStartupActivity(StartupActivity startupActivity);

}
