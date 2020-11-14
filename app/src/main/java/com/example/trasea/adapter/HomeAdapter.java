package com.example.trasea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trasea.R;
import com.example.trasea.model.Post;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private List<Post> list;
    Context context;

    public HomeAdapter(List<Post> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_items,parent,false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeHolder extends RecyclerView.ViewHolder {

        private CircleImageView avt;
        private TextView tv_username,tv_time,tv_like_counter,tv_cmt_counter;
        private ImageView iv_post;
        private ImageButton img_btn_like,img_btn_comment,img_btn_share;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);

            avt = itemView.findViewById(R.id.avt);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_like_counter = itemView.findViewById(R.id.tv_like_counter);
            iv_post = itemView.findViewById(R.id.iv_post);
            img_btn_like = itemView.findViewById(R.id.img_btn_like);
            img_btn_comment = itemView.findViewById(R.id.img_btn_comment);
            img_btn_share = itemView.findViewById(R.id.img_btn_share);
            tv_cmt_counter = itemView.findViewById(R.id.tv_cmt_counter);
        }

        public void bind(int position) {
            tv_username.setText(list.get(position).getUsername());
            tv_time.setText(list.get(position).getTime());

            // Load Avatar With Glide Library
            Glide.with(context.getApplicationContext())
                    .load(list.get(position).getAvt())
                    .placeholder(R.drawable.tokuda)
                    .timeout(5000)
                    .into(avt);

            // Load Image-Post With Glide Library
            Glide.with(context.getApplicationContext())
                    .load(list.get(position).getImgPost())
                    .placeholder(R.drawable.img_post_test)
                    .timeout(7000)
                    .into(iv_post);

            // Handle Likes Counter
            int likeCount = list.get(position).getLikeCounter();
            if(likeCount == 0) {
                tv_like_counter.setVisibility(View.INVISIBLE);
            }else if (likeCount == 1) {
                tv_like_counter.setText(likeCount +" like");
            }else {
                tv_like_counter.setText(likeCount +" likes");
            }

            // Handle Comments Counter
            int cmtCount = list.get(position).getCmtCounter();
            if(cmtCount == 0) {
                tv_cmt_counter.setVisibility(View.INVISIBLE);
            }else if (cmtCount == 1) {
                tv_cmt_counter.setText(cmtCount +" comment");
            }else {
                tv_cmt_counter.setText(cmtCount +" comments");
            }

        }
    }
}
