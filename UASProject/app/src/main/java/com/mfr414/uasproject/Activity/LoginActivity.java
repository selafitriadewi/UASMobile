package com.mfr414.uasproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    }
}
