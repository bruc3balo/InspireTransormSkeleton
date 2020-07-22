package com.example.whitneybb.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.whitneybb.R;
import com.example.whitneybb.login.adapter.LoginTabAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);
        ViewPager loginPager = findViewById(R.id.loginViewPager);
        final LoginTabAdapter loginTabAdapter = new LoginTabAdapter(getSupportFragmentManager());
        loginPager.setAdapter(loginTabAdapter);
        TabLayout loginTabs = findViewById(R.id.loginTab);
        loginTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                    case 1:
                        tab.setText(loginTabAdapter.getPageTitle(tab.getPosition()));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        loginPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(loginTabs));

        loginTabs.setupWithViewPager(loginPager);

        Objects.requireNonNull(loginTabs.getTabAt(0)).setText(loginTabAdapter.getPageTitle(0));

        Objects.requireNonNull(loginTabs.getTabAt(1)).setText(loginTabAdapter.getPageTitle(1));



        FirebaseApp.initializeApp(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}