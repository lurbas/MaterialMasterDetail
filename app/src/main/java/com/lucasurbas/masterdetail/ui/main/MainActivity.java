package com.lucasurbas.masterdetail.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lucasurbas.masterdetail.R;
import com.lucasurbas.masterdetail.injection.app.ApplicationComponent;
import com.lucasurbas.masterdetail.injection.main.DaggerMainComponent;
import com.lucasurbas.masterdetail.injection.main.MainComponent;
import com.lucasurbas.masterdetail.injection.main.MainModule;
import com.lucasurbas.masterdetail.ui.util.BaseActivity;
import com.lucasurbas.masterdetail.ui.widget.ContainersLayout;
import com.lucasurbas.masterdetail.ui.widget.CustomAppBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @Inject MainContract.Presenter presenter;
    @Inject MainContract.Navigator navigator;

    @BindView(R.id.activity_main__nav) NavigationView navigationView;
    @Nullable
    @BindView(R.id.activity_main__nav_side)
    NavigationView navigationSideView;
    @Nullable
    @BindView(R.id.activity_main__insets)
    ViewGroup insetsView;
    @BindView(R.id.activity_main__drawer) DrawerLayout drawer;
    @BindView(R.id.activity_main__custom_appbar) CustomAppBar customAppBar;
    @BindView(R.id.activity_main__containers_layout) ContainersLayout containersLayout;

    private MainComponent mainComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (insetsView != null && navigationSideView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(insetsView, new OnApplyWindowInsetsListener() {
                @Override
                public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                    ((ViewGroup.MarginLayoutParams) insetsView.getLayoutParams()).topMargin = insets.getSystemWindowInsetTop();
                    ((ViewGroup.MarginLayoutParams) insetsView.getLayoutParams()).bottomMargin = insets.getSystemWindowInsetBottom();
                    insetsView.requestLayout();
                    ((ViewGroup.MarginLayoutParams) navigationSideView.getLayoutParams()).topMargin = (-insets.getSystemWindowInsetTop());
                    navigationSideView.requestLayout();
                    return insets.consumeSystemWindowInsets();
                }
            });
            navigationSideView.setNavigationItemSelectedListener(this);
        }

        navigationView.setNavigationItemSelectedListener(this);
        customAppBar.setOnNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer();
            }
        });

        presenter.attachView(this);
        if (savedInstanceState == null) {
            presenter.clickPeople();
        }
    }

    @Override
    protected void setupActivityComponent(ApplicationComponent applicationComponent) {
        mainComponent = DaggerMainComponent.builder()
                .applicationComponent(applicationComponent)
                .mainModule(new MainModule(this))
                .build();

        mainComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void closeDrawer() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }, 100);
        }
    }

    @Override
    public void openDrawer() {
        if (drawer != null && !drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    public void toggleDrawer() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer != null && !drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void highlightHomeFeed() {
        navigationView.setCheckedItem(R.id.menu_main_nav__home_feed);
        if (navigationSideView != null) {
            navigationSideView.setCheckedItem(R.id.menu_main_nav__home_feed);
        }
    }

    @Override
    public void highlightPeople() {
        navigationView.setCheckedItem(R.id.menu_main_nav__people);
        if (navigationSideView != null) {
            navigationSideView.setCheckedItem(R.id.menu_main_nav__people);
        }
    }

    @Override
    public void highlightFavorites() {
        navigationView.setCheckedItem(R.id.menu_main_nav__favorites);
        if (navigationSideView != null) {
            navigationSideView.setCheckedItem(R.id.menu_main_nav__favorites);
        }
    }

    @Override
    public void highlightMap() {
        navigationView.setCheckedItem(R.id.menu_main_nav__map);
        if (navigationSideView != null) {
            navigationSideView.setCheckedItem(R.id.menu_main_nav__map);
        }
    }

    @Override
    public void highlightSettings() {
        //empty
    }

    @Override
    public void highlightFeedback() {
        //empty
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_nav__people:
                presenter.clickPeople();
                break;

            case R.id.menu_main_nav__home_feed:
                presenter.clickHomeFeed();
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

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!navigator.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public CustomAppBar getCustomAppBar() {
        return customAppBar;
    }

    public ContainersLayout getContainersLayout() {
        return containersLayout;
    }

    public MainContract.Navigator getNavigator() {
        return navigator;
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

}
