package com.example.yeonkyung.smarttap8;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.*;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.*;


/**
 * Created by Nazzang on 2018-11-01.
 */

public class Equi_Month extends AppCompatActivity {

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equi_month);
        TextView textView = (TextView)findViewById(R.id.name);
        Intent intent = getIntent();
       String receive = intent.getStringExtra("String");

       // ArrayList arrayList = intent.getExtras().getCharSequenceArrayList("val");
       //ArrayList <Equi_List_item>arrayList = intent.getParcelableArrayListExtra("val");
      String name = getIntent().getStringExtra("name");
      String price = getIntent().getStringExtra("price");
      textView.setText(name);
     Log.d("기기명 = ", name);
     Log.d("요금", price);
        lineChart = (LineChart) findViewById(R.id.chart);

        //리스트에 Entry 타입을 선언, Entry타입의 list는 그래프의 x축, y축 값 설정가능
        //add(new Entry(x축 한칸당, y값))
        List<Entry> entries = new ArrayList<>();

        entries.add(new Entry(1, 543));
        entries.add(new Entry(2, 676));
        entries.add(new Entry(3, 1022));
        entries.add(new Entry(4, 893));
        entries.add(new Entry(5, 456));
        entries.add(new Entry(6, 776));
        entries.add(new Entry(7, 988));
        entries.add(new Entry(8, 544));
        entries.add(new Entry(9, 257));
        entries.add(new Entry(10, 876));
        entries.add(new Entry(11, Float.parseFloat(price)));

        //DataSet 만들기. DataSet에 Entry를 넣는다. 그래프의 선색, 포인트 지점의 원색, 등을 설정 가능
        LineDataSet lineDataSet = new LineDataSet(entries, "<" + name + "> 월별 사용량");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        //XAxis, YAxis로 x축 속성, y축 속성 설정(포지션, 색상 등)
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        //라인차트에 효과를 줄 수 있다.(차트 드래그, 더블클릭, 줌, 애니매이션 등)
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();

        GraphMarkerView marker = new GraphMarkerView(this,R.layout.equi_month_marktext);
        marker.setChartView(lineChart);
        lineChart.setMarker(marker);
    }
}
