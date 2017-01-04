package com.lucasurbas.masterdetails.injection.people;

import com.lucasurbas.masterdetails.injection.FragmentScope;
import com.lucasurbas.masterdetails.ui.people.PeopleFragment;

import dagger.Subcomponent;

/**
 * Created by Lucas on 04/01/2017.
 */
@FragmentScope
@Subcomponent(
        modules = {PeopleModule.class}
)
public interface PeopleComponent {

    void inject(PeopleFragment fragment);
}
