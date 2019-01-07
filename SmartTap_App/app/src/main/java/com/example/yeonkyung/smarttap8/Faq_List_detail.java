package com.example.yeonkyung.smarttap8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Faq_List_detail extends AppCompatActivity{
    EditText faq_result_title, faq__result_text;
    Button faq_canleBtnr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_detail_layout);

        init();
    }

    void init(){
        faq_result_title = (EditText)findViewById(R.id.faq_result_title);
        faq__result_text = (EditText)findViewById(R.id.faq__result_text);

        Intent intent = getIntent();

        faqdata d = intent.getParcelableExtra("faqdata");

        faq_result_title.setText(d.getFtitle());
        faq__result_text.setText(d.getFtext());
    }

    public void onClick(View v){
        if(v.getId()==R.id.faq_canleBtnr){
            finish();
        }
    }
}

