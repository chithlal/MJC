package com.developer.chithlal.mjc.root;

import android.app.Application;

import com.developer.chithlal.mjc.root.di.AppComponent;
import com.developer.chithlal.mjc.root.di.AppModule;
import com.developer.chithlal.mjc.root.di.DaggerAppComponent;
import com.developer.chithlal.mjc.root.di.EngineerListModule;

public class App extends Application {
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .engineerListModule(new EngineerListModule())
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
}
