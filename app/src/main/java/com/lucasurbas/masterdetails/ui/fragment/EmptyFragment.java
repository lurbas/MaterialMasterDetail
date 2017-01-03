package com.lucasurbas.masterdetails.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 02/01/2017.
 */

public class EmptyFragment extends Fragment {

    private static final String KEY_TITLE = "key_title";

    @BindView(R.id.fragment_empty__title) TextView title;

    public static EmptyFragment newInstance(String title) {
        EmptyFragment fragment = new EmptyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
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
        title.setText(getArguments().getString(KEY_TITLE));
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getNavigator().goToDetails();
            }
        });
    }
}
