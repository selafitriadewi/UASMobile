package com.mfr414.uasproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mfr414.uasproject.Activity.EditJobActivity;
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
        final int idTask = i+1;
        viewHolder.taskTitle.setText(tasks.get(i).getJobTitle());
        viewHolder.taskDesc.setText(tasks.get(i).getJobDesc());
        viewHolder.taskStatus.setText(tasks.get(i).getStatus());
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(context, EditJobActivity.class);
                edit.putExtra("idTask",idTask);
                context.startActivity(edit);
            }
        });
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
            taskTitle = itemView.findViewById(R.id.TVTitle);
            taskDesc = itemView.findViewById(R.id.TVDesc);
            taskStatus = itemView.findViewById(R.id.TVStatus);
            btnEdit = itemView.findViewById(R.id.buttonEdit);
        }
    }
}
