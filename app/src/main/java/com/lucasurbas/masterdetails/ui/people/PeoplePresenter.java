package com.lucasurbas.masterdetails.ui.people;

import android.os.Handler;

import com.lucasurbas.masterdetails.data.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by Lucas on 04/01/2017.
 */

public class PeoplePresenter implements PeopleContract.Presenter {

    private PeopleContract.View view;
    private PeopleContract.Navigator navigator;

    private List<Person> peopleList;

    @Inject
    public PeoplePresenter(PeopleContract.Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void attachView(PeopleContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getPeople() {
        peopleList = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            Person person = new Person(UUID.randomUUID().toString());
            person.setName("Name " + (i + 1));
            person.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Eadem nunc mea adversum te oratio est.");
            peopleList.add(person);
        }
        if (view != null) {
            view.showPeopleList(peopleList);
        }
    }

    @Override
    public void clickPerson() {
        navigator.goToPersonDetails();
    }

    @Override
    public void clickPersonAction() {
        view.showToast("Action clicked");
    }

    @Override
    public void loadMorePeople() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view != null) {
                    view.hideLoading();
                    Person person = new Person(UUID.randomUUID().toString());
                    person.setName("Name " + (peopleList.size() + 1));
                    person.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Eadem nunc mea adversum te oratio est.");
                    peopleList.add(0, person);
                    view.showPeopleList(peopleList);
                }
            }
        }, 2000);
    }
}
