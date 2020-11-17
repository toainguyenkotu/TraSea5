package com.example.trasea.fragments;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trasea.EditProfileActivity;
import com.example.trasea.R;
import com.example.trasea.adapter.ViewPagerStorageImageAdapter;
import com.google.android.material.tabs.TabLayout;

public class ProfileFrag extends Fragment implements View.OnClickListener {

    Button btnEditProdile;
    FrameLayout flHeader;
    ViewPager viewPager;
    ViewPagerStorageImageAdapter viewPagerAdapter;
    TabLayout tabLayout;
    ImageView iv_storageImage;
    LinearLayout ln_profile, ln_post;


    public ProfileFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init(view);
        clickPostForHideHeader(view);
        addTabs();

    }

    private void init(View view) {
        flHeader = view.findViewById(R.id.fl_header);

        btnEditProdile = view.findViewById(R.id.btnEditProfile);
        btnEditProdile.setOnClickListener(this);

        tabLayout = view.findViewById(R.id.tab_profile);
        viewPager = view.findViewById(R.id.vp_profile);
        iv_storageImage = view.findViewById(R.id.ivStorageImage);
        ln_profile = view.findViewById(R.id.ln_profile);



    }

    private void clickPostForHideHeader(View view) {
        ln_post = view.findViewById(R.id.ln_Posts);
        ln_post.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    flHeader.setVisibility(View.GONE);
                }
                return true;
            }
        });
    }

    private void addTabs() {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_stogeimage));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_listcontact));

        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //lien ket viewPager
        viewPagerAdapter = new ViewPagerStorageImageAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_stogeimage);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.getTabAt(0).setIcon(R.drawable.icon_stogeimage);
                        break;
                    case 1:
                        tabLayout.getTabAt(1).setIcon(R.drawable.icon_listcontact);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.getTabAt(0).setIcon(null);
                        break;
                    case 1:
                        tabLayout.getTabAt(1).setIcon(null);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditProfile:
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivityForResult(intent, 101);
                break;
        }

    }
}