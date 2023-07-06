package com.Soni5.vibehub.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Soni5.vibehub.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class SearchFragment extends Fragment {



    public SearchFragment() {
        // Required empty public constructor
    }

    EditText editText;
    FrameLayout search , browse;
    RecyclerView recyclerView1 , recyclerView2;
    TextView textView;
    FirebaseFirestore firestore;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        editText = view.findViewById(R.id.searchedittext);
        search = view.findViewById(R.id.search);
        browse = view.findViewById(R.id.browse);
        recyclerView1 =  search.findViewById( R.id.searchrecyclerview);
        textView = search.findViewById( R.id.textView3);
        firestore = FirebaseFirestore.getInstance();
        textView.setText("hello recycler View");


        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    browse.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);

                    //Toast.makeText(getActivity(), "EditText is active", Toast.LENGTH_SHORT).show();
                } else {
                    browse.setVisibility(View.VISIBLE);
                    search.setVisibility(View.GONE);

                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when the text is being changed


                String newText = s.toString();
                // Perform actions based on the new text
               firestore.collection("User").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                   @Override
                   public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                   {
                       for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                       {
                           String data = documentSnapshot.getString("Username");
                           if (data.contains(s.toString()))
                           {
                               Toast.makeText(getActivity(), "hega", Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               Toast.makeText(getActivity(), "koj ni hega", Toast.LENGTH_SHORT).show();
                           }
                       }
                   }
               });
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed
                String newText = s.toString();
                // Perform actions based on the new text
//                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//                firestore.collection("User").whereArrayContains("Username",s.toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task)
//                    {
//
//                        if(task.getResult().isEmpty())
//                        {
//                            textView.setText("Empty va smaan");
//                        } else if (!task.getResult().isEmpty())
//                        {
//                            textView.setText(task.getResult().toString());
//                        }
//                    }
//                });




            }
        });






        return view;

    }


}