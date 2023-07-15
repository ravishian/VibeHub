package com.Soni5.vibehub.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Soni5.vibehub.Models.Model_Comment;
import com.Soni5.vibehub.R;

import java.util.ArrayList;

public class CommentAdapter  extends RecyclerView.Adapter<CommentAdapter.commentholder>
{

    ArrayList<Model_Comment> datalist;

    public CommentAdapter(ArrayList<Model_Comment> datalist)

    {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public commentholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_row,parent,false);
        return new commentholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull commentholder holder, int position)
    {
        holder.t1.setText(datalist.get(position).getUsername());
        holder.t2.setText(datalist.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class  commentholder extends RecyclerView.ViewHolder {

        TextView t1,t2;
        public commentholder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.g_name);
            t2 = itemView.findViewById(R.id.g_student);
        }
    }
}

