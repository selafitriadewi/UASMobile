package com.mfr414.uasproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mfr414.uasproject.R;

public class NewJobActivity extends AppCompatActivity {

    private EditText jobTitle;
    private EditText jobDesc;
    private FirebaseAuth fAuth;
    private Button btnAddJob;
    private StorageReference fDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        fDatabase=FirebaseStorage.getInstance().getReference();
        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() == null){
            finish();
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }

        jobTitle = findViewById(R.id.editJobTitle);
        jobDesc = findViewById(R.id.editJobDesc);
        btnAddJob = findViewById(R.id.buttonAddJob);

        btnAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
