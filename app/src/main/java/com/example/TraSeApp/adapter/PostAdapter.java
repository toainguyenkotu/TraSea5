package com.example.TraSeApp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.TraSeApp.R;
import com.example.TraSeApp.model.Post;
import com.example.TraSeApp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public Context mContext;
    public List<Post> mPost;

    public FirebaseUser firebaseUser;

    public PostAdapter(Context mContext, List<Post> mPost) {
        this.mContext = mContext;
        this.mPost = mPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Post post = mPost.get(position);

        Glide.with(mContext.getApplicationContext())
                .load(post.getPostimage())
                .timeout(5000)
                .placeholder(R.drawable.ic_person_64x64)
                .into(holder.iv_post);

        if(post.getDescription().equals("")){
            holder.tv_description.setVisibility(View.GONE);
        }else {
            holder.tv_description.setVisibility(View.VISIBLE);
            holder.tv_description.setText(post.getDescription());
        }

        getPublisherInfo(holder.avt,holder.tv_username,post.getPublisher()); // Get avt, get username, get id publisher
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView avt;
        private TextView tv_username,tv_time,tv_like_counter,tv_cmt_counter,tv_description;
        private ImageView iv_post;
        private ImageButton img_btn_like,img_btn_comment,img_btn_share;

        public ViewHolder(@NonNull View itemView) {
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
            tv_description = itemView.findViewById(R.id.tv_description);
        }
    }

    private void getPublisherInfo(final CircleImageView image_profile, final TextView username, final String userid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(userid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Glide.with(mContext)
                        .load(user.getImgUrl())
                        .timeout(5000)
                        .placeholder(R.drawable.tokuda)
                        .into(image_profile);



                username.setText(user.getUsername());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    }

