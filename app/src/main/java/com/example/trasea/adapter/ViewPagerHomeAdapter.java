package com.example.trasea.adapter;

import android.app.Notification;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.trasea.fragments.AddFrag;
import com.example.trasea.fragments.HomeFrag;
import com.example.trasea.fragments.NotificationFrag;
import com.example.trasea.fragments.ProfileFrag;
import com.example.trasea.fragments.SearchFrag;

public class ViewPagerHomeAdapter extends FragmentStatePagerAdapter {

    int noOfTabs;

    public ViewPagerHomeAdapter(@NonNull FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new HomeFrag();

            case 1:
                return new SearchFrag();


            case 2:
                return new AddFrag();


            case 3:
                return new NotificationFrag();


            case 4:
                return new ProfileFrag();


            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
