package com.mfr414.uasproject.Activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mfr414.uasproject.MainActivity;
import com.mfr414.uasproject.R;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textLogin;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RelativeLayout layout = findViewById(R.id.layout);
        AnimationDrawable animation = (AnimationDrawable) layout.getBackground();
        animation.setEnterFadeDuration(2000);
        animation.setExitFadeDuration(4000);
        animation.start();

        fAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.buttonRegister);
        editTextEmail = findViewById(R.id.editEmail);
        editTextPassword = findViewById(R.id.editPassword);
        textLogin = findViewById(R.id.textViewLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(login);
            }
        });

    }

    public void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String pass = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(this,"Please insert your email",Toast.LENGTH_SHORT).show();
            return;
        }if(pass.isEmpty()){
            Toast.makeText(this,"Please insert your password",Toast.LENGTH_SHORT).show();
            return;
        }

        fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    fAuth.signOut();
                    Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(login);
                }else{
                    Toast.makeText(RegisterActivity.this,"Error While Processing Data, Please Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
