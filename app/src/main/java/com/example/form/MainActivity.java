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
    EditText eUsername,ePassword,ePassword2,eName,eAddress,eStatus;
    Button bRegister, bLogin;
    RadioGroup rGender;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        eUsername = (EditText)findViewById(R.id.Username);
        ePassword =(EditText)findViewById(R.id.Password);
        ePassword2 =(EditText)findViewById(R.id.Password2);
        eName = (EditText)findViewById(R.id.Name);
        eAddress =(EditText)findViewById(R.id.Address);
        eStatus = (EditText)findViewById(R.id.Status) ;
        rGender =(RadioGroup)findViewById(R.id.Gender);
        bRegister =(Button)findViewById(R.id.Register);
        bLogin=(Button)findViewById(R.id.Login);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUsername = eUsername.getText().toString();
                String sPassword = ePassword.getText().toString();
                String sPassword2 = ePassword2.getText().toString();
                String sName = eName.getText().toString();
                String sAddress = eAddress.getText().toString();
                String sStatus = eStatus.getText().toString();
                //Radio Button inserting
                RadioButton sGender = findViewById(rGender.getCheckedRadioButtonId());
                String GenderValue = sGender.getText().toString();
                //fill up fields
                if (sUsername.equals("")||sPassword.equals("")||sPassword2.equals("")||sName.equals("")||sAddress.equals("")||GenderValue.equals("")||sStatus.equals("")){
                    Toast.makeText(getApplicationContext(),"Field are empty",Toast.LENGTH_SHORT).show();
                }
                //inserting fields
                else{
                    //inserting password and confirm password
                    if (sPassword.equals(sPassword2)){
                        //confirming Username
                        Boolean chkUsername = db.chkUsername(sUsername);
                        //inserting user
                        if (chkUsername == true){
                            Boolean insert = db.insert(sUsername,sPassword,sName,sAddress,GenderValue,sStatus);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_SHORT).show();
                                eUsername.setText("");
                                ePassword.setText("");
                                ePassword2.setText("");
                                eName.setText("");
                                eAddress.setText("");
                                eStatus.setText("");
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