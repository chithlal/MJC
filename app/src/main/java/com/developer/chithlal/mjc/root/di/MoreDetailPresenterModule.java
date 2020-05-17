package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.user_details.MoreDetailContract;
import com.developer.chithlal.mjc.app.user_details.MoreDetailsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MoreDetailPresenterModule {
    @Provides
    MoreDetailContract.Presenter providePresenter(){
        return new MoreDetailsPresenter();
    }
}
