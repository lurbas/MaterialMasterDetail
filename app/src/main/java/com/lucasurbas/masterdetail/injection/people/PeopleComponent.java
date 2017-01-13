package com.lucasurbas.masterdetail.injection.people;

import com.lucasurbas.masterdetail.injection.FragmentScope;
import com.lucasurbas.masterdetail.ui.people.PeopleFragment;

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
