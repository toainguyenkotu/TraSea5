package com.example.TraSeApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.TraSeApp.MessageEvent;
import com.example.TraSeApp.R;
import com.example.TraSeApp.databinding.FragmentInEditProfileBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FragmentInEditProfile extends Fragment {
    FragmentInEditProfileBinding binding;
    TextView tv_title;

    public FragmentInEditProfile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_in_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_title = binding.getRoot().findViewById(R.id.tv_title);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event){
        if (event!=null && MessageEvent.FROM_A_TO_B.equals(event.type)){
            if (event.message.equals("1")){
                tv_title.setText(R.string.name);
                binding.tvNote.setText(R.string.note_name+ + R.string.note_name_continue);
            }else if (event.message.equals("2")){
                tv_title.setText(R.string.username);
                binding.tvNote.setText(R.string.note_username);
            }else{
                tv_title.setText(R.string.bio);
                binding.tvNote.setText("");
            }
        }
        EventBus.getDefault().removeStickyEvent(event);
    }
}
