package com.lucasurbas.masterdetail.injection.people;

import com.lucasurbas.masterdetail.injection.FragmentScope;
import com.lucasurbas.masterdetail.ui.people.PeopleContract;
import com.lucasurbas.masterdetail.ui.people.PeopleNavigator;
import com.lucasurbas.masterdetail.ui.people.PeoplePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lucas on 04/01/2017.
 */
@Module
public class PeopleModule {

    @Provides
    PeopleContract.Navigator providePeopleNavigator(PeopleNavigator navigator) {
        return navigator;
    }

    @Provides
    @FragmentScope
    PeopleContract.Presenter providePeoplePresenter(PeoplePresenter presenter) {
        return presenter;
    }
}
