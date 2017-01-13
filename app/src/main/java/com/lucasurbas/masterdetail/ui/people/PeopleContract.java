package com.lucasurbas.masterdetail.ui.people;

import com.lucasurbas.masterdetail.data.Person;
import com.lucasurbas.masterdetail.ui.util.BaseNavigator;
import com.lucasurbas.masterdetail.ui.util.BasePresenter;
import com.lucasurbas.masterdetail.ui.util.BaseView;

import java.util.List;

/**
 * Created by Lucas on 04/01/2017.
 */

public interface PeopleContract {

    interface Navigator extends BaseNavigator {

        void goToPersonDetails(Person person);
    }

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showPeopleList(List<Person> peopleList);

        void showToast(String message);
    }

    interface Presenter extends BasePresenter<PeopleContract.View> {

        void getPeople();

        void clickPerson(Person person);

        void clickPersonAction(Person person);

        void loadMorePeople();
    }
}
