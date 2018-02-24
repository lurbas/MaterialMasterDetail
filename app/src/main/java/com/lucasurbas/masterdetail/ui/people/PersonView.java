package com.lucasurbas.masterdetail.ui.people;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.lucasurbas.listitemview.ListItemView;
import com.lucasurbas.masterdetail.R;
import com.lucasurbas.masterdetail.data.Person;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 04/01/2017.
 */

public class PersonView extends FrameLayout {

    @BindView(R.id.item_view_user__row)
    ListItemView row;

    private Person person;

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
        row.setTitle(person.getName());
        row.setSubtitle(person.getDescription());
        row.getAvatarView().setImageDrawable(getResources().getDrawable(R.drawable.circle_black_transparent));
    }

    public void setOnPersonClickListener(final OnPersonClickListener onPersonClickListener) {
        if (onPersonClickListener != null) {
            row.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPersonClickListener.onPersonClick(person);
                }
            });
            row.setOnMenuItemClickListener(new ListItemView.OnMenuItemClickListener() {
                @Override
                public void onActionMenuItemSelected(MenuItem item) {
                    if(item.getItemId() == R.id.menu_person_item__favorite){
                        onPersonClickListener.onPersonActionClick(person);
                    }
                }
            });
        } else {
            row.setOnClickListener(null);
            row.setOnMenuItemClickListener(null);
        }
    }
}
