package com.lucasurbas.masterdetails.ui.navigation;

import android.view.View;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.ui.activity.MainActivity;
import com.lucasurbas.masterdetails.ui.contract.MainContract;
import com.lucasurbas.masterdetails.ui.fragment.DetailsFragment;
import com.lucasurbas.masterdetails.ui.fragment.EmptyFragment;

import javax.inject.Inject;

/**
 * Created by Lucas on 02/01/2017.
 */

public class MainNavigation implements MainContract.Navigation {

    private static final String TAG_DETAILS = "tag_details";
    private MainActivity mainActivity;

    @Inject
    public MainNavigation(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    private void singleColumn() {
        mainActivity.findViewById(R.id.activity_main__space_master).setVisibility(View.GONE);
        mainActivity.findViewById(R.id.activity_main__space_details).setVisibility(View.GONE);
        mainActivity.findViewById(R.id.activity_main__frame_details).setVisibility(View.GONE);

//        Fragment details = mainActivity.getSupportFragmentManager().findFragmentByTag(TAG_DETAILS);
//        if (details != null) {
//            mainActivity.getSupportFragmentManager().beginTransaction().remove(details).commitAllowingStateLoss();
//        }
    }

    private void twoColumns() {
        mainActivity.findViewById(R.id.activity_main__space_master).setVisibility(View.VISIBLE);
        mainActivity.findViewById(R.id.activity_main__space_details).setVisibility(View.VISIBLE);
        mainActivity.findViewById(R.id.activity_main__frame_details).setVisibility(View.VISIBLE);
    }


    @Override
    public void goToHomeFeed() {
        singleColumn();
        String title = "Home Feed";
        EmptyFragment fragment = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment).commitAllowingStateLoss();
    }

    @Override
    public void goToPeople() {
        singleColumn();
        String title = "People";
        EmptyFragment fragment = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment).commitAllowingStateLoss();
    }

    @Override
    public void goToFavorites() {
        singleColumn();
        String title = "Favorites";
        EmptyFragment fragment = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment).commitAllowingStateLoss();
    }

    @Override
    public void goToMap() {
        singleColumn();
        String title = "Map";
        EmptyFragment fragment = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment).commitAllowingStateLoss();
    }

    @Override
    public void goToDetails() {
        twoColumns();
        DetailsFragment fragment = DetailsFragment.newInstance("Details");
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS).commitAllowingStateLoss();
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
