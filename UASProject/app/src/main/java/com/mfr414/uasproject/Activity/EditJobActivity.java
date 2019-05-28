package com.mfr414.uasproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mfr414.uasproject.MainActivity;
import com.mfr414.uasproject.Model.Task;
import com.mfr414.uasproject.R;

public class EditJobActivity extends AppCompatActivity {

    private EditText jobStatus;
    private FirebaseAuth fAuth;
    private Button btnEditJob,btnCancel;
    private DatabaseReference fDatabase;
    private int idTask;

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
        idTask = getIntent().getIntExtra("idTask",0) ;
        jobStatus = findViewById(R.id.editJobStatus);
        btnEditJob = findViewById(R.id.buttonEditJob);
        btnCancel = findViewById(R.id.buttonCancel);

        btnEditJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(idTask);
                Log.d("idTaskValue",String.valueOf(idTask));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditJobActivity.this, MainActivity.class));
            }
        });
    }

    private void updateData(int idTask) {
        String taskStatus = jobStatus.getText().toString().trim();

        fDatabase.child(String.valueOf(idTask)).child("status").setValue(taskStatus);

        Toast.makeText(this,"Status Tugas Dirubah",Toast.LENGTH_SHORT).show();
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}
