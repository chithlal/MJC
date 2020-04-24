package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.engineer.HireEngineer;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class,EngineerListModule.class})
@Singleton
public interface AppComponent {

    void inject(HireEngineer hireEngineerActivity);

}
