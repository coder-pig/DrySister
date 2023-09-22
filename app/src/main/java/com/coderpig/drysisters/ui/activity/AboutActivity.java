package com.coderpig.drysisters.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.coderpig.drysisters.R;
import com.coderpig.drysisters.ui.fragment.AboutFragment;
import com.r0adkll.slidr.Slidr;

/**
 * 描述：关于的Activity
 *
 * @author jay on 2018/1/12 14:01
 */

public class AboutActivity extends AppCompatActivity{

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Slidr.attach(this);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("关于");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
        getSupportFragmentManager().beginTransaction().replace(R.id.cly_root, AboutFragment.newInstance()).commit();
    }
}
