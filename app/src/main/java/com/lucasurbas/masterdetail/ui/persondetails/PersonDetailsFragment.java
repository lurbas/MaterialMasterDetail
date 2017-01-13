package com.lucasurbas.masterdetail.ui.persondetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucasurbas.masterdetail.R;
import com.lucasurbas.masterdetail.data.Person;
import com.lucasurbas.masterdetail.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 02/01/2017.
 */

public class PersonDetailsFragment extends Fragment {

    private static final String KEY_PERSON = "key_person";

    @BindView(R.id.fragment_person_details__toolbar) Toolbar toolbar;
    @BindView(R.id.fragment_person_details__description) TextView description;

    private Person person;

    public static PersonDetailsFragment newInstance(Person person) {
        PersonDetailsFragment fragment = new PersonDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PERSON, person);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.person = getArguments().getParcelable(KEY_PERSON);

        setupToolbar();
        setPerson(person);
    }

    private void setupToolbar() {
        toolbar.inflateMenu(R.menu.person_details);

        if (!((MainActivity) getActivity()).getContainersLayout().hasTwoColumns()) {
            toolbar.setNavigationIcon(R.drawable.ic_back_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });
        }
    }

    private void setPerson(Person person) {
        toolbar.setTitle(person.getName());
        description.setText(person.getDescription());
    }
}
