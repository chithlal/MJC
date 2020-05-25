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
import com.developer.chithlal.mjc.root.di.UserProfileModule;
import com.google.firebase.FirebaseApp;

public class App extends Application {
    private User mUser;

    AccountManagerInterface mAccountManager;
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "font/app_font_nu.ttf");
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .engineerListModule(new EngineerListModule())
                .userProfileModule(new UserProfileModule())
                .moreDetailPresenterModule(new MoreDetailPresenterModule())
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
        setUser(mAccountManager.getUser());
    }

    private void setUser(User user){
        mUser = user;


    }
    public User getUser(){
        //nullable

        if (mAccountManager!=null)
        return mAccountManager.getUser();
        else return null;
    }
    //method to update the user details for the app. Can be accessed till the application is running
   public void updateUser(){
        if (mAccountManager!=null)
            mAccountManager.updateUser();
   }

}
