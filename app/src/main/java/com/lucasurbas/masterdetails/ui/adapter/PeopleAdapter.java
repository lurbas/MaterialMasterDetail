package com.lucasurbas.masterdetails.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.data.Person;
import com.lucasurbas.masterdetails.ui.widget.PersonView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 04/01/2017.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PersonViewHolder> {

    private List<Person> peopleList;

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        public PersonView personView;

        public PersonViewHolder(PersonView personView) {
            super(personView);
            this.personView = personView;
        }
    }

    public PeopleAdapter() {
        this.peopleList = new ArrayList<Person>();
    }

    @Override
    public PeopleAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PersonView view = (PersonView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_user, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.personView.setUser(peopleList.get(position));
    }

    public void setPeopleList(List<Person> peopleList){
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }
}
