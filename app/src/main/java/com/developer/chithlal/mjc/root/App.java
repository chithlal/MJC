package com.developer.chithlal.mjc.root;

import android.app.Application;

import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.root.account_manager.AccountManagerInterface;
import com.developer.chithlal.mjc.root.di.AddWorkModule;
import com.developer.chithlal.mjc.root.di.AppComponent;
import com.developer.chithlal.mjc.root.di.AppModule;
import com.developer.chithlal.mjc.root.di.DaggerAppComponent;
import com.developer.chithlal.mjc.root.di.EngineerListModule;
import com.developer.chithlal.mjc.root.di.UserProfileModule;

public class App extends Application {
    private User mUser;
    AccountManagerInterface mAccountManager;
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .engineerListModule(new EngineerListModule())
                .userProfileModule(new UserProfileModule())
                .build();
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

    //setup after login and logout
    public void setAccountManager(AccountManagerInterface accountManager){
        this.mAccountManager = accountManager;
    }

    public void setUser(User user){
        mUser = user;

    }
    public User getUser(){
        //nullable
        return mUser;
    }

}
