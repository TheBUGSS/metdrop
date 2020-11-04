package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText eUsername, ePassword;
    Button eLogin;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        eUsername = (EditText)findViewById(R.id.Username);
        ePassword = (EditText)findViewById(R.id.Password);
        eLogin = (Button) findViewById(R.id.Login);
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUsername = eUsername.getText().toString();
                String sPassword = ePassword.getText().toString();
                //Validation of Username and Password
                Boolean chkUsernamePassword = db.UsernamePassword(sUsername, sPassword);
                if(chkUsernamePassword==true) {
                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (Login.this, Home.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Wrong Username or Password",Toast.LENGTH_SHORT).show();

            }
        });

    }
}