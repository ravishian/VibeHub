package com.Soni5.vibehub.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Soni5.vibehub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SearchProfileFragment extends Fragment {

 String id ;
    ImageView profilePicture;
    TextView username;
    TextView bio;
    TextView followerCount;
    TextView followingCount;
    TextView Name;
    RecyclerView postsGrid;
    Button followbtn;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;


    public SearchProfileFragment(String id) {
        // Required empty public constructor

        this.id = id;
    }





    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search_profile, container, false);

        profilePicture = view.findViewById(R.id.profile_picture);
        username = view.findViewById(R.id.username);
        bio = view.findViewById(R.id.bio);
        followerCount = view.findViewById(R.id.follower_count);
        followingCount = view.findViewById(R.id.following_count);
        postsGrid = view.findViewById(R.id.posts_grid);
        Name = view.findViewById(R.id.searchname);
        followbtn = view.findViewById(R.id.followbutton);
        firestore = FirebaseFirestore.getInstance();

        HashMap<String , Object> v = new HashMap<>();
        HashMap<String , Object> w = new HashMap<>();

        firebaseAuth = FirebaseAuth.getInstance();


        ArrayList<String> a = new ArrayList<>();
        a.add(id);

        v.put("Follower", a);
        v.put("id",firebaseAuth.getUid());


        ArrayList<String> b = new ArrayList<>();
        b.add(firebaseAuth.getUid());

        w.put("Following", id);
        w.put("id",b);




        firestore.collection("User").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
             if (task.getResult().exists())
             {
                 username.setText(task.getResult().getString("Username"));
                 Name.setText(task.getResult().getString("Name"));
                 bio.setText(task.getResult().getString("Bio"));
             }
            }
        });


        followbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "fdggdg", Toast.LENGTH_SHORT).show();







                firestore.collection("Follow").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                    {

                        if (task.getResult().exists())
                        {
                            Toast.makeText(getActivity(), "Hello worlf", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            firestore.collection("Follow").document(firebaseAuth.getUid()).set(v).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    Toast.makeText(getActivity(), "Ho gya", Toast.LENGTH_SHORT).show();

                                    firestore.collection("Following").document(id).set(w).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(getActivity(), "Ho gya", Toast.LENGTH_SHORT).show();
                                        }
                                    }) ;

                                }
                            }) ;

                            Toast.makeText(getActivity(), "wwe", Toast.LENGTH_SHORT).show();
                        }

                        DocumentSnapshot document = task.getResult();
                        List<String >  Follower = (List<String>) document.get("Follower");


                    }
                });

            }
        });



        return view;
    }
}