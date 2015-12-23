package com.fitter.internal.di.modules;

import android.content.Context;

import com.fitter.FitterApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by evgeniy.yanev on 11/30/15.
 */
@Module
public class ApplicationModule {
    private FitterApplication application;

    public ApplicationModule(FitterApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

}
