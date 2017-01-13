package com.lucasurbas.masterdetail.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucasurbas.masterdetail.R;
import com.lucasurbas.masterdetail.ui.main.MainActivity;
import com.lucasurbas.masterdetail.ui.widget.CustomAppBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 04/01/2017.
 */

public class MapFragment extends Fragment {

    @BindView(R.id.fragment_map__title) TextView title;
    @BindView(R.id.fragment_map__main_icon) ImageView mainIcon;

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        title.setText(getString(R.string.fragment_map__title));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();
    }

    private void setupToolbar() {
        CustomAppBar appBar = ((MainActivity) getActivity()).getCustomAppBar();
        appBar.setTitle("");
        appBar.clearMenu();

        if (!appBar.hasGeneralToolbar()) {
            mainIcon.setImageResource(R.drawable.ic_menu_24dp);
            mainIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).toggleDrawer();
                }
            });
        }
    }
}
