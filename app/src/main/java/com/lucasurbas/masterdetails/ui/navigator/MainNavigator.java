package com.lucasurbas.masterdetails.ui.navigator;

import android.support.v4.app.Fragment;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.ui.activity.MainActivity;
import com.lucasurbas.masterdetails.ui.contract.MainContract;
import com.lucasurbas.masterdetails.ui.contract.PeopleContract;
import com.lucasurbas.masterdetails.ui.fragment.DetailsFragment;
import com.lucasurbas.masterdetails.ui.fragment.EmptyFragment;
import com.lucasurbas.masterdetails.ui.fragment.HomeFeedFragment;
import com.lucasurbas.masterdetails.ui.fragment.PeopleFragment;

import javax.inject.Inject;

/**
 * Created by Lucas on 02/01/2017.
 */

public class MainNavigator implements MainContract.Navigator, PeopleContract.Navigator {

    private static final String TAG_DETAILS = "tag_details";
    private static final String TAG_MASTER = "tag_master";
    private MainActivity mainActivity;

    public enum State {
        SINGLE_COLUMN_MASTER, SINGLE_COLUMN_DETAILS, TWO_COLUMNS_EMPTY, TWO_COLUMNS_WITH_DETAILS
    }

    @Inject
    public MainNavigator(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private boolean clearDetails() {
        Fragment details = mainActivity.getSupportFragmentManager().findFragmentByTag(TAG_DETAILS);
        if (details != null) {
            mainActivity.getSupportFragmentManager().beginTransaction().remove(details).commitNow();
            return true;
        }
        return false;
    }

    private void clearMaster() {
        Fragment master = mainActivity.getSupportFragmentManager().findFragmentByTag(TAG_MASTER);
        if (master != null) {
            mainActivity.getSupportFragmentManager().beginTransaction().remove(master).commitNow();
        }
    }

    @Override
    public void goToHomeFeed() {
        mainActivity.getCustomAppBar().setState(State.SINGLE_COLUMN_MASTER);
        mainActivity.getContainersLayout().setState(State.SINGLE_COLUMN_MASTER);
        clearDetails();
        HomeFeedFragment fragment = HomeFeedFragment.newInstance();
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment, TAG_MASTER).commitAllowingStateLoss();
    }

    @Override
    public void goToPeople() {
        mainActivity.getCustomAppBar().setState(State.TWO_COLUMNS_EMPTY);
        mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_EMPTY);
        clearDetails();
        PeopleFragment master = PeopleFragment.newInstance();
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, master, TAG_MASTER).commitAllowingStateLoss();
    }

    @Override
    public void goToFavorites() {
        mainActivity.getCustomAppBar().setState(State.SINGLE_COLUMN_MASTER);
        mainActivity.getContainersLayout().setState(State.SINGLE_COLUMN_MASTER);
        clearDetails();
        String title = "Favorites";
        EmptyFragment fragment = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment, TAG_MASTER).commitAllowingStateLoss();
    }

    @Override
    public void goToMap() {
        mainActivity.getCustomAppBar().setState(State.SINGLE_COLUMN_DETAILS);
        mainActivity.getContainersLayout().setState(State.SINGLE_COLUMN_DETAILS);
        clearMaster();
        String title = "Map";
        EmptyFragment fragment = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS).commitAllowingStateLoss();
    }

    @Override
    public void goToPersonDetails() {
        mainActivity.getCustomAppBar().setState(State.TWO_COLUMNS_WITH_DETAILS);
        mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_WITH_DETAILS);
        DetailsFragment fragment = DetailsFragment.newInstance("Details");
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS).commitAllowingStateLoss();
    }

    @Override
    public void goToSettings() {
        //start new activity
    }

    @Override
    public void goToFeedback() {
        //start new activity
    }

    @Override
    public boolean onBackPressed() {
        State state = mainActivity.getContainersLayout().getState();
        if (state.equals(State.TWO_COLUMNS_WITH_DETAILS) && !mainActivity.getContainersLayout().hasTwoColumns()) {
            if (clearDetails()) {
                mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_EMPTY);
                return true;
            }
        }
        return false;
    }
}
