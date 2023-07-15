package com.Soni5.vibehub.Fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Soni5.vibehub.Adapter.CommentAdapter;
import com.Soni5.vibehub.Models.Model_Comment;
import com.Soni5.vibehub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CommentSheetFragment extends BottomSheetDialogFragment {


    String id;

    public CommentSheetFragment(String id) {
        // Required empty public constructor
        this.id = id;
    }


    RecyclerView recyclerView;
    Button commentbtn;
    EditText editText;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    ArrayList<Model_Comment> datalist;
    CommentAdapter adapter;
    FirebaseFirestore db;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_comment_sheet, container, false);


        recyclerView = view.findViewById(R.id.cmtr);
        commentbtn = view.findViewById(R.id.cmtbtn);
        editText = view.findViewById(R.id.editcomment);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext() ));
        datalist = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        adapter= new CommentAdapter(datalist);
        recyclerView.setAdapter(adapter);

        int peekHeight = getResources().getDisplayMetrics().heightPixels / 2;
        getDialog().getWindow().setDimAmount(0.5f); // Optional: Adds dimming effect to the background
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, peekHeight);




       // datalist.add();
        db.collection("Comment").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if (task.getResult().exists())
                {
                    DocumentSnapshot document = task.getResult();
                    List<String > Comment = (List<String>) document.get("Comment");
                    List<String > ids = (List<String>) document.get("id");

                    int i ;
                    for (i =0 ; i <ids.size();i++)
                    {
                        int finalI = i;
                        firestore.collection("User").document(ids.get(i)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> tasks)
                            {
                                if (tasks.getResult().exists())
                                {
                                    datalist.add(new Model_Comment(tasks.getResult().getString("Username"), Comment.get(finalI) ));

                                    Log.d("TAG", "onComplete: " + tasks.getResult().getString("Username")+ Comment.get(finalI) );


                                }adapter.notifyDataSetChanged();

                            }
                        });
                    }




                }

            }
        });








        commentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                ArrayList a = new ArrayList();
                a.add(editText.getText().toString());

                ArrayList b = new ArrayList();
                b.add(firebaseAuth.getUid());

                HashMap<String,Object>  v = new HashMap<>();
                v.put("Comment",a);
                v.put("id",b);
                v.put("post",id);

                HashMap<String,Object>  w = new HashMap<>();








                firestore.collection("Comment").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task)

                    {
                        if (task.getResult().exists())
                        {
                            DocumentSnapshot document = task.getResult();
                            List<String > Comment = (List<String>) document.get("Comment");
                            List<String > ids = (List<String>) document.get("id");

                            Comment.add(Comment.size(),editText.getText().toString());
                            ids.add(ids.size(), firebaseAuth.getUid());

                            w.put("Comment",Comment);
                            w.put("id",ids);




                    firestore.collection("Comment").document(id).update(w).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        Toast.makeText(getActivity(), "Commented", Toast.LENGTH_SHORT).show();

                    }
                });


                        }
                        else
                        {
                            firestore.collection("Comment").document(id).set(v).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {

                                    Toast.makeText(getActivity(), "Commented", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getActivity(), "Hello world1", Toast.LENGTH_SHORT).show();
                        firestore.collection("Comment").document(id).set(v).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                Toast.makeText(getActivity(), "Commented", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });



            }
        });




        return  view;
    }


}