package com.Soni5.vibehub.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.Soni5.vibehub.Fragment.SearchProfileFragment;
import com.Soni5.vibehub.Models.Model_Search;
import com.Soni5.vibehub.R;

import java.util.ArrayList;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.searchholder>

{

    public SearchAdapter(ArrayList<Model_Search> datalist) {
        this.datalist = datalist;
    }

    ArrayList<Model_Search> datalist;





    @NonNull
    @Override
    public searchholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row,parent,false);
        return new searchholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchholder holder, int position) {


        holder.t1.setText(datalist.get(position).getName());
        holder.t2.setText(datalist.get(position).getUsername());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                appCompatActivity.
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new SearchProfileFragment(datalist.get(position).getId()))
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class searchholder extends RecyclerView.ViewHolder {

        TextView t1,t2;
        ConstraintLayout constraintLayout;
    public searchholder(@NonNull View itemView) {
        super(itemView);

        t1 = itemView.findViewById(R.id.g_name);
        t2 = itemView.findViewById(R.id.g_student);
        constraintLayout = itemView.findViewById(R.id.consbtn);

    }
}
}
