package com.lucasurbas.masterdetails.ui.navigation;

import android.support.v4.app.Fragment;
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
    private static final String TAG_MASTER = "tag_master";
    private MainActivity mainActivity;

    public enum State {
        SINGLE_COLUMN_MASTER, SINGLE_COLUMN_DETAILS, TWO_COLUMNS
    }

    @Inject
    public MainNavigation(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private boolean hasTwoColumns() {
        return mainActivity.findViewById(R.id.activity_main__space_master) != null;
    }

    private void singleColumnMaster() {
        mainActivity.state = State.SINGLE_COLUMN_MASTER;
        mainActivity.getCustomAppBar().setState(State.SINGLE_COLUMN_MASTER);
        if (hasTwoColumns()) {
            mainActivity.findViewById(R.id.activity_main__space_master).setVisibility(View.GONE);
            mainActivity.findViewById(R.id.activity_main__space_details).setVisibility(View.GONE);
        }
        mainActivity.findViewById(R.id.activity_main__frame_details).setVisibility(View.GONE);
        mainActivity.findViewById(R.id.activity_main__frame_master).setVisibility(View.VISIBLE);
    }

    private void clearDetails() {
        Fragment details = mainActivity.getSupportFragmentManager().findFragmentByTag(TAG_DETAILS);
        if (details != null) {
            mainActivity.getSupportFragmentManager().beginTransaction().remove(details).commitNow();
        }
    }

    private void singleColumnDetails() {
        mainActivity.state = State.SINGLE_COLUMN_DETAILS;
        mainActivity.getCustomAppBar().setState(State.SINGLE_COLUMN_DETAILS);
        if (hasTwoColumns()) {
            mainActivity.findViewById(R.id.activity_main__space_master).setVisibility(View.GONE);
            mainActivity.findViewById(R.id.activity_main__space_details).setVisibility(View.GONE);
        }
        mainActivity.findViewById(R.id.activity_main__frame_master).setVisibility(View.GONE);
        mainActivity.findViewById(R.id.activity_main__frame_details).setVisibility(View.VISIBLE);
    }

    private void clearMaster() {
        Fragment master = mainActivity.getSupportFragmentManager().findFragmentByTag(TAG_MASTER);
        if (master != null) {
            mainActivity.getSupportFragmentManager().beginTransaction().remove(master).commitNow();
        }
    }

    private void twoColumns() {
        mainActivity.state = State.TWO_COLUMNS;
        mainActivity.getCustomAppBar().setState(State.TWO_COLUMNS);
        if (hasTwoColumns()) {
            mainActivity.findViewById(R.id.activity_main__space_master).setVisibility(View.VISIBLE);
            mainActivity.findViewById(R.id.activity_main__space_details).setVisibility(View.VISIBLE);
        }
        mainActivity.findViewById(R.id.activity_main__frame_details).setVisibility(View.VISIBLE);
        mainActivity.findViewById(R.id.activity_main__frame_master).setVisibility(View.VISIBLE);
    }

    @Override
    public void goToHomeFeed() {
        singleColumnMaster();
        clearDetails();
        String title = "Home Feed";
        EmptyFragment fragment = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment, TAG_MASTER).commitAllowingStateLoss();
    }

    @Override
    public void goToPeople() {
        twoColumns();
        clearDetails();
        if (hasTwoColumns()) {
            EmptyFragment details = EmptyFragment.newInstance("Empty Details");
            mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_details, details, TAG_DETAILS).commitAllowingStateLoss();
        }
        String title = "People";
        EmptyFragment master = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, master, TAG_MASTER).commitAllowingStateLoss();
    }

    @Override
    public void goToFavorites() {
        singleColumnMaster();
        clearDetails();
        String title = "Favorites";
        EmptyFragment fragment = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, fragment, TAG_MASTER).commitAllowingStateLoss();
    }

    @Override
    public void goToMap() {
        singleColumnDetails();
        clearMaster();
        String title = "Map";
        EmptyFragment fragment = EmptyFragment.newInstance(title);
        mainActivity.getCustomAppBar().setTitle(title);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS).commitAllowingStateLoss();
    }

    @Override
    public void goToDetails() {
        DetailsFragment fragment = DetailsFragment.newInstance("Details");
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS).commitAllowingStateLoss();
    }

    @Override
    public void restoreState(State state) {
        switch (state) {
            case SINGLE_COLUMN_MASTER:
                singleColumnMaster();
                break;
            case SINGLE_COLUMN_DETAILS:
                singleColumnDetails();
                break;
            case TWO_COLUMNS:
                twoColumns();
                break;
        }
    }

    @Override
    public void goToSettings() {
        //start new activity
    }

    @Override
    public void goToFeedback() {
        //start new activity
    }
}
