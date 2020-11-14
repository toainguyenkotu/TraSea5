package com.example.trasea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.trasea.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addTabs();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.story_toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.vp_story);
        tabLayout = findViewById(R.id.tab_story);
    }

    private void addTabs() {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_find_no_fill));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_add_no_fill));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_noti));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_personal));

        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_filled);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()){
                    case 0 :
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_filled);
                        break;
                    case 1 :
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search);
                        break;
                    case 2:
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_add);
                        break;
                    case 3 :
                        tabLayout.getTabAt(3).setIcon(R.drawable.ic_noti_filled);
                        break;
                    case 4 :
                        tabLayout.getTabAt(4).setIcon(R.drawable.ic_personal_filled);
                        break;


                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 :
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
                        break;
                    case 1 :
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_find_no_fill);
                        break;
                    case 2:
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_add_no_fill);
                        break;
                    case 3 :
                        tabLayout.getTabAt(3).setIcon(R.drawable.ic_noti);
                        break;
                    case 4 :
                        tabLayout.getTabAt(4).setIcon(R.drawable.ic_personal);
                        break;


                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}