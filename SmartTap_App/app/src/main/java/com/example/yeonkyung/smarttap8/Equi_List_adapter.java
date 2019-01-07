package com.example.yeonkyung.smarttap8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nazzang on 2018-06-20.
 */

public class Equi_List_adapter extends BaseAdapter {

    private ArrayList<Equi_List_item> datalist;
    private ArrayList<Equi_List_item> arrayList = new ArrayList<>();
    private Context context;

    public Equi_List_adapter(ArrayList<Equi_List_item>datalist, Context context){
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
            view= LayoutInflater.from(context).inflate(R.layout.equi_item, null);
        }
        TextView name_eqItme = (TextView)view.findViewById(R.id.name_eqItme);
        TextView current_eqItme=(TextView)view.findViewById(R.id.current_eqItme);
        TextView currentM_eqItem = (TextView)view.findViewById(R.id.currentM_eqItem);


       TextView add_monthR_textv = (TextView)view.findViewById(R.id.add_monthR_textv);
        TextView add_nowR_text =(TextView)view.findViewById(R.id.add_nowR_text);

        final int position = i;

        name_eqItme.setText(datalist.get(i).getEquiname());
        current_eqItme.setText(datalist.get(i).getEquicurrent());
        currentM_eqItem.setText(datalist.get(i).getEquicurrentM());
//        add_monthR_textv.setText(datalist.get(i).getEquimonth());
//        add_nowR_text.setText(datalist.get(i).getEquicurrent());

        return view;
    }
}
