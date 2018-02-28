package com.coderpig.drysisters.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.coderpig.drysisters.R;
import com.coderpig.drysisters.ui.fragment.SettingFragment;
import com.r0adkll.slidr.Slidr;

/**
 * 描述：设置的Activity
 *
 * @author jay on 2018/1/12 14:01
 */

public class SettingActivity extends AppCompatActivity{

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Slidr.attach(this);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());

        getSupportFragmentManager().beginTransaction().replace(R.id.cly_root, SettingFragment.newInstance()).commit();

    }


}
