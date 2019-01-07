package com.example.yeonkyung.smarttap8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nazzang on 2018-06-14.
 */

public class Reservation_adapter extends BaseAdapter{

    private ArrayList<Reservation_item> datalist;
    private ArrayList<Reservation_item> arrayList = new ArrayList<>();
    private Context context;

    public Reservation_adapter(ArrayList<Reservation_item> datalist,Context context){
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view= LayoutInflater.from(context).inflate(R.layout.reservation_item, null);
        }
        TextView tmN = (TextView) view.findViewById(R.id.timeName_item);
        TextView tm1 = (TextView) view.findViewById(R.id.time1_item);
        TextView tm2 = (TextView) view.findViewById(R.id.time2_item);

        final Button btnTonoff=(Button) view.findViewById(R.id.onoff_item);
        final int position = i;
        final int[] CHECK_NUM = {0};


        btnTonoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CHECK_NUM 이0 일경우 setSelected를 true로 줘서 초록스위치가 나오게 한다.
                if (CHECK_NUM[0] == 0){
                    btnTonoff.setSelected(true);
                    CHECK_NUM[0] = 1; // 다음에 누르면 색이 변하도록 값을 변경.
                }
                else{
                    btnTonoff.setSelected(false);

                    CHECK_NUM[0] = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });

        tmN.setText(datalist.get(i).getTimename());
        tm1.setText(datalist.get(i).getTime1());
        tm2.setText(datalist.get(i).getTime2());

        return view;
    }
}
