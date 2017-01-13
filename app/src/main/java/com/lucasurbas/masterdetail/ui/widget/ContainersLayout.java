package com.lucasurbas.masterdetail.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lucasurbas.masterdetail.R;
import com.lucasurbas.masterdetail.ui.main.MainNavigator;
import com.lucasurbas.masterdetail.ui.util.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.animation.ObjectAnimator.ofFloat;

/**
 * Created by Lucas on 03/01/2017.
 */

public class ContainersLayout extends FrameLayout {

    public static final int ANIM_DURATION = 250;

    private static final String STATE_SUPER = "state_super";
    private static final String STATE_CONTAINERS_STATE = "state_containers_state";

    @Nullable
    @BindView(R.id.activity_main__space_master)
    View spaceMaster;
    @Nullable
    @BindView(R.id.activity_main__space_details)
    View spaceDetails;

    @BindView(R.id.activity_main__frame_master)
    ViewGroup frameMaster;
    @BindView(R.id.activity_main__frame_details)
    ViewGroup frameDetails;

    private MainNavigator.State state;

    public ContainersLayout(Context context) {
        super(context);
        init();
    }

    public ContainersLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ContainersLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ContainersLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_main_containers, this, true);
        ButterKnife.bind(this);
    }

    public boolean hasTwoColumns() {
        return spaceMaster != null && spaceDetails != null;
    }

    private void singleColumnMaster() {
        if (hasTwoColumns()) {
            spaceMaster.setVisibility(View.GONE);
            spaceDetails.setVisibility(View.GONE);
            frameDetails.setVisibility(View.GONE);
        } else {
            animateOutFrameDetails();
        }
        frameMaster.setVisibility(View.VISIBLE);
    }

    private void singleColumnDetails() {
        if (hasTwoColumns()) {
            spaceMaster.setVisibility(View.GONE);
            spaceDetails.setVisibility(View.GONE);
        }
        frameMaster.setVisibility(View.GONE);
        frameDetails.setVisibility(View.VISIBLE);
    }

    private void twoColumnsEmpty() {
        if (hasTwoColumns()) {
            spaceMaster.setVisibility(View.VISIBLE);
            spaceDetails.setVisibility(View.VISIBLE);
            frameDetails.setVisibility(View.VISIBLE);
        } else {
            animateOutFrameDetails();
        }
        frameMaster.setVisibility(View.VISIBLE);
    }

    private void twoColumnsWithDetails() {
        if (hasTwoColumns()) {
            spaceMaster.setVisibility(View.VISIBLE);
            spaceDetails.setVisibility(View.VISIBLE);
            frameMaster.setVisibility(View.VISIBLE);
            frameDetails.setVisibility(View.VISIBLE);
        } else {
            animateInFrameDetails();
        }
    }

    private void animateInFrameDetails() {
        frameDetails.setVisibility(View.VISIBLE);
        ViewUtils.onLaidOut(frameDetails, new Runnable() {
            @Override
            public void run() {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(frameDetails, View.ALPHA, 0.4f, 1f);
                ObjectAnimator translate = ofFloat(frameDetails, View.TRANSLATION_Y, frameDetails.getHeight() * 0.3f, 0f);

                AnimatorSet set = new AnimatorSet();
                set.playTogether(alpha, translate);
                set.setDuration(ANIM_DURATION);
                set.setInterpolator(new LinearOutSlowInInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameMaster.setVisibility(View.GONE);
                    }
                });
                set.start();
            }
        });
    }

    private void animateOutFrameDetails() {
        ViewUtils.onLaidOut(frameDetails, new Runnable() {
            @Override
            public void run() {
                if (!frameDetails.isShown()) {
                    return;
                }
                ObjectAnimator alpha = ObjectAnimator.ofFloat(frameDetails, View.ALPHA, 1f, 0f);
                ObjectAnimator translate = ofFloat(frameDetails, View.TRANSLATION_Y, 0f, frameDetails.getHeight() * 0.3f);

                AnimatorSet set = new AnimatorSet();
                set.playTogether(alpha, translate);
                set.setDuration(ANIM_DURATION);
                set.setInterpolator(new FastOutLinearInInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameDetails.setAlpha(1f);
                        frameDetails.setTranslationY(0);
                        frameDetails.setVisibility(View.GONE);
                    }
                });
                set.start();
            }
        });
    }

    public void setState(MainNavigator.State state) {
        this.state = state;
        switch (state) {
            case SINGLE_COLUMN_MASTER:
                singleColumnMaster();
                break;
            case SINGLE_COLUMN_DETAILS:
                singleColumnDetails();
                break;
            case TWO_COLUMNS_EMPTY:
                twoColumnsEmpty();
                break;
            case TWO_COLUMNS_WITH_DETAILS:
                twoColumnsWithDetails();
                break;
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        bundle.putString(STATE_CONTAINERS_STATE, state.name());
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            setState(MainNavigator.State.valueOf(bundle.getString(STATE_CONTAINERS_STATE)));
            parcelable = bundle.getParcelable(STATE_SUPER);
        }
        super.onRestoreInstanceState(parcelable);
    }

    public MainNavigator.State getState() {
        return state;
    }
}
