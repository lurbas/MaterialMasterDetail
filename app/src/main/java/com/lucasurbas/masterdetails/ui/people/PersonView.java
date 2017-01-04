package com.lucasurbas.masterdetails.ui.people;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lucasurbas.masterdetails.R;
import com.lucasurbas.masterdetails.data.Person;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 04/01/2017.
 */

public class PersonView extends FrameLayout {

    @BindView(R.id.item_view_user__name) TextView name;
    @BindView(R.id.item_view_user__description) TextView description;

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
        name.setText(person.getName());
        description.setText(person.getDescription());
    }
}
