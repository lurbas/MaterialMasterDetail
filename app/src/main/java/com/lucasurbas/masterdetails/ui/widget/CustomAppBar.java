package com.lucasurbas.masterdetails.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.ui.main.MainNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 02/01/2017.
 */

public class CustomAppBar extends AppBarLayout {

    private static final String STATE_SUPER = "state_super";
    private static final String STATE_TITLE = "state_title";
    private static final String STATE_TOOLBAR_STATE = "state_toolbar_state";

    @Nullable
    @BindView(R.id.view_main_toolbar__toolbar_general)
    Toolbar toolbarGeneral;
    @BindView(R.id.view_main_toolbar__toolbar_specific) Toolbar toolbarSpecific;
    @Nullable
    @BindView(R.id.view_main_toolbar__space_toolbar)
    View space;

    private MainNavigator.State state;

    public CustomAppBar(Context context) {
        super(context);
        init();
    }

    public CustomAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_main_toolbar, this, true);
        ButterKnife.bind(this);

        if (toolbarGeneral != null) {
            toolbarGeneral.setNavigationIcon(R.drawable.ic_menu_24dp);
        } else {
            toolbarSpecific.setNavigationIcon(R.drawable.ic_menu_24dp);
        }
    }

    public void setOnNavigationClickListener(OnClickListener onNavigationClickListener) {
        if (toolbarGeneral != null) {
            toolbarGeneral.setNavigationOnClickListener(onNavigationClickListener);
        } else {
            toolbarSpecific.setNavigationOnClickListener(onNavigationClickListener);
        }
    }

    public void setTitle(String title) {
        toolbarSpecific.setTitle(title);
    }

    public void clearMenu() {
        toolbarSpecific.getMenu().clear();
        if (toolbarGeneral != null) {
            toolbarGeneral.getMenu().clear();
        }
    }

    public void setMenuRes(@MenuRes int menuGeneral, @MenuRes int menuSpecific, @MenuRes int menuMerged) {
        toolbarSpecific.getMenu().clear();
        if (toolbarGeneral != null) {
            toolbarGeneral.getMenu().clear();
            toolbarGeneral.inflateMenu(menuGeneral);
            toolbarSpecific.inflateMenu(menuSpecific);
        } else {
            toolbarSpecific.inflateMenu(menuMerged);
        }
    }

    public void setState(MainNavigator.State state) {
        this.state = state;
        switch (state) {
            case SINGLE_COLUMN_MASTER:
            case SINGLE_COLUMN_DETAILS:
                if (space != null) {
                    space.setVisibility(GONE);
                }
                break;
            case TWO_COLUMNS_EMPTY:
            case TWO_COLUMNS_WITH_DETAILS:
                if (space != null) {
                    space.setVisibility(VISIBLE);
                }
                break;
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        bundle.putString(STATE_TITLE, (String) toolbarSpecific.getTitle());
        bundle.putString(STATE_TOOLBAR_STATE, state.name());
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            toolbarSpecific.setTitle(bundle.getString(STATE_TITLE));
            setState(MainNavigator.State.valueOf(bundle.getString(STATE_TOOLBAR_STATE)));
            parcelable = bundle.getParcelable(STATE_SUPER);
        }
        super.onRestoreInstanceState(parcelable);
    }
}
