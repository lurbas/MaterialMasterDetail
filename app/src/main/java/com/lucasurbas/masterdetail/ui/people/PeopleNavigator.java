package com.lucasurbas.masterdetail.ui.people;

import com.lucasurbas.masterdetail.data.Person;
import com.lucasurbas.masterdetail.ui.main.MainContract;

import javax.inject.Inject;

/**
 * Created by Lucas on 17/01/2017.
 */

public class PeopleNavigator implements PeopleContract.Navigator {

    private MainContract.Navigator mainNavigator;

    @Inject
    public PeopleNavigator(MainContract.Navigator mainNavigator) {
        this.mainNavigator = mainNavigator;
    }

    @Override
    public void goToPersonDetails(Person person) {
        mainNavigator.goToPersonDetails(person);
    }
}
