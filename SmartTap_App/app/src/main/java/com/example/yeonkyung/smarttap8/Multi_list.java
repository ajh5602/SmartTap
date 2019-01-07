package com.example.yeonkyung.smarttap8;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Multi_list extends AppCompatActivity {

    ListView multiList;

    ArrayAdapter<String> adapter;

    int Addition = 1;

    ArrayList<Multi_item> storage = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_list);
        setTitle("멀티탭 목록");
        init();
    }

    void init(){
        multiList = (ListView)findViewById(R.id.multiList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title);

        multiList.setAdapter(adapter);

        multiList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Multi_list.this);
                final int position = i;
                dlg.setTitle("삭제")
                        .setMessage("삭제하시겠습니까 ?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                title.remove(position);
                                storage.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        });

        multiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Multi_list.this, Multi_detail.class);

                intent.putExtra("Multi_item", storage.get(i));

                startActivity(intent);


            }
        });

    }


    public void onClick(View v){
        if(v.getId() ==  R.id.addmultiBtn){
            Intent intent = new Intent(this, Multi_add.class);
            startActivityForResult(intent, Addition);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Multi_item) {
        super.onActivityResult(requestCode, resultCode, Multi_item);
        if(requestCode ==Addition){
            if(resultCode==RESULT_OK){
                Multi_item d = Multi_item.getParcelableExtra("Multi_item");
                title.add(d.getName());
                storage.add(d);
                adapter.notifyDataSetChanged();
            }
        }

    }
}
