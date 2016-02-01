package com.n.meallog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.n.adapter.LoginPagerAdapter;
import com.n.view.LockableViewPager;

public class LoginActivity extends AppCompatActivity {
    private LockableViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewPager = (LockableViewPager) findViewById(R.id.login_viewpager);
        LoginPagerAdapter pagerAdapter = new LoginPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setSwipeable(false);
    }

    public LockableViewPager getViewPager() { return viewPager;}
}
