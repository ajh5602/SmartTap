package com.example.yeonkyung.smarttap8;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Reservation_detail extends AppCompatActivity {

    TextView edtTimename;
    TextView edtTime1;
    TextView edtTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_detail);

        setTitle("예약 확인");
        init();
    }


    private void init() {
        edtTimename = (TextView) findViewById(R.id.edtTimename);
        edtTime1 = (TextView) findViewById(R.id.edtTime1);
        edtTime2 = (TextView) findViewById(R.id.edtTime2);

        Intent intent = getIntent();

        Reservation_item r = intent.getParcelableExtra("reservation_item");

        edtTimename.setText(r.getTimename());
        edtTime1.setText(r.getTime1());
        edtTime2.setText(r.getTime2());
    }

    public void onClick(View v){
        if (v.getId()==R.id.BackBtn){
            finish();
        }
    }
}