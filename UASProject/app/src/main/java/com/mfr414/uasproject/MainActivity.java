package com.mfr414.uasproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mfr414.uasproject.Activity.LoginActivity;
import com.mfr414.uasproject.Activity.NewJobActivity;
import com.mfr414.uasproject.Adapter.RecyclerAdapter;
import com.mfr414.uasproject.Model.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.onDeleteTaskListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabaseReference DatabaseReff;
    ArrayList<Task> ArrayTask;


    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() == null){
            finish();
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
        DatabaseReff = FirebaseDatabase.getInstance().getReference().child("task").child(fAuth.getCurrentUser().getUid());
        recyclerView = findViewById(R.id.rv_task);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayTask = new ArrayList<Task>();

        DatabaseReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot datafromfirebase : dataSnapshot.getChildren()){
                    Task t = datafromfirebase.getValue(Task.class);
                    ArrayTask.add(t);
                }
                adapter = new RecyclerAdapter(MainActivity.this,ArrayTask,MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Something Went Wrong,Please Try Again",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
            return true;
        }if(id == R.id.action_AddNewJob){
            addNewJob();
        }

        return super.onOptionsItemSelected(item);
    }

    private void addNewJob() {
        startActivity(new Intent(this, NewJobActivity.class));
    }

    private void logout() {
        fAuth.signOut();
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }

    @Override
    public void onDeleteTask(int id) {
        deleteTask(id);
    }

    private void deleteTask(int idTask){
        int id = idTask;

        DatabaseReff.child(String.valueOf(id)).removeValue();
        Toast.makeText(this,"Data Berhasil Dihapus",Toast.LENGTH_SHORT);
        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);
        finish();
    }
}
