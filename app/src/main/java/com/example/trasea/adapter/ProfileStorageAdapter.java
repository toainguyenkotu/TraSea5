package com.example.trasea.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trasea.databinding.StorageItemBinding;
import com.example.trasea.model.Post;

import java.util.List;

public class ProfileStorageAdapter extends RecyclerView.Adapter<ProfileStorageAdapter.ViewHolder> {
    List<Post> posts;

    public ProfileStorageAdapter(List<Post> posts) {
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
        public ViewHolder(@NonNull StorageItemBinding binding) {
            super(binding.getRoot());

        }

        public void bind(Post post) {
            binding.ivStorageImage.setImageResource(post.getImgPost());
        }
    }
}
