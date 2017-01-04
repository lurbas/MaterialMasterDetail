package com.lucasurbas.masterdetails.ui.contract;

import com.lucasurbas.masterdetails.data.Person;
import com.lucasurbas.masterdetails.ui.util.BaseNavigator;
import com.lucasurbas.masterdetails.ui.util.BasePresenter;
import com.lucasurbas.masterdetails.ui.util.BaseView;

import java.util.List;

/**
 * Created by Lucas on 04/01/2017.
 */

public interface PeopleContract {

    interface Navigator extends BaseNavigator {

        void goToPersonDetails();
    }

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showPeopleList(List<Person> peopleList);

    }

    interface Presenter extends BasePresenter<PeopleContract.View> {

        void getPeople();

        void clickPerson();

        void clickPersonAction();

        void loadMorePeople();
    }
}
