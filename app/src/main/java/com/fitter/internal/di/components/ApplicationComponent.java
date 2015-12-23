package com.fitter.internal.di.components;

import android.content.Context;

import com.fitter.internal.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by evgeniy.yanev on 11/30/15.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context context();
}
