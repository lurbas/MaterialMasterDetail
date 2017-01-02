package com.lucasurbas.masterdetails.ui.navigation;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.ui.activity.MainActivity;
import com.lucasurbas.masterdetails.ui.contract.MainContract;
import com.lucasurbas.masterdetails.ui.fragment.EmptyFragment;

import javax.inject.Inject;

/**
 * Created by Lucas on 02/01/2017.
 */

public class MainNavigation implements MainContract.Navigation {

    private MainActivity mainActivity;

    @Inject
    public MainNavigation(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    public void goToHomeFeed() {
        EmptyFragment fragment = EmptyFragment.newInstance("HomeFeed");
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment).commitAllowingStateLoss();
    }

    @Override
    public void goToPeople() {
        EmptyFragment fragment = EmptyFragment.newInstance("People");
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment).commitAllowingStateLoss();
    }

    @Override
    public void goToFavorites() {
        EmptyFragment fragment = EmptyFragment.newInstance("Favorites");
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment).commitAllowingStateLoss();
    }

    @Override
    public void goToMap() {
        EmptyFragment fragment = EmptyFragment.newInstance("Map");
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment).commitAllowingStateLoss();
    }

    @Override
    public void goToSettings() {

    }

    @Override
    public void goToFeedback() {

    }

//    private void openDrawer() {
//        DrawerLayout drawer = (DrawerLayout) mainActivity.findViewById(R.id.drawer_layout);
//        if (drawer != null && !drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.openDrawer(GravityCompat.START);
//        }
//    }
//
//    private void closeDrawer() {
//        DrawerLayout drawer = (DrawerLayout) mainActivity.findViewById(R.id.drawer_layout);
//        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        }
//    }
}
