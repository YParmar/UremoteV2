package com.example.rk.uremotev2.homescreen;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.base.BaseActivity;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomeActivityPresenter> implements HomeActivityContract.HomeView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = new HomeActivityPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initActionBar(toolbar, true, "Home");


    }

}
