package com.example.yeonkyung.smarttap8;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Reservation_list extends AppCompatActivity {

    ListView timelist;

    Reservation_adapter adapter;

    int Addition = 1;
    ArrayList<Reservation_item> storage = new ArrayList<>();
    Button addAlarmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_list);
        setTitle("예약");

        init();
    }

    private void init() {
        timelist = (ListView)findViewById(R.id.timeList);
        adapter = new Reservation_adapter(storage, this);

        storage.add(new Reservation_item("나윤나윤","3:30","5:20"));
        storage.add(new Reservation_item("컴퓨터","4:30","4:50"));
        storage.add(new Reservation_item("선풍기","5:30","7:20"));

        timelist.setAdapter(adapter);

        timelist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Reservation_list.this);
                final int position =i;
                dlg.setTitle("삭제")
                        .setMessage("삭제하시겠습니까?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                storage.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        });

        timelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(Reservation_list.this, Reservation_detail.class);

                intent.putExtra("reservation_item", storage.get(i));
                startActivity(intent);
            }
        });

    }

    public void onClick(View view){
        Intent intent = new Intent(this, Reservation_add.class);
        startActivityForResult(intent, Addition);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == Addition){
            if (resultCode ==RESULT_OK){
                Reservation_item r = data.getParcelableExtra("reservation_item");
                storage.add(r);
            }
        }
    }
}

