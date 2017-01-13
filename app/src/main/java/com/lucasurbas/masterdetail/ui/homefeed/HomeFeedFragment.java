package com.lucasurbas.masterdetail.ui.homefeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucasurbas.masterdetail.R;
import com.lucasurbas.masterdetail.ui.main.MainActivity;
import com.lucasurbas.masterdetail.ui.widget.CustomAppBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 03/01/2017.
 */

public class HomeFeedFragment extends Fragment {

    @BindView(R.id.fragment_empty__title) TextView title;

    public static HomeFeedFragment newInstance() {
        HomeFeedFragment fragment = new HomeFeedFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        title.setText(getString(R.string.fragment_homefeed__title));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();
    }

    private void setupToolbar() {
        CustomAppBar appBar = ((MainActivity)getActivity()).getCustomAppBar();
        appBar.setTitle(getString(R.string.fragment_homefeed__title));
        appBar.setMenuRes(R.menu.homefeed_general, R.menu.homefeed_specific, R.menu.homefeed_merged);
    }
}
