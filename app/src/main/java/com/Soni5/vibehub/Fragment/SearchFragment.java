package com.Soni5.vibehub.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Soni5.vibehub.Adapter.SearchAdapter;
import com.Soni5.vibehub.Models.Model_Search;
import com.Soni5.vibehub.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {



    public SearchFragment() {
        // Required empty public constructor
    }

    EditText editText;
    FrameLayout search , browse;
    RecyclerView recyclerView1 , recyclerView2;
    TextView textView;
    FirebaseFirestore firestore;
    ImageView imageView;

    ArrayList<Model_Search> datalist;
    SearchAdapter adapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        datalist = new ArrayList<>();

        editText = view.findViewById(R.id.searchedittext);
        search = view.findViewById(R.id.search);
        browse = view.findViewById(R.id.browse);
        recyclerView1 =  search.findViewById( R.id.searchrecyclerview);
        firestore = FirebaseFirestore.getInstance();
        imageView = view.findViewById(R.id.imageViewback);

        recyclerView1.setLayoutManager(new LinearLayoutManager(view.getContext() ));
        adapter= new SearchAdapter(datalist);
        recyclerView1.setAdapter(adapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browse.setVisibility(View.VISIBLE);
                search.setVisibility(View.GONE);
            }
        });


        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    browse.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                    recyclerView1.setVisibility(View.VISIBLE);


                    Toast.makeText(getActivity(), "EditText is active", Toast.LENGTH_SHORT).show();
                } else {
                    browse.setVisibility(View.VISIBLE);
                    search.setVisibility(View.GONE);
                    recyclerView1.setVisibility(View.GONE);

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
//                firestore.collection("User").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
//                    {
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
//                        {
//                            String data = documentSnapshot.getString("Username");
//                            if (data.contains(s.toString()))
//                            {
//                                Toast.makeText(getActivity(), "hega", Toast.LENGTH_SHORT).show();
//                              //  Model_Search obj = documentSnapshot.toObject(Model_Search.class);
//                               // datalist.add(obj);
//
//                            }
//
//                            else
//                            {
//                                //    Toast.makeText(getActivity(), "koj ni hega", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
               // adapter.notifyDataSetChanged();


            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has been changed
                String newText = s.toString();
                Toast.makeText(getActivity(), s.toString(), Toast.LENGTH_SHORT).show();
                // Perform actions based on the new text

                firestore.collection("User").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                    {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d:list)
                        {

                            if (d.getString("Name").contains(s.toString()))
                            {
                                Log.d("TAG",d.getString("Name") );
                                Model_Search obj = d.toObject(Model_Search.class);
                               datalist.add(obj);
                            }

                        }
                        adapter.notifyDataSetChanged();

                    }
                });



            }
        });






        return view;

    }


}