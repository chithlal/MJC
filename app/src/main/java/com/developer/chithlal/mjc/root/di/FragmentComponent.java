package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.UserProfile.AddWorkDetailsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AddWorkModule.class})
@Singleton
public interface FragmentComponent {

    void inject(AddWorkDetailsFragment addWorkDetailsFragment);

}
