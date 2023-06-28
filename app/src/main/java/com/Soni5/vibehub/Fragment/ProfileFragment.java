package com.Soni5.vibehub.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Soni5.vibehub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }



    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;

    TextView name , bio , username;

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

        firestore.collection("User").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.getResult().exists())
                {
                    name.setText(task.getResult().getString("Name"));
                    bio.setText(task.getResult().getString("Bio"));
                    username.setText(task.getResult().getString("Username"));
                }

            }
        });





        return  view;
    }
}