package com.lucasurbas.masterdetail.ui.main;

import javax.inject.Inject;

/**
 * Created by Lucas on 02/01/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainContract.Navigator navigator;

    @Inject
    public MainPresenter(MainContract.Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void clickHomeFeed() {
        if (view != null) {
            view.highlightHomeFeed();
            view.closeDrawer();
        }
        navigator.goToHomeFeed();
    }

    @Override
    public void clickPeople() {
        if (view != null) {
            view.highlightPeople();
            view.closeDrawer();
        }
        navigator.goToPeople();
    }

    @Override
    public void clickFavorites() {
        if (view != null) {
            view.highlightFavorites();
            view.closeDrawer();
        }
        navigator.goToFavorites();
    }

    @Override
    public void clickMap() {
        if (view != null) {
            view.highlightMap();
            view.closeDrawer();
        }
        navigator.goToMap();
    }

    @Override
    public void clickSettings() {
        if (view != null) {
//            view.highlightSettings();
            view.closeDrawer();
        }
        navigator.goToSettings();
    }

    @Override
    public void clickFeedback() {
        if (view != null) {
//            view.highlightHomeFeed();
            view.closeDrawer();
        }
        navigator.goToFeedback();
    }
}
