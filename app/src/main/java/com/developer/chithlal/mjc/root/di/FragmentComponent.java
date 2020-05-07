package com.developer.chithlal.mjc.root.di;

import com.developer.chithlal.mjc.app.Base.ui.profile.ProfileFragment;
import com.developer.chithlal.mjc.app.UserProfile.AddWorkDetailsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AddWorkModule.class,ProfileSettingsModule.class})
@Singleton
public interface FragmentComponent {

    void inject(AddWorkDetailsFragment addWorkDetailsFragment);
    void inject(ProfileFragment profileFragment);

}
