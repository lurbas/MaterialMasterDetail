package com.lucasurbas.masterdetail.app;

import android.app.Application;
import android.content.Context;

import com.lucasurbas.masterdetail.injection.app.ApplicationComponent;
import com.lucasurbas.masterdetail.injection.app.ApplicationModule;
import com.lucasurbas.masterdetail.injection.app.DaggerApplicationComponent;

/**
 * Created by Lucas on 02/01/2017.
 */

public class MasterDetailApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getAppComponent(Context context) {
        return ((MasterDetailApplication) context.getApplicationContext()).applicationComponent;
    }
}
