package com.lucasurbas.masterdetails.injection.main;

import android.content.Context;

import com.lucasurbas.masterdetails.injection.ActivityScope;
import com.lucasurbas.masterdetails.ui.activity.MainActivity;
import com.lucasurbas.masterdetails.ui.contract.MainContract;
import com.lucasurbas.masterdetails.ui.navigation.MainNavigation;
import com.lucasurbas.masterdetails.ui.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lucas on 12/06/16.
 */
@Module
public class MainModule {

    private final MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainContract.Navigation provideMainNavigation(MainNavigation navigation) {
        return navigation;
    }

    @Provides
    @ActivityScope
    MainContract.Presenter provideMainPresenter(MainPresenter presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    Context provideContext(){
        return mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity(){
        return mainActivity;
    }
}
