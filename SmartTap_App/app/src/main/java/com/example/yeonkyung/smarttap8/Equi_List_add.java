package com.example.yeonkyung.smarttap8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Equi_List_add extends AppCompatActivity{
    private EditText addName_edt, detail_edt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equi_add_list);

        init();

    }

    void init(){
        addName_edt = (EditText)findViewById(R.id.addName_edt);
        detail_edt = (EditText)findViewById(R.id.detail_edt);

    }

    public  void onClick(View v){
        if(v.getId()==R.id.btnCancel){
            finish();
        }else if(v.getId()==R.id.btnAdd){
            Intent intent = new Intent();

            String name = addName_edt.getText().toString();
            String detail = detail_edt.getText().toString();

            data temp = new data(name, detail);

            intent.putExtra("data", temp);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
