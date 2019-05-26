package com.mfr414.uasproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.mfr414.uasproject.Activity.LoginActivity;
import com.mfr414.uasproject.Activity.NewJobActivity;

public class MainActivity extends AppCompatActivity {

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
}
