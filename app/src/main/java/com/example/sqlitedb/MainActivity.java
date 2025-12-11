package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText txtEmail, txtPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        txtEmail = (EditText) findViewById(R.id.email_txt);
        txtPassword = (EditText) findViewById(R.id.password_txt);

        btnRegister = (Button) findViewById(R.id.register_btn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = txtEmail.getText().toString();
                String Password = txtPassword.getText().toString();

                if (Email.equals("") || Password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkEmail = db.checkEmail(Email);
                    if (checkEmail == true){
                        Boolean insert = db.insert(Email, Password);

                        if (insert == true){
                            Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}