package com.example.whitneybb.login.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whitneybb.login.fragments.create.CreateAccountFragment;
import com.example.whitneybb.login.fragments.login.LoginFragment;


public class LoginTabAdapter extends FragmentPagerAdapter {
    private String[] loginTabs = new String[]{"Login", "Create Account"};

    public LoginTabAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return loginTabs[position];

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 1:
                return new CreateAccountFragment();
            case 0:
            default:
                return new LoginFragment();

        }

    }

    @Override
    public int getCount() {
        return loginTabs.length;
    }
}
