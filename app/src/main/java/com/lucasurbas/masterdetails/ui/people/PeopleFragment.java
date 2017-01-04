package com.lucasurbas.masterdetails.ui.people;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.data.Person;
import com.lucasurbas.masterdetails.injection.people.PeopleModule;
import com.lucasurbas.masterdetails.ui.main.MainActivity;
import com.lucasurbas.masterdetails.ui.widget.CustomAppBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 04/01/2017.
 */

public class PeopleFragment extends Fragment implements PeopleContract.View {

    @BindView(R.id.fragment_people__swipe_refresh) SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fragment_people__recycler_view) RecyclerView recyclerView;

    @Inject PeopleContract.Presenter presenter;

    private PeopleAdapter adapter;

    public static PeopleFragment newInstance() {
        PeopleFragment fragment = new PeopleFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_people, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();
        setupRecyclerView();
        setupSwipeRefresh();

        inject();
        presenter.attachView(this);
        presenter.getPeople();
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PeopleAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        CustomAppBar appBar = ((MainActivity) getActivity()).getCustomAppBar();
        appBar.setTitle(getString(R.string.fragment_people__title));
        appBar.setMenuRes(R.menu.people_general, R.menu.people_specific, R.menu.people_merged);
    }

    private void setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMorePeople();
            }
        });
    }

    private void inject() {
        ((MainActivity) getActivity())
                .getMainComponent()
                .plus(new PeopleModule())
                .inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        //swipeRefreshLayout bug
        if (swipeRefresh != null) {
            swipeRefresh.setRefreshing(false);
            swipeRefresh.destroyDrawingCache();
            swipeRefresh.clearAnimation();
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showPeopleList(List<Person> peopleList) {
        adapter.setPeopleList(peopleList);
    }
}
