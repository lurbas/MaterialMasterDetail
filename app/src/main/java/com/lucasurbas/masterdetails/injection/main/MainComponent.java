package com.lucasurbas.masterdetails.injection.main;

import com.lucasurbas.masterdetails.injection.ActivityScope;
import com.lucasurbas.masterdetails.injection.app.ApplicationComponent;
import com.lucasurbas.masterdetails.injection.people.PeopleComponent;
import com.lucasurbas.masterdetails.injection.people.PeopleModule;
import com.lucasurbas.masterdetails.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by Lucas on 12/06/16.
 */
@ActivityScope
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {MainModule.class}
)
public interface MainComponent {

    void inject(MainActivity activity);

    PeopleComponent plus(PeopleModule peopleModule);

}
