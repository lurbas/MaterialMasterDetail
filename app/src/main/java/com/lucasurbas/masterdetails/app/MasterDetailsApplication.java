package com.lucasurbas.masterdetails.app;

import android.app.Application;
import android.content.Context;

import com.lucasurbas.masterdetails.injection.app.ApplicationComponent;
import com.lucasurbas.masterdetails.injection.app.ApplicationModule;
import com.lucasurbas.masterdetails.injection.app.DaggerApplicationComponent;

/**
 * Created by Lucas on 02/01/2017.
 */

public class MasterDetailsApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getAppComponent(Context context) {
        return ((MasterDetailsApplication) context.getApplicationContext()).applicationComponent;
    }
}
