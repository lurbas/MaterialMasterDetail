package com.lucasurbas.masterdetails.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lucasurbas.masterdetails.app.MasterDetailsApplication;
import com.lucasurbas.masterdetails.injection.app.ApplicationComponent;

/**
 * Created by Lucas on 19/06/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(MasterDetailsApplication.getAppComponent(this));
    }

    protected abstract void setupActivityComponent(ApplicationComponent applicationComponent);
}
