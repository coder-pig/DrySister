package com.coderpig.drysisters.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.coderpig.drysisters.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private FloatingActionButton fab_github;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        nav_view = findViewById(R.id.nav_view);
        drawer_layout = findViewById(R.id.drawer_layout);
        fab_github = findViewById(R.id.fab_github);

        setSupportActionBar(toolbar);
        nav_view.setItemIconTintList(null);
        nav_view.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        fab_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getResources().getString(R.string.tip), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void initData() { }

    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_xiaojiejie:
                break;
            case R.id.nav_weather:
                break;
            case R.id.nav_news:
                break;
            case R.id.nav_tools:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_about:
                break;
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
