package com.lucasurbas.masterdetails.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.injection.app.ApplicationComponent;
import com.lucasurbas.masterdetails.injection.main.DaggerMainComponent;
import com.lucasurbas.masterdetails.injection.main.MainModule;
import com.lucasurbas.masterdetails.ui.contract.MainContract;
import com.lucasurbas.masterdetails.ui.navigator.MainNavigator;
import com.lucasurbas.masterdetails.ui.widget.ContainersLayout;
import com.lucasurbas.masterdetails.ui.widget.CustomAppBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @Inject MainContract.Presenter presenter;
    @Inject MainContract.Navigator navigator;

    @BindView(R.id.activity_main__nav_view) NavigationView navigationView;
    @BindView(R.id.activity_main__drawer) DrawerLayout drawer;
    @BindView(R.id.activity_main__custom_appbar) CustomAppBar customAppBar;
    @BindView(R.id.activity_main__containers_layout) ContainersLayout containersLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setNavigationItemSelectedListener(this);
        customAppBar.setOnNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else if (drawer != null && !drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        presenter.attachView(this);
        if (savedInstanceState == null) {
            presenter.clickPeople();
        }
    }

    @Override
    protected void setupActivityComponent(ApplicationComponent applicationComponent) {
        DaggerMainComponent.builder()
                .applicationComponent(applicationComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void closeDrawer() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void openDrawer() {
        if (drawer != null && !drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void highlightHomeFeed() {
        navigationView.setCheckedItem(R.id.menu_main_nav__home_feed);
    }

    @Override
    public void highlightPeople() {
        navigationView.setCheckedItem(R.id.menu_main_nav__people);
    }

    @Override
    public void highlightFavorites() {
        navigationView.setCheckedItem(R.id.menu_main_nav__favorites);
    }

    @Override
    public void highlightMap() {
        navigationView.setCheckedItem(R.id.menu_main_nav__map);
    }

    @Override
    public void highlightSettings() {

    }

    @Override
    public void highlightFeedback() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_nav__home_feed:
                presenter.clickHomeFeed();
                break;

            case R.id.menu_main_nav__people:
                presenter.clickPeople();
                break;

            case R.id.menu_main_nav__favorites:
                presenter.clickFavorites();
                break;

            case R.id.menu_main_nav__map:
                presenter.clickMap();
                break;

            case R.id.menu_main_nav__settings:
                presenter.clickSettings();
                break;

            case R.id.menu_main_nav__feedback:
                presenter.clickFeedback();
                break;

            default:
                return false;
        }
        return true;
    }

    public CustomAppBar getCustomAppBar() {
        return customAppBar;
    }

    public ContainersLayout getContainersLayout(){
        return containersLayout;
    }

    public MainContract.Navigator getNavigator() {
        return navigator;
    }

}
