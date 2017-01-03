package com.lucasurbas.masterdetails.ui.contract;


import com.lucasurbas.masterdetails.ui.navigation.MainNavigation;
import com.lucasurbas.masterdetails.ui.util.BaseNavigation;
import com.lucasurbas.masterdetails.ui.util.BasePresenter;
import com.lucasurbas.masterdetails.ui.util.BaseView;

/**
 * Created by Lucas on 12/06/16.
 */
public interface MainContract {

    interface Navigation extends BaseNavigation {

        void goToHomeFeed();

        void goToPeople();

        void goToFavorites();

        void goToMap();

        void goToSettings();

        void goToFeedback();

        void goToDetails();

        void restoreState(MainNavigation.State state);
    }

    interface View extends BaseView {

        void closeDrawer();

        void openDrawer();

        void highlightHomeFeed();

        void highlightPeople();

        void highlightFavorites();

        void highlightMap();

        void highlightSettings();

        void highlightFeedback();

    }

    interface Presenter extends BasePresenter<View> {

        void clickHomeFeed();

        void clickPeople();

        void clickFavorites();

        void clickMap();

        void clickSettings();

        void clickFeedback();
    }
}
