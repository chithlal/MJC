package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.startup.StartupContract;
import com.developer.chithlal.mjc.app.startup.StartupModel;
import com.developer.chithlal.mjc.app.startup.StartupPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class StartupActivityModule {

    @Provides
    StartupContract.Presenter providePresenter(StartupContract.Model model)
    {
        return new StartupPresenter(new StartupModel());
    }

    @Provides
    StartupContract.Model provideStartupModel(){
        return new StartupModel();
    }
}
