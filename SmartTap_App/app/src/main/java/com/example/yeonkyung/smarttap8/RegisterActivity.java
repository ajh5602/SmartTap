package com.example.yeonkyung.smarttap8;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText reg_nameText = findViewById(R.id.reg_nameText);
        final EditText reg_idText = findViewById(R.id.reg_idText);
        final EditText reg_pwText = findViewById(R.id.reg_pwText);
        final EditText reg_pwTextOK = findViewById(R.id.reg_pwTextOK);

        final TextView checkpasswordtv = findViewById(R.id.checkpasswordtv);

        Button registerNoBtn = findViewById(R.id.registerNoBtn);
        Button registerOkBtn = findViewById(R.id.registerOkBtn);

        //비밀번호 일치 검사
        reg_pwTextOK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = reg_pwText.getText().toString();
                String confirm = reg_pwTextOK.getText().toString();

                if(password.equals(confirm)){
                    checkpasswordtv.setText("비밀번호가 일치합니다.");
                    checkpasswordtv.setTextColor(Color.BLACK);
                }else {
                    checkpasswordtv.setText("비밀번호가 틀립니다.");
                    checkpasswordtv.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //취소Btn
        registerNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //완료Btn
        registerOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이름 확인
                if(reg_nameText.getText().toString().length() ==0){
                    Toast.makeText(RegisterActivity.this, "이름을 입력하세요!",Toast.LENGTH_SHORT).show();
                    reg_nameText.requestFocus();
                    return;
                }

                //아이디 확인
                if(reg_idText.getText().toString().length() ==0){
                    Toast.makeText(RegisterActivity.this, "아이디를 입력하세요!",Toast.LENGTH_SHORT).show();
                    reg_idText.requestFocus();
                    return;
                }

                if(reg_pwText.getText().toString().length() ==0){
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력하세요!",Toast.LENGTH_SHORT).show();
                    reg_pwText.requestFocus();
                    return;
                }

                if(reg_pwTextOK.getText().toString().length() ==0){
                    Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다!",Toast.LENGTH_SHORT).show();
                    reg_pwText.setText("");
                    reg_pwTextOK.setText("");
                    reg_pwTextOK.requestFocus();
                    return;
                }


                finish();
            }
        });


    }
}