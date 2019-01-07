package com.example.yeonkyung.smarttap8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText idText = findViewById(R.id.idText);
        EditText pwText = findViewById(R.id.pwText);

        Button loginBtn = findViewById(R.id.loginBtn);
        Button registerBtn = findViewById(R.id.registerBtn);

        CheckBox loginCheck = findViewById(R.id.loginCheck);


        //로그인Btn
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Login.this,MainActivity.class);
                startActivity(loginIntent);

            }
        });
        //회원가입Btn
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this,RegisterActivity.class);
                startActivity(registerIntent);

            }
        });

    }
}