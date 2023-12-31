package com.Soni5.vibehub.Fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Soni5.vibehub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }



    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;

    TextView name , bio , username,following,follower,postcount;
    ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        name = view.findViewById(R.id.textViewUsername);
        bio = view.findViewById(R.id.textViewBio);
        username = view.findViewById(R.id.Username);
        following = view.findViewById(R.id.textViewFollowingCount);
        follower = view.findViewById(R.id.textViewFollowersCount);
        imageView = view.findViewById(R.id.imageViewProfilePicture);
        postcount = view.findViewById(R.id.textViewPostsCount);
       // imageViewProfilePicture


        firestore.collection("User").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.getResult().exists())
                {
                    name.setText(task.getResult().getString("Name"));
                    bio.setText(task.getResult().getString("Bio"));
                    username.setText(task.getResult().getString("Username"));
                    Picasso.get().load(task.getResult().getString("DP")).into(imageView);
                    //Log.d("TAG", "onComplete: "+task.getResult().getString("DP"));
                }

            }
        });

        firestore.collection("Follow").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if (task.getResult().exists())
                {
                    DocumentSnapshot document = task.getResult();
                    List<String > Follower = (List<String>) document.get("Follower");
                  //  Log.d("TAG", String.valueOf(Follower.size()));


                        following.setText(String.valueOf("      "+Follower.size() + " \nFollowing"));





                  //  Toast.makeText(getActivity(), Follower.size(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    following.setText("0" + " Following");
                  //  Toast.makeText(getActivity(), "hellop zero", Toast.LENGTH_SHORT).show();
                }
            }
        });

        firestore.collection("Following").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if (task.getResult().exists())
                {

                    DocumentSnapshot document = task.getResult();
                    List<String > Follower = (List<String>) document.get("id");
                    //  Log.d("TAG", String.valueOf(Follower.size()));
                    follower.setText(String.valueOf("       "+Follower.size() +" \nFollower"));


                    //  Toast.makeText(getActivity(), Follower.size(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                 //   following.setText(String.valueOf(Follower.size() + " Following"));

                    follower.setText(String.valueOf("       0\n" +  " Follower"));
                }
            }
        });


        firestore.collection("Post").whereEqualTo("Uid",firebaseAuth.getUid()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();
                int a = 0;
                for (DocumentSnapshot t : list1)
                {
                    a++;

                }

                postcount.setText(String.valueOf("   "+a + "\n Post"));
            }
        });





        return  view;
    }
}