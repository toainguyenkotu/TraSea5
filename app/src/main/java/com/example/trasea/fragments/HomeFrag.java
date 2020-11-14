package com.example.trasea.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trasea.R;
import com.example.trasea.adapter.HomeAdapter;
import com.example.trasea.model.Post;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeFrag extends Fragment {
    private RecyclerView rv_posts;
    private List<Post> list;
    HomeAdapter homeAdapter;


    public HomeFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        list = new ArrayList<>();
        create_test_data();

        homeAdapter = new HomeAdapter(list,getContext());
        rv_posts.setAdapter(homeAdapter);
    }

    private void create_test_data() {
        for(int i = 0 ; i < 10 ; i++){
            Post post = new Post("User "+i , i+" minutes","ID "+i,R.drawable.img_post_test,R.drawable.tokuda,i,i);
            list.add(post);
        }
    }

    private void initView(View view) {
        rv_posts = view.findViewById(R.id.rv_posts);
        rv_posts.setHasFixedSize(true);
        rv_posts.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}