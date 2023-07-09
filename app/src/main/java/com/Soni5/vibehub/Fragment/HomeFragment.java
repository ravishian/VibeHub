package com.Soni5.vibehub.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.Soni5.vibehub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import io.grpc.Context;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }


    ImageButton post;
    Uri uri;
    StorageReference storageReference;
    ImageView imageView;
    RecyclerView recyclerView;
    FirebaseFirestore firebaseStore;
    FirebaseAuth firebaseAuth;




    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view =  inflater.inflate(R.layout.fragment_home, container, false);

        firebaseStore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        post = view.findViewById(R.id.imagepost);
        recyclerView = view.findViewById(R.id.postrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext() ));


        storageReference = FirebaseStorage.getInstance().getReference();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectimage();

            }
        });



        return  view;
    }


    private void selectimage()

    {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null)
        {

            uri = data.getData();
           Toast.makeText(getActivity(), "upload", Toast.LENGTH_SHORT).show();
           uplodimage();

            //imageView.setImageURI(uri);






        }

    }


    void uplodimage()
    {

      //  imageView.setImageURI(uri);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference().child("hello /"+ UUID.randomUUID().toString());
        uploader.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                //Toast.makeText(getActivity(), "uploadddddd", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onSuccess:  upload ");


                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)

                    {

                        Random random = new Random();
                        double randomNumber = random.nextDouble();
                        String randomString = String.valueOf(randomNumber);

                        FieldValue serverTimestamp = FieldValue.serverTimestamp();
                        HashMap<String,String> v = new HashMap<>();
                        v.put("Uid",firebaseAuth.getUid());
                        v.put("Link",task.getResult().toString());
                        v.put("Id",randomString);
                        v.put("Time",serverTimestamp.toString());





                        Log.d("TAGaaa", serverTimestamp.toString());
                        System.out.println(serverTimestamp);
                        firebaseStore.collection("Post").document(randomString).set(v).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                });
            }
        });

    }
}