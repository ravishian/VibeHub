package com.Soni5.vibehub.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Soni5.vibehub.Models.Model_Post;
import com.Soni5.vibehub.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter  extends RecyclerView.Adapter<PostAdapter.postholder>{

    public PostAdapter(ArrayList<Model_Post> datalist) {
        this.datalist = datalist;
    }

    ArrayList<Model_Post> datalist;


    @NonNull
    @Override
    public postholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row,parent,false);
        return new postholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postholder holder, int position)
    {


     //   Picasso.get().load(datalist.get(position).getLink()).into(holder.grou);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class postholder extends RecyclerView.ViewHolder {

//        ImageView profileImageView =itemView.findViewById(R.id.profileImageView);
//        //TextView usernameTextView = itemView.findViewById(R.id.usernameTextView);
//        ImageView postImageView = itemView.findViewById(R.id.postImageView);
//        ImageView likeImageView = itemView.findViewById(R.id.likeImageView);
//        ImageView commentImageView = itemView.findViewById(R.id.commentImageView);
//        ImageView shareImageView = itemView.findViewById(R.id.shareImageView);
//        ImageView saveImageView = itemView.findViewById(R.id.saveImageView);
//        TextView likeCountTextView = itemView.findViewById(R.id.likeCountTextView);
//        TextView captionTextView = itemView.findViewById(R.id.captionTextView);
//        TextView locationTextView = itemView.findViewById(R.id.locationTextView);
//        TextView timestampTextView = itemView.findViewById(R.id.timestampTextView);

        public postholder(@NonNull View itemView) {
            super(itemView);



        }
    }
}
