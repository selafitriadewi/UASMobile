package com.mfr414.uasproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mfr414.uasproject.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin= findViewById(R.id.buttonLogin);
        editTextEmail = findViewById(R.id.editEmailLogin);
        editTextPassword = findViewById(R.id.editPasswordLogin);
        textRegister = findViewById(R.id.textViewRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    private void loginUser() {

    }
}
