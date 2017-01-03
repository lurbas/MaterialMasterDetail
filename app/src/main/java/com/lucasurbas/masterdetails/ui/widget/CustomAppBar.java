package com.lucasurbas.masterdetails.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.ui.navigation.MainNavigation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 02/01/2017.
 */

public class CustomAppBar extends FrameLayout {

    private static final String STATE_SUPER = "state_super";
    private static final String STATE_TITLE = "state_title";
    private static final String STATE_TOOLBAR_STATE = "state_toolbar_state";

    @Nullable
    @BindView(R.id.view_main_toolbar__toolbar_general)
    Toolbar toolbarGeneral;
    @BindView(R.id.view_main_toolbar__toolbar_specific) Toolbar toolbarSpecific;
    @Nullable
    @BindView(R.id.view_main_toolbar___space_toolbar)
    View space;

    private MainNavigation.State state;

    public CustomAppBar(Context context) {
        super(context);
        init();
    }

    public CustomAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomAppBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomAppBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

    public void setMenuRes(@MenuRes int menuGeneral, @MenuRes int menuSpecific, @MenuRes int menuMerged) {
        if (toolbarGeneral != null) {
            toolbarGeneral.inflateMenu(menuGeneral);
            toolbarSpecific.inflateMenu(menuSpecific);
        } else {
            toolbarSpecific.inflateMenu(menuMerged);
        }
    }

    public void setState(MainNavigation.State state) {
        this.state = state;
        switch (state) {
            case SINGLE_COLUMN_MASTER:
            case SINGLE_COLUMN_DETAILS:
                if (space != null) {
                    space.setVisibility(GONE);
                }
                break;
            case TWO_COLUMNS:
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
            setState(MainNavigation.State.valueOf(bundle.getString(STATE_TOOLBAR_STATE)));
            parcelable = bundle.getParcelable(STATE_SUPER);
        }
        super.onRestoreInstanceState(parcelable);
    }
}
