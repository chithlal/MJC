package com.developer.chithlal.mjc.root;

import android.app.Application;


import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.firebase.DataRepository;
import com.developer.chithlal.mjc.app.util.TypefaceUtil;
import com.developer.chithlal.mjc.root.account_manager.AccountManagerInterface;
import com.developer.chithlal.mjc.root.di.AppComponent;
import com.developer.chithlal.mjc.root.di.AppModule;
import com.developer.chithlal.mjc.root.di.DaggerAppComponent;
import com.developer.chithlal.mjc.root.di.EngineerListModule;
import com.developer.chithlal.mjc.root.di.MoreDetailPresenterModule;
import com.developer.chithlal.mjc.root.di.StartupActivityModule;
import com.developer.chithlal.mjc.root.di.UserProfileModule;
import com.google.firebase.FirebaseApp;


import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {



    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
        TypefaceUtil.overrideFont(getApplicationContext(), "SANS", "font/open_sans_regular.ttf");
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .engineerListModule(new EngineerListModule())
                .userProfileModule(new UserProfileModule())
                .moreDetailPresenterModule(new MoreDetailPresenterModule())
                .startupActivityModule(new StartupActivityModule())
                .build();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("mjc.realm.db").build();
        Realm.setDefaultConfiguration(config);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }





}
