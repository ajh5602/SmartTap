package com.example.yeonkyung.smarttap8;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Equi_List_detail extends AppCompatActivity{
    TextView textname, nowR_text, monthR_textv, rDetail_textv;
    Button btnback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equi_detail_list);

        init();
    }

    void init(){
        textname = (TextView)findViewById(R.id.textname);
        nowR_text = (TextView)findViewById(R.id.nowR_text);
        monthR_textv = (TextView)findViewById(R.id.monthR_textv);
        rDetail_textv = (TextView)findViewById(R.id.rDetail_textv);

        Intent intent = getIntent();

        data d = intent.getParcelableExtra("data");
        textname.setText(d.getName());
        rDetail_textv.setText(d.getDetail());

    }

    public void onClick(View v){
        if(v.getId()==R.id.btnback){
            finish();
        }
    }
}

