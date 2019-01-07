package com.example.yeonkyung.smarttap8;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;

public class Reservation_add extends AppCompatActivity {

    EditText edtTimename;
    TextView edtTime1;
    TextView edtTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_add);
        setTitle("추가");

        init();
    }

    void init() {
        edtTimename = (EditText) findViewById(R.id.edtTimename);
        edtTime1 = (TextView) findViewById(R.id.edtTime1);
        edtTime2 = (TextView) findViewById(R.id.edtTime2);


        edtTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Reservation_add.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtTime1.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false); //No 24 hour time
                mTimePicker.setTitle("시작시간");
                mTimePicker.show();
            }
        });

        

        edtTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Reservation_add.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtTime2.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false); //No 24 hour time
                mTimePicker.setTitle("종료시간");
                mTimePicker.show();
            }
        });


    }

    public void onClick(View view) {
        if (view.getId() == R.id.cancleBtn) {
            finish();
        } else if (view.getId() == R.id.okBtn) {
            Intent intent = new Intent();

            String timename = edtTimename.getText().toString();
            String time1 = edtTime1.getText().toString();
            String time2 = edtTime2.getText().toString();

            if (edtTime1.getText().toString().replace(" ", "").equals("")) {
                Toast.makeText(getApplicationContext(), "시작 시간이 비어 있습니다. 시간을 선택해주세요.", Toast.LENGTH_SHORT).show();
            } else if (edtTime2.getText().toString().replace(" ", "").equals("")) {
                Toast.makeText(getApplicationContext(), "종료 시간이 비어 있습니다. 시간을 선택해주세요.", Toast.LENGTH_SHORT).show();
            } else if (edtTime2.getText().toString().equals(edtTime1)) {
                Toast.makeText(getApplicationContext(), "시작 시간과 종료 시간이 같습니다. 다르게 설정해주세요.", Toast.LENGTH_SHORT).show();
            } else if (edtTimename.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "예약 명이 비어 있습니다.", Toast.LENGTH_SHORT).show();
            } else {
                //insertTodatabase(timename, time1, time2);
                //finish();
                Reservation_item temp = new Reservation_item(timename, time1, time2);
                //intent.putExtra("reservation_item", temp);
                setResult(RESULT_OK, intent);
                finish();
            }

            /*Reservation_item temp = new Reservation_item(timename, time1, time2);
            intent.putExtra("reservation_item", temp);
            setResult(RESULT_OK, intent);
            finish();*/
        }
    }

    private void insertTodatabase(String timename, String time1, String time2) {
        class insertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Reservation_add.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String timename = (String) params[0];
                    String time1 = (String) params[1];
                    String time2 = (String) params[2];

                    String link = "http://13.124.107.126/reservation_add.php";
                    String data = URLEncoder.encode("timename", "UTF-8") + "=" + URLEncoder.encode(timename, "UTF-8");
                    data += "&" + URLEncoder.encode("time1", "UTF-8") + "=" + URLEncoder.encode(time1, "UTF-8");
                    data += "&" + URLEncoder.encode("time2", "UTF-8") + "=" + URLEncoder.encode(time2, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer sb = new StringBuffer();
                    String line = null;

                    //read server response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception : " + e.getMessage());
                }
            }
        }
        insertData task = new insertData();
        task.execute(timename, time1, time2);
    }
}
