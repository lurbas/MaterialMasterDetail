package com.lucasurbas.masterdetail.ui.people;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lucasurbas.masterdetail.R;
import com.lucasurbas.masterdetail.data.Person;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 04/01/2017.
 */

public class PersonView extends FrameLayout {

    @BindView(R.id.item_view_user__row) View row;
    @BindView(R.id.item_view_user__name) TextView name;
    @BindView(R.id.item_view_user__description) TextView description;
    @BindView(R.id.item_view_user__action) View action;

    private Person person;
    private PersonView.OnPersonClickListener onPersonClickListener;

    public interface OnPersonClickListener {

        void onPersonClick(Person person);

        void onPersonActionClick(Person person);
    }

    public PersonView(Context context) {
        super(context);
        init();
    }

    public PersonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_view_user_internal, this, true);
        ButterKnife.bind(this);
    }

    public void setUser(Person person) {
        this.person = person;
        name.setText(person.getName());
        description.setText(person.getDescription());
    }

    public void setonPersonClickListener(final OnPersonClickListener onPersonClickListener) {
        this.onPersonClickListener = onPersonClickListener;
        if (onPersonClickListener != null) {
            row.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPersonClickListener.onPersonClick(person);
                }
            });
            action.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPersonClickListener.onPersonActionClick(person);
                }
            });
        } else {
            row.setOnClickListener(null);
            action.setOnClickListener(null);
        }
    }
}
