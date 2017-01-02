package com.lucasurbas.masterdetails.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.injection.app.ApplicationComponent;
import com.lucasurbas.masterdetails.injection.main.DaggerMainComponent;
import com.lucasurbas.masterdetails.injection.main.MainModule;
import com.lucasurbas.masterdetails.ui.contract.MainContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @Inject MainContract.Presenter presenter;

    @BindView(R.id.activity_main__nav_view) NavigationView navigationView;
    @BindView(R.id.activity_main__drawer) DrawerLayout drawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setNavigationItemSelectedListener(this);

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

    }

    @Override
    public void highlightPeople() {

    }

    @Override
    public void highlightFavorites() {

    }

    @Override
    public void highlightMap() {

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
}
