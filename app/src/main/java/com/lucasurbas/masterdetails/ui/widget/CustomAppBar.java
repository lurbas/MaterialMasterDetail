package com.lucasurbas.masterdetails.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.lucasurbas.masterdetails.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 02/01/2017.
 */

public class CustomAppBar extends FrameLayout {

    @Nullable
    @BindView(R.id.view_main_toolbar__toolbar_general)
    Toolbar toolbarGeneral;
    @BindView(R.id.view_main_toolbar__toolbar_specific) Toolbar toolbarSpecific;

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
}
