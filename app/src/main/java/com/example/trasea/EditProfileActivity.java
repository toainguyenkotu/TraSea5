package com.example.trasea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.trasea.databinding.ActivityEditProfileBinding;
import com.example.trasea.fragments.FragmentInEditProfile;
import com.example.trasea.fragments.ProfileFrag;

import org.greenrobot.eventbus.EventBus;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityEditProfileBinding binding;
    FragmentInEditProfile fragment;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageView imageView = binding.getRoot().findViewById(R.id.iv_x);
        imageView.setOnClickListener(this);

        binding.txtName.setOnClickListener(this);
        binding.txtUserName.setOnClickListener(this);
        binding.txtBio.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_x:
                finish();
                break;
            case R.id.txtName:
                createNewFragment();
                EventBus.getDefault().postSticky(new MessageEvent(constant.NAME, MessageEvent.FROM_A_TO_B));
                break;
            case R.id.txtUserName:
                createNewFragment();
                EventBus.getDefault().postSticky(new MessageEvent(constant.USERNAME, MessageEvent.FROM_A_TO_B));
                break;
            case R.id.txtBio:
                createNewFragment();
                EventBus.getDefault().postSticky(new MessageEvent(constant.BIO, MessageEvent.FROM_A_TO_B));
                break;
        }
    }

    private  void createNewFragment(){
        if (fragment == null){
            fragment = new FragmentInEditProfile();
        }
        FragmentTransaction manager =getSupportFragmentManager().beginTransaction();
        manager.replace(R.id.container, fragment);
        manager.commit();
    }
}