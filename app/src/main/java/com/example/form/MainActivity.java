package com.example.form;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Username,Password,Password2,Name,Address,Status;
    Button Register, Login;
    RadioGroup Gender;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        Username = (EditText)findViewById(R.id.Username);
        Password =(EditText)findViewById(R.id.Password);
        Password2 =(EditText)findViewById(R.id.Password2);
        Name = (EditText)findViewById(R.id.Name);
        Address =(EditText)findViewById(R.id.Address);
        Status = (EditText)findViewById(R.id.Status) ;
        Gender =(RadioGroup)findViewById(R.id.Gender);
        Register =(Button)findViewById(R.id.Register);
        Login=(Button)findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UsernameValue = Username.getText().toString();
                String PasswordValue = Password.getText().toString();
                String Password2Value = Password2.getText().toString();
                String NameValue = Name.getText().toString();
                String AddressValue = Address.getText().toString();
                String StatusValue = Status.getText().toString();
                //Radio Button inserting
                RadioButton sGender = findViewById(Gender.getCheckedRadioButtonId());
                String GenderValue = sGender.getText().toString();
                //fill up fields
                if (UsernameValue.equals("")||PasswordValue.equals("")||Password2Value.equals("")||NameValue.equals("")||AddressValue.equals("")||GenderValue.equals("")||StatusValue.equals("")){
                    Toast.makeText(getApplicationContext(),"Field are empty",Toast.LENGTH_SHORT).show();
                }
                //inserting fields
                else{
                    //inserting password and confirm password
                    if (PasswordValue.equals(Password2Value)){
                        //confirming Username
                        Boolean chkUsername = db.chkUsername(UsernameValue);
                        //inserting user
                        if (chkUsername == true){
                            Boolean insert = db.insert(UsernameValue,PasswordValue,NameValue,AddressValue,GenderValue,StatusValue);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_SHORT).show();

                            }
                        }

                        else {
                            Toast.makeText(getApplicationContext(),"Username Already Exists",Toast.LENGTH_SHORT).show();
                        }

                    }


                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}