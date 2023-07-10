package com.Soni5.vibehub.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Soni5.vibehub.Models.Model_Post;
import com.Soni5.vibehub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public void onBindViewHolder(@NonNull postholder holder, @SuppressLint("RecyclerView") int position)
    {

           Picasso.get().load(datalist.get(position).getLink()).into(holder.profileImageView);
           Picasso.get().load(datalist.get(position).getDP()).into(holder.postImageView);
           holder.usernameTextView.setText(datalist.get(position).getUsername());
           holder.usernamenextTextView.setText(datalist.get(position).getUsername());


        holder.likeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseAuth firebaseAuth;
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseFirestore firestore;
                firestore = FirebaseFirestore.getInstance();

                firestore.collection("Post").document(datalist.get(position).getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                    {
                        if (task.getResult().exists())
                        {

                            DocumentSnapshot document = task.getResult();
                            List<String > Likes = (List<String>) document.get("Likes");

                            ArrayList<String>  v = new ArrayList<>();
                            v.add(firebaseAuth.getUid());


                            HashMap<String,Object> j = new HashMap<>();

                            if (Likes == null)
                            {
                                j.put("Likes",v);
                                Log.d("TAGq", "onComplete:  No Likesse");

                                firestore.collection("Post").document(datalist.get(position).getId()).update(j).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused)
                                    {
                                        Log.d("TAGqe", "onComplete:  No Likesse");
                                    }
                                });
                            }
                            else
                            {
                                if (Likes.contains(firebaseAuth.getUid()))
                                {

                                }
                                else
                                {

                                    HashMap<String,Object> g = new HashMap<>();
                                    //       v.size();
                                    Likes.add(v.size(),firebaseAuth.getUid());
                                    g.put("Likes",Likes);

                                    firestore.collection("Post").document(datalist.get(position).getId()).update(g).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused)
                                        {
                                            Log.d("TAGqe", "onComplete:  Noi Likesse");
                                        }
                                    });
                                }

                            }

                            //  Log.d("TAG", String.valueOf(Follower.size()));
                           // following.setText(String.valueOf(Follower.size() + " Following"));


                            //  Toast.makeText(getActivity(), Follower.size(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                           // Log.d("TAGq", "onComplete:  No Likesse");
                           // Toast.makeText(getActivity(), "hellop zero", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class postholder extends RecyclerView.ViewHolder {

       ImageView profileImageView =itemView.findViewById(R.id.profileImageView);
       TextView usernameTextView = itemView.findViewById(R.id.usernameTextView);
        ImageView postImageView = itemView.findViewById(R.id.postImageView);
        ImageView likeImageView = itemView.findViewById(R.id.likeImageView);
//        ImageView commentImageView = itemView.findViewById(R.id.commentImageView);
//        ImageView shareImageView = itemView.findViewById(R.id.shareImageView);
//        ImageView saveImageView = itemView.findViewById(R.id.saveImageView);
//        TextView likeCountTextView = itemView.findViewById(R.id.likeCountTextView);
//        TextView captionTextView = itemView.findViewById(R.id.captionTextView);
//        TextView locationTextView = itemView.findViewById(R.id.locationTextView);
//        TextView timestampTextView = itemView.findViewById(R.id.timestampTextView)
        TextView usernamenextTextView = itemView.findViewById(R.id.usernamenext);



        public postholder(@NonNull View itemView) {
            super(itemView);





        }
    }
}
