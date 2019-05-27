package com.mfr414.uasproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mfr414.uasproject.Model.Task;
import com.mfr414.uasproject.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Task> tasks;
    public RecyclerAdapter(Context c,ArrayList<Task> t) {
        context =  c;
        tasks = t;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
     View v = LayoutInflater.from(context).inflate(R.layout.item_task, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.taskTitle.setText(tasks.get(i).getJobTitle());
        viewHolder.taskDesc.setText(tasks.get(i).getJobDesc());
        viewHolder.taskStatus.setText(tasks.get(i).getStatus());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitle,taskDesc,taskStatus;
        Button btnEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle=itemView.findViewById(R.id.TVTitle);
            taskDesc=itemView.findViewById(R.id.TVDesc);
            taskStatus=itemView.findViewById(R.id.TVStatus);
            btnEdit = itemView.findViewById(R.id.buttonEdit);
    }
}
