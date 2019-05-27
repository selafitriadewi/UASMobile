package com.mfr414.uasproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mfr414.uasproject.R;

public class EditJobActivity extends AppCompatActivity {

    private EditText jobTitle;
    private EditText jobDesc;
    private FirebaseAuth fAuth;
    private Button btnEditJob,btnCancel;
    private DatabaseReference fDatabase;
    private String idTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);

        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() == null){
            finish();
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
        fDatabase= FirebaseDatabase.getInstance().getReference().child("task").child(fAuth.getCurrentUser().getUid());
        idTask = getIntent().getStringExtra("idTask") ;

        jobTitle = findViewById(R.id.editJobTitle);
        jobDesc = findViewById(R.id.editJobDesc);
        btnEditJob = findViewById(R.id.buttonEditJob);
        btnCancel = findViewById(R.id.buttonCancel);

        btnEditJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
