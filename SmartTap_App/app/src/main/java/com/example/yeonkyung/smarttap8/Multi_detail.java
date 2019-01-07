package com.example.yeonkyung.smarttap8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Multi_detail extends AppCompatActivity {
    TextView multiNameTxt, multiNowTxt, multiMonthTxt, multiDetailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_detail);
        setTitle("");
        init();
    }

    void init(){
        multiNameTxt = (TextView)findViewById(R.id.multiNameTxt);
        multiNowTxt = (TextView)findViewById(R.id.multiNowTxt);
        multiMonthTxt = (TextView)findViewById(R.id.multiMonthTxt);
        multiDetailTxt = (TextView)findViewById(R.id.multiDetailTxt);

        Intent intent = getIntent();

        Multi_item d = intent.getParcelableExtra("Multi_item");
        multiNameTxt.setText(d.getName());
        multiDetailTxt.setText(d.getDetail());

    }

    public void onClick(View v){
        if(v.getId()==R.id.multiBackBtn){
            finish();
        }
    }
}

