package com.coderpig.drysisters.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.coderpig.drysisters.DryConstant;
import com.coderpig.drysisters.R;
import com.coderpig.drysisters.ui.fragment.LittleSisterFragment;
import com.coderpig.drysisters.ui.fragment.NewsFragment;
import com.coderpig.drysisters.ui.fragment.SubwayFragment;
import com.coderpig.drysisters.ui.fragment.ToolsFragment;
import com.coderpig.drysisters.ui.fragment.WeatherFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private ConstraintLayout cly_main_content;
    private FragmentManager mFgManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFgManager = getSupportFragmentManager();
        initView();
        initData();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        nav_view = findViewById(R.id.nav_view);
        drawer_layout = findViewById(R.id.drawer_layout);
        cly_main_content = findViewById(R.id.cly_main_content);

        setSupportActionBar(toolbar);
        nav_view.setItemIconTintList(null);
        nav_view.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initData() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_see_little_sister:
                if (mFgManager.findFragmentByTag(DryConstant.FG_LITTLE_SISTER) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            LittleSisterFragment.newInstance(), DryConstant.FG_LITTLE_SISTER).commit();
                }
                break;
            case R.id.nav_see_news:
                if (mFgManager.findFragmentByTag(DryConstant.FG_NEWS) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            NewsFragment.newInstance(), DryConstant.FG_NEWS).commit();
                }
                break;
            case R.id.nav_use_check_weather:
                if (mFgManager.findFragmentByTag(DryConstant.FG_WEATHER) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            WeatherFragment.newInstance(), DryConstant.FG_WEATHER).commit();
                }
                break;
            case R.id.nav_use_check_subway:
                if (mFgManager.findFragmentByTag(DryConstant.FG_SUBWAY) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            SubwayFragment.newInstance(), DryConstant.FG_SUBWAY).commit();
                }
                break;
            case R.id.nav_use_tools:
                if (mFgManager.findFragmentByTag(DryConstant.FG_TOOLS) == null) {
                    mFgManager.beginTransaction().replace(R.id.cly_main_content,
                            ToolsFragment.newInstance(), DryConstant.FG_TOOLS).commit();
                }
                break;
            case R.id.nav_else_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.nav_else_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
