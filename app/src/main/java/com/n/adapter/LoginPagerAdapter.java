package com.n.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.n.fragment.JoinFragment;
import com.n.fragment.LoginFragment;

/**
 * Created by N on 2016-01-24.
 */
public class LoginPagerAdapter extends FragmentStatePagerAdapter{

    public LoginPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new LoginFragment();
                break;
            case 1:
                fragment = new JoinFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
