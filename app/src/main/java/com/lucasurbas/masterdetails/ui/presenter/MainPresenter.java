package com.lucasurbas.masterdetails.ui.presenter;

import com.lucasurbas.masterdetails.ui.contract.MainContract;

import javax.inject.Inject;

/**
 * Created by Lucas on 02/01/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainContract.Navigation navigation;

    @Inject
    public MainPresenter(MainContract.Navigation navigation) {
        this.navigation = navigation;
    }

    @Override
    public void clickHomeFeed() {
        if (view != null) {
            view.highlightHomeFeed();
            view.closeDrawer();
        }
        navigation.goToHomeFeed();
    }

    @Override
    public void clickPeople() {
        if (view != null) {
            view.highlightPeople();
            view.closeDrawer();
        }
        navigation.goToPeople();
    }

    @Override
    public void clickFavorites() {
        if (view != null) {
            view.highlightFavorites();
            view.closeDrawer();
        }
        navigation.goToFavorites();
    }

    @Override
    public void clickMap() {
        if (view != null) {
            view.highlightMap();
            view.closeDrawer();
        }
        navigation.goToMap();
    }

    @Override
    public void clickSettings() {
        if (view != null) {
//            view.highlightSettings();
            view.closeDrawer();
        }
        navigation.goToSettings();
    }

    @Override
    public void clickFeedback() {
        if (view != null) {
//            view.highlightHomeFeed();
            view.closeDrawer();
        }
        navigation.goToFeedback();
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
