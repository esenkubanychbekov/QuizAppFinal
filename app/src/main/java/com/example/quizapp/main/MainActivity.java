package com.example.quizapp.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.quizapp.R;
import com.example.quizapp.history.HistoryFragment;
import com.example.quizapp.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;
    private BottomNavigationView mBottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.main_view_pager);
        mBottomNav = findViewById(R.id.main_bottom_nav);

        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mBottomNav.setOnNavigationItemSelectedListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                int selectedItem = R.id.nav_main;
                switch (position) {
                    case 1:
                        selectedItem = R.id.nav_history;
                        break;
                    case 2:
                        selectedItem = R.id.nav_settings;
                        break;
                }

                mBottomNav.setSelectedItemId(selectedItem);

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int page = 0;
        switch (menuItem.getItemId()) {
            case R.id.nav_history:
                page = 1;
            case R.id.nav_settings:
                page = 2;

        }
        mViewPager.setCurrentItem(page);
        return false;
    }


    public class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = MainFragment.newInstance();
                    break;
                case 1:
                    fragment = HistoryFragment.newInstance();
                    break;
                default:
                    fragment =
                            SettingsFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
