package com.example.TraSeApp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.TraSeApp.R;
import com.example.TraSeApp.databinding.StorageItemBinding;
import com.example.TraSeApp.fragments.ProfileFrag;
import com.example.TraSeApp.model.Post;

import java.util.List;

public class ProfileStorageAdapter extends RecyclerView.Adapter<ProfileStorageAdapter.ViewHolder> {
    List<Post> posts;
    private Context context;

    public ProfileStorageAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StorageItemBinding binding = StorageItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        StorageItemBinding binding;
        ImageView iv_post;
        public ViewHolder(@NonNull StorageItemBinding binding) {
            super(binding.getRoot());

            iv_post = itemView.findViewById(R.id.iv_post);

        }

        public void bind(final Post post) {
            Glide.with(context).load(post.getPostimage()).into(binding.ivStorageImage);

            iv_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = context.getSharedPreferences("caches", Context.MODE_PRIVATE).edit();
                    editor.putString("profileid", post.getPublisher());
                    editor.apply();

                    ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFrag()).commit();
                }
            });

        }
    }
}
