package com.example.yeonkyung.smarttap8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Faq_List_add extends AppCompatActivity{
    private EditText faq_title, faq_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_add_layout);

        init();
    }

    void init(){
        faq_title = (EditText)findViewById(R.id.faq_title);
        faq_text = (EditText)findViewById(R.id.faq_text);
    }

    public void onClick(View v){
        if(v.getId() == R.id.faq_canleBtn){
            finish();
        }else if(v.getId() == R.id.faq_okBtn){
            Intent intent = new Intent();

            String title =faq_title.getText().toString();
            String text = faq_text.getText().toString();


            faqdata data = new faqdata(title, text);

            intent.putExtra("faqdata", data);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}