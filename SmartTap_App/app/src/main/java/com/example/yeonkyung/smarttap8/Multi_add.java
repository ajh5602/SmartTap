package com.example.yeonkyung.smarttap8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Multi_add extends AppCompatActivity {
    private EditText multiAddEdt, multiDetailEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_add);
        setTitle("멀티탭 추가");
        init();

    }

    void init(){
        multiAddEdt = (EditText)findViewById(R.id.multiAddEdt);
        multiDetailEdt = (EditText)findViewById(R.id.multiDetailEdt);

    }

    public  void onClick(View v){
        if(v.getId()==R.id.multiCancleBtn){
            finish();
        }else if(v.getId()==R.id.multiOkBtn){
            Intent intent = new Intent();

            String name = multiAddEdt.getText().toString();
            String detail = multiDetailEdt.getText().toString();

            Multi_item temp = new Multi_item(name, detail);

            intent.putExtra("Multi_item", temp);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
