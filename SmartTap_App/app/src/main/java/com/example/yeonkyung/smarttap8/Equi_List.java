package com.example.yeonkyung.smarttap8;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Equi_List extends AppCompatActivity {
    ListView mainList;

    Equi_List_adapter adapter;

    int Addition = 1;

    ArrayList<Equi_List_item> storage = new ArrayList<>();

    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.equi_list);

        init();
// URL 설정.
        String url = "http://13.124.107.126/kwak/getJason.php";

        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();

    }

    void init(){
        /*
        Equi_List_item equi_list_item1 = new Equi_List_item("헤어드라이기", "안녕", "33", "1", "2");
        Equi_List_item equi_list_item2 = new Equi_List_item("난풍기", "안녕", "33", "1", "2");
        Equi_List_item equi_list_item3 = new Equi_List_item("스팀청소기", "안녕", "33", "1", "2");
        Equi_List_item equi_list_item4 = new Equi_List_item("선풍기", "안녕", "33", "1", "2");
        storage.add(equi_list_item1);
        storage.add(equi_list_item2);
        storage.add(equi_list_item3);
        storage.add(equi_list_item4);
        */
        mainList = (ListView)findViewById(R.id.mainList);
        adapter = new Equi_List_adapter(storage,this);
        mainList.setAdapter(adapter);
        mainList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Equi_List.this);
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
                                storage.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        });

/*        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Equi_List.this, Equi_List_detail.class);
                intent.putExtra("data", storage.get(i));
                startActivity(intent);
            }
        });*/

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Equi_Month.class);
                intent.putExtra("name",storage.get(position).getEquiname());
                intent.putExtra("price", storage.get(position).getEquicurrentM());


                startActivity(intent);
            }
        });

    }


    public void onClick(View v){
        if(v.getId() ==  R.id.addList_btn){
            Log.d("혹시 여기냐?", "여기냐고?");
            Intent intent = new Intent(this, Equi_List_add.class);
            startActivityForResult(intent, Addition);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==Addition){
            if(resultCode==RESULT_OK){
             //   Equi_List_item e = Equi_List_item.getParcelableExtra("Equi_List_item");
             //   storage.add(e);
            }
        }


    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
         //   test.setText(s);
            /*
            ArrayList<String> arrayList = null;
            arrayList = dataParser(s);
            data myData[] = {null};
            myData[0] = new data(arrayList.get(0), "할말 없습니다");
            myData[1] = new data(arrayList.get(3), "할말 없습니다");

            title.add(arrayList.get(0).toString());
            title.add(arrayList.get(3).toString());

            data myData1 = new data("난풍기", "할말 없습니다");
            data myData2 = new data("드라이기", "할말 없습니다");
            data myData3 = new data("스팀청소기", "할말 없습니다");
            storage.add(myData1);
            storage.add(myData2);
            storage.add(myData3);
*/
            ArrayList<ArrayList<String>> arrayList = null;
            arrayList = dataParser(s);
//전기세 구하는 알고리즘
            for (int i=0; i<arrayList.size()-1; i++) {

                double kwh = Double.parseDouble(arrayList.get(i).get(2).toString())/1000.0; //kw로 변환하는 작업
                kwh = kwh/3600.0; // kw/h 로 변환
                double price;

                //누진세 적용
                if(kwh <= 100.0) {
                    price = kwh * 60.7;
                } else if (kwh <= 200) {
                    price = (100.0 * 60.7) + ((kwh-100.0) * 125.9);
                } else if (kwh <= 300) {
                    price = (100.0 * 60.7) + (100.0 * 125.9) + ((kwh-200)*187.9);
                } else if (kwh <= 400) {
                    price = (100.0 * 60.7) + (100.0 * 125.9) + (100.0*187.9) + ((kwh-300)*280.6);
                } else if (kwh <= 500){
                    price = (100.0 * 60.7) + (100.0 * 125.9) + (100.0*187.9) + (100.0 * 280.6) + ((kwh-400)*417.7);
                } else {
                    price = (100.0 * 60.7) + (100.0 * 125.9) + (100.0*187.9) + (100.0 * 280.6) + (100.0 * 417.7) + ((kwh-500)*709.5);
                }
                String kwh_str = String.format("%.2f", kwh*1000.0);
                String price_str = String.format("%.2f", price);
                String name = null;
                if(arrayList.get(i).get(0).toString().equals("1"))  {
                    name = "헤어드라이기";
                } else if (arrayList.get(i).get(0).toString().equals("2")) {
                    name = "난풍기";
                }else if (arrayList.get(i).get(0).toString().equals("3")) {
                    name = "스팀청소기";
                }else if (arrayList.get(i).get(0).toString().equals("4")) {
                    name = "선풍기";
                }
                Log.d(i+"번째", "Add 해버리기");
                Log.d(arrayList.get(i).get(0).toString(), kwh_str + "");
                storage.add(new Equi_List_item(name, "라랄", kwh_str + "", arrayList.get(i).get(2) + "", price_str+""));
                adapter.notifyDataSetChanged();

            }

        }
    }



    public ArrayList<ArrayList<String>> dataParser(String jsonString) {

        String date = null;
        String device = null;
        String useageSum = null;

        String[] arraysum = new String[3];
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        JSONArray jarray = null;
        try {
            jarray = new JSONObject(jsonString).getJSONArray("webnautes");

            for (int i = 0; i < jarray.length(); i++) {
                Log.d("제이슨어레이의 크기는", jarray.length()+"입니다");
                HashMap map = new HashMap<>();

                JSONObject jObject = jarray.getJSONObject(i);
                ArrayList<String> arrayList1 = new ArrayList<>();

                device = jObject.optString("device");
                date = jObject.optString("date");
                useageSum = jObject.optString("useageSum");
                arrayList1.add(device);
                arrayList1.add(date);
                arrayList1.add(useageSum);
                Log.d("arrayList의 사이즈는", arrayList.size()+"입니다");
                arrayList.add(arrayList1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i=0; i<arrayList.size(); i++)
            Log.d("Device", arrayList.get(i).toString()+"");
        return arrayList;
    }



}

