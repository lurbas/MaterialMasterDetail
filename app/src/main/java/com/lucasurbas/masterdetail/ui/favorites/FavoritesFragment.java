package com.lucasurbas.masterdetail.ui.favorites;

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
 * Created by Lucas on 04/01/2017.
 */

public class FavoritesFragment extends Fragment {

    @BindView(R.id.fragment_empty__title) TextView title;

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
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
        title.setText(getString(R.string.fragment_favorites__title));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();
    }

    private void setupToolbar() {
        CustomAppBar appBar = ((MainActivity)getActivity()).getCustomAppBar();
        appBar.setTitle(getString(R.string.fragment_favorites__title));
        appBar.setMenuRes(R.menu.favorites_general, R.menu.favorites_specific, R.menu.favorites_merged);
    }
}
