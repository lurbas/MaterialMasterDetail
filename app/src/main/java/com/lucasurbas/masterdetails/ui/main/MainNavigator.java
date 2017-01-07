package com.lucasurbas.masterdetails.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.data.Person;
import com.lucasurbas.masterdetails.ui.favorites.FavoritesFragment;
import com.lucasurbas.masterdetails.ui.homefeed.HomeFeedFragment;
import com.lucasurbas.masterdetails.ui.map.MapFragment;
import com.lucasurbas.masterdetails.ui.people.PeopleContract;
import com.lucasurbas.masterdetails.ui.people.PeopleFragment;
import com.lucasurbas.masterdetails.ui.persondetails.PersonDetailsFragment;

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
        final Fragment details = mainActivity.getSupportFragmentManager().findFragmentByTag(TAG_DETAILS);
        if (details != null) {
            mainActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .remove(details)
                    .commitNow();
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
        clearDetails();
        mainActivity.getCustomAppBar().setState(State.SINGLE_COLUMN_MASTER);
        mainActivity.getContainersLayout().setState(State.SINGLE_COLUMN_MASTER);
        HomeFeedFragment fragment = HomeFeedFragment.newInstance();
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment, TAG_MASTER).commitAllowingStateLoss();
    }

    @Override
    public void goToPeople() {
        clearDetails();
        mainActivity.getCustomAppBar().setState(State.TWO_COLUMNS_EMPTY);
        mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_EMPTY);
        PeopleFragment master = PeopleFragment.newInstance();
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, master, TAG_MASTER).commitAllowingStateLoss();
    }

    @Override
    public void goToFavorites() {
        clearDetails();
        mainActivity.getCustomAppBar().setState(State.SINGLE_COLUMN_MASTER);
        mainActivity.getContainersLayout().setState(State.SINGLE_COLUMN_MASTER);
        FavoritesFragment fragment = FavoritesFragment.newInstance();
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment, TAG_MASTER).commitAllowingStateLoss();
    }

    @Override
    public void goToMap() {
        clearMaster();
        mainActivity.getCustomAppBar().setState(State.SINGLE_COLUMN_DETAILS);
        mainActivity.getContainersLayout().setState(State.SINGLE_COLUMN_DETAILS);
        MapFragment fragment = MapFragment.newInstance();
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS).commitAllowingStateLoss();
    }

    @Override
    public void goToPersonDetails(Person person) {
        mainActivity.getCustomAppBar().setState(State.TWO_COLUMNS_WITH_DETAILS);
        mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_WITH_DETAILS);
        PersonDetailsFragment fragment = PersonDetailsFragment.newInstance(person);
        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS)
                .commitAllowingStateLoss();
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
