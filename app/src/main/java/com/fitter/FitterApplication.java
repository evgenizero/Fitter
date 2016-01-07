package com.fitter;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.fitter.internal.di.components.ApplicationComponent;
import com.fitter.internal.di.components.DaggerApplicationComponent;
import com.fitter.internal.di.modules.ApplicationModule;

import entities.User;

/**
 * Created by evgeniy.yanev on 11/21/15.
 */
public class FitterApplication extends Application {

    private ApplicationComponent applicationComponent;
    private User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        Stetho.initializeWithDefaults(this);
        ActiveAndroid.initialize(this);
        initInjector();
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
