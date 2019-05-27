package com.mfr414.uasproject.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mfr414.uasproject.MainActivity;
import com.mfr414.uasproject.Model.Task;
import com.mfr414.uasproject.R;

public class NewJobActivity extends AppCompatActivity {

    private EditText jobTitle;
    private EditText jobDesc;
    private FirebaseAuth fAuth;
    private Button btnAddJob,btnCancel;
    private DatabaseReference fDatabase;
    long maxId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);


        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() == null){
            finish();
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
        fDatabase= FirebaseDatabase.getInstance().getReference().child("task").child(fAuth.getCurrentUser().getUid());

        jobTitle = findViewById(R.id.editJobTitle);
        jobDesc = findViewById(R.id.editJobDesc);
        btnAddJob = findViewById(R.id.buttonAddJob);
        btnCancel = findViewById(R.id.buttonCancel);

        fDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxId=dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveTask();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewJobActivity.this, MainActivity.class));
            }
        });
    }

    private void SaveTask() {
        String taskTittle = jobTitle.getText().toString().trim();
        String taskDesc = jobDesc.getText().toString().trim();
        String taskStatus = "undone";

        Task task = new Task(taskTittle,taskDesc,taskStatus);

        fDatabase.child(String.valueOf(maxId+1)).setValue(task);
        Toast.makeText(this,"tugas ditambahkan",Toast.LENGTH_SHORT).show();
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}
