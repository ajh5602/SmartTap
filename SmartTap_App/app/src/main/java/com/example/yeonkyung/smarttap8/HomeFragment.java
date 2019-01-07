package com.example.yeonkyung.smarttap8;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Nazzang on 2018-04-12.
 */

public class HomeFragment extends Fragment {
    String serverAddr = "192.168.1.3";
    View myView;
    int CHECK_NUM1 = 0, CHECK_NUM2 = 0, CHECK_NUM3 = 0, CHECK_NUM4 = 0; // light.setSelected 조건에 사용할 변수. 스위치 변경검사에 사용한다./ onoff기능
    static int myProgressBar = 0;
    ProgressBar progressBar ;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.home_layout, container, false);
        progressBar = (ProgressBar) myView.findViewById(R.id.progressBar);
        getCurrent();



        final Button light1 = (Button) myView.findViewById(R.id.light1);
        final Button light2 = (Button) myView.findViewById(R.id.light2);
        final Button light3 = (Button) myView.findViewById(R.id.light3);
        final Button light4 = (Button) myView.findViewById(R.id.light4);
        final Button refreshBtn = (Button) myView.findViewById(R.id.refreshBtn);

        //light1 색 변경

        light1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CHECK_NUM 이0 일경우 setSelected를 true로 줘서 초록스위치가 나오게 한다.
                if (CHECK_NUM1 == 0) {
                    light1.setSelected(true);
                    String CHECK_NUM_S1_ON = String.valueOf(CHECK_NUM1);
                    insert_button1_ON(CHECK_NUM_S1_ON);
                    CHECK_NUM1 = 1; // 다음에 누르면 색이 변하도록 값을 변경.

                } else {
                    light1.setSelected(false);
                    String CHECK_NUM_S1_OFF = String.valueOf(CHECK_NUM1);
                    insert_button1_OFF(CHECK_NUM_S1_OFF);
                    CHECK_NUM1 = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });
        //light2 색 변경
        light2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CHECK_NUM 이0 일경우 setSelected를 true로 줘서 초록스위치가 나오게 한다.
                if (CHECK_NUM2 == 0) {
                    light2.setSelected(true);
                    String CHECK_NUM_S2_ON = String.valueOf(CHECK_NUM2);
                    insert_button2_ON(CHECK_NUM_S2_ON);
                    CHECK_NUM2 = 1; // 다음에 누르면 색이 변하도록 값을 변경.
                } else {
                    light2.setSelected(false);
                    String CHECK_NUM_S2_OFF = String.valueOf(CHECK_NUM2);
                    insert_button2_OFF(CHECK_NUM_S2_OFF);
                    CHECK_NUM2 = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });
        //light3 색 변경
        light3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CHECK_NUM 이0 일경우 setSelected를 true로 줘서 초록스위치가 나오게 한다.
                if (CHECK_NUM3 == 0) {
                    light3.setSelected(true);
                    String CHECK_NUM_S3_ON = String.valueOf(CHECK_NUM3);
                    insert_button3_ON(CHECK_NUM_S3_ON);
                    CHECK_NUM3 = 1; // 다음에 누르면 색이 변하도록 값을 변경.
                } else {
                    light3.setSelected(false);
                    String CHECK_NUM_S3_OFF = String.valueOf(CHECK_NUM3);
                    insert_button3_OFF(CHECK_NUM_S3_OFF);
                    CHECK_NUM3 = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });
        //light4 색 변경
        light4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CHECK_NUM 이0 일경우 setSelected를 true로 줘서 초록스위치가 나오게 한다.
                if (CHECK_NUM4 == 0) {
                    light4.setSelected(true);
                    String CHECK_NUM_S4_ON = String.valueOf(CHECK_NUM4);
                    insert_button4_ON(CHECK_NUM_S4_ON);
                    CHECK_NUM4 = 1; // 다음에 누르면 색이 변하도록 값을 변경.
                } else {
                    light4.setSelected(false);
                    String CHECK_NUM_S4_OFF = String.valueOf(CHECK_NUM4);
                    insert_button4_OFF(CHECK_NUM_S4_OFF);
                    CHECK_NUM4 = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Act_refresh();


                /*

                //progressbar 값 설정
                int progressvalue = 50;

                Log.d("progress는!!", myProgressBar+"");

                TextView progressTxt = (TextView) myView.findViewById(R.id.progressTxt);
                int min = myProgressBar;
                    progressTxt.setText(min + "/3200 W (" + min / 32 + "%)");

                */

                int progressvalue = 10;

                Log.d("progress는!!", myProgressBar+"");

                TextView progressTxt = (TextView) myView.findViewById(R.id.progressTxt);


                Log.d("TAGzzz", myProgressBar+1000+"");
                progressBar.setProgress(myProgressBar/32);
            }
        });




        //devicemenuBtn 팝업메뉴 설정(onoff 할수있는 기기변경)
        final Button devicemenuBtn1 = (Button) myView.findViewById(R.id.devicemenuBtn1);
        final Button devicemenuBtn2 = (Button) myView.findViewById(R.id.devicemenuBtn2);
        final Button devicemenuBtn3 = (Button) myView.findViewById(R.id.devicemenuBtn3);
        final Button devicemenuBtn4 = (Button) myView.findViewById(R.id.devicemenuBtn4);

        devicemenuBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), view);

                getActivity().getMenuInflater().inflate(R.menu.homedevicemenu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.m1:

                                devicemenuBtn1.setText(menuItem.getTitle());
                                break;
                            case R.id.m2:
                                devicemenuBtn1.setText(menuItem.getTitle());
                                break;
                            case R.id.m3:
                                devicemenuBtn1.setText(menuItem.getTitle());
                                break;
                            case R.id.m4:
                                devicemenuBtn1.setText(menuItem.getTitle());
                            default:
                                break;
                            //기기포트 변경 php 전송 1포트
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        devicemenuBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), view);

                getActivity().getMenuInflater().inflate(R.menu.homedevicemenu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.m1:
                                devicemenuBtn2.setText(menuItem.getTitle());
                                break;
                            case R.id.m2:
                                devicemenuBtn2.setText(menuItem.getTitle());
                                break;
                            case R.id.m3:
                                devicemenuBtn2.setText(menuItem.getTitle());
                                break;
                            case R.id.m4:
                                devicemenuBtn2.setText(menuItem.getTitle());
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        devicemenuBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), view);

                getActivity().getMenuInflater().inflate(R.menu.homedevicemenu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.m1:
                                devicemenuBtn3.setText(menuItem.getTitle());
                                break;
                            case R.id.m2:
                                devicemenuBtn3.setText(menuItem.getTitle());
                                break;
                            case R.id.m3:
                                devicemenuBtn3.setText(menuItem.getTitle());
                                break;
                            case R.id.m4:
                                devicemenuBtn3.setText(menuItem.getTitle());
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        devicemenuBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), view);

                getActivity().getMenuInflater().inflate(R.menu.homedevicemenu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.m1:
                                devicemenuBtn4.setText(menuItem.getTitle());
                                break;
                            case R.id.m2:
                                devicemenuBtn4.setText(menuItem.getTitle());
                                break;
                            case R.id.m3:
                                devicemenuBtn4.setText(menuItem.getTitle());
                                break;
                            case R.id.m4:
                                devicemenuBtn4.setText(menuItem.getTitle());
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
        //devicemenuBtn 팝업메뉴 설정(onoff 할수있는 기기변경) 끝


        //기기조회 인텐트
        Button equi_Btn = (Button) myView.findViewById(R.id.equi_Btn);
        equi_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent equiIntent = new Intent(getActivity(), Equi_List.class);
                getActivity().startActivity(equiIntent);
            }
        });

        //예약 인텐트
        Button reservationBtn = (Button) myView.findViewById(R.id.reservationBtn);
        reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reservationIntent = new Intent(getActivity(), Reservation_list.class);
                getActivity().startActivity(reservationIntent);
            }
        });

        //멀티탭 인텐트
        Button multiBtn = (Button) myView.findViewById(R.id.multiBtn);
        multiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent multiIntent = new Intent(getActivity(), Multi_list.class);
                getActivity().startActivity(multiIntent);
            }
        });
        return myView;
    }

    /*light1 변경시 서버에 ON 명령어 보내는 소스*/
    private void insert_button1_ON(String CHECK_NUM) {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String CHECK_NUM = (String) params[0];
                    Log.d("TAG", "버튼 1 ON 소스 진입");
                   // String link = "http://192.168.1.9/port1/ON";
                    String link = "http://" + serverAddr + "/port1/ON";
                    String data = URLEncoder.encode("CHECK_NUM", "UTF-8") + "=" + URLEncoder.encode(CHECK_NUM, "UTF-8");

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
        Log.d("TAG", "버튼 1 ON 소스 끝");
        task.execute(CHECK_NUM);
    }

    /*light1 변경시 서버에 OFF 명령어 보내는 소스*/
    private void insert_button1_OFF(String CHECK_NUM) {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String CHECK_NUM = (String) params[0];
                    Log.d("TAG", "버튼 1 OFF 소스 진입");
                    //String link = "http://192.168.1.9/port1/OFF";
                    String link = "http://" + serverAddr + "/port1/OFF";
                    String data = URLEncoder.encode("CHECK_NUM", "UTF-8") + "=" + URLEncoder.encode(CHECK_NUM, "UTF-8");

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
        Log.d("TAG", "버튼 1 OFF 소스 끝");
        task.execute(CHECK_NUM);
    }

    /* LIGHT2 시작 */
    /*light2 변경시 서버에 ON 명령어 보내는 소스*/
    private void insert_button2_ON(String CHECK_NUM) {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String CHECK_NUM = (String) params[0];
                    Log.d("TAG", "버튼 2 ON 소스 진입");
                  //  String link = "http://192.168.1.9/port2/ON";
                    String link = "http://" + serverAddr + "/port2/ON";
                    String data = URLEncoder.encode("CHECK_NUM", "UTF-8") + "=" + URLEncoder.encode(CHECK_NUM, "UTF-8");

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
        Log.d("TAG", "버튼 2 ON 소스 끝");
        task.execute(CHECK_NUM);
    }

    /*light2 변경시 서버에 OFF 명령어 보내는 소스*/
    private void insert_button2_OFF(String CHECK_NUM) {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String CHECK_NUM = (String) params[0];
                    Log.d("TAG", "버튼 2 OFF 소스 진입");
                    //String link = "http://192.168.1.9/port2/OFF";
                    String link = "http://" + serverAddr + "/port2/OFF";
                    String data = URLEncoder.encode("CHECK_NUM", "UTF-8") + "=" + URLEncoder.encode(CHECK_NUM, "UTF-8");

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
        Log.d("TAG", "버튼 2 OFF 소스 실행");
        task.execute(CHECK_NUM);
    }

    /* LIGHT3 시작 */
    /*light3 변경시 서버에 ON 명령어 보내는 소스*/
    private void insert_button3_ON(String CHECK_NUM) {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String CHECK_NUM = (String) params[0];
                    Log.d("TAG", "버튼 3 ON 소스 진입");
                    //String link = "http://192.168.1.9/port3/ON";
                    String link = "http://" + serverAddr + "/port3/ON";
                    String data = URLEncoder.encode("CHECK_NUM", "UTF-8") + "=" + URLEncoder.encode(CHECK_NUM, "UTF-8");

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
        Log.d("TAG", "버튼 3 ON 소스 끝");
        task.execute(CHECK_NUM);
    }

    /*light3 변경시 서버에 OFF 명령어 보내는 소스*/
    private void insert_button3_OFF(String CHECK_NUM) {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String CHECK_NUM = (String) params[0];
                    Log.d("TAG", "버튼 3 OFF 소스 진입");
                    //String link = "http://192.168.1.9/port3/OFF";
                    String link = "http://" + serverAddr + "/port3/OFF";
                    //String data = URLEncoder.encode("CHECK_NUM", "UTF-8") + "=" + URLEncoder.encode(CHECK_NUM, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                   // wr.write(data);
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
        Log.d("TAG", "버튼 3 OFF 소스 실행");
        task.execute(CHECK_NUM);
    }

    /* LIGHT4 시작 */
    /*light4 변경시 서버에 ON 명령어 보내는 소스*/
    private void insert_button4_ON(String CHECK_NUM) {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String CHECK_NUM = (String) params[0];
                    Log.d("TAG", "버튼 4 ON 소스 진입");
                    //String link = "http://192.168.1.9/port0/ON";
                    String link = "http://" + serverAddr + "/port0/ON";
                    String data = URLEncoder.encode("CHECK_NUM", "UTF-8") + "=" + URLEncoder.encode(CHECK_NUM, "UTF-8");

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
        Log.d("TAG", "버튼 4 ON 소스 끝");
        task.execute(CHECK_NUM);
    }

    /*light3 변경시 서버에 OFF 명령어 보내는 소스*/
    private void insert_button4_OFF(String CHECK_NUM) {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String CHECK_NUM = (String) params[0];
                    Log.d("TAG", "버튼 4 OFF 소스 진입");
                   // String link = "http://192.168.1.9/port0/OFF";
                    String link = "http://" + serverAddr + "/port0/OFF";
                    String data = URLEncoder.encode("CHECK_NUM", "UTF-8") + "=" + URLEncoder.encode(CHECK_NUM, "UTF-8");

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
        Log.d("TAG", "버튼 4 OFF 소스 실행");
        task.execute(CHECK_NUM);
    }

    private void Act_refresh() {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }


            @Override
            protected String doInBackground(String... params) {
                try {

                    Log.d("TAG", "refresh 코드 진입");
                    TextView progressTxt = (TextView) myView.findViewById(R.id.progressTxt);
                    int min = myProgressBar;

                 //   progressBar.setProgress(min/32);

                    Log.d("TAGzzz", myProgressBar+1000+"");
                    //String link = "http://192.168.1.9/get/elec";
                    String link = "http://" + serverAddr + "/get/elec";
                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String line = null;

                    //read server response
                    Log.d("TAG", sb.toString() + "라구연!!@@");
                    while ((line = reader.readLine()) != null) { // 못들어감
                        Log.d("TAG", "refresh 코드 진입11");
                        sb.append(line);
                        break;
                    }
                    Log.d("TAG", sb.toString() + "라구연!!@@@@@@");
                    myProgressBar = Integer.parseInt(sb.toString());
                    progressTxt.setText(myProgressBar + "/3200 W (" + myProgressBar / 32 + "%)");
                    progressBar.setProgress(myProgressBar/32);
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception : " + e.getMessage());
                }
            }
        }
        insertData task = new insertData();
        Log.d("TAG", "refresh 코드 실행");
        task.execute();
    }



    private void getCurrent() {

        class insertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }


            @Override
            protected String doInBackground(String... params) {
                try {
                    TextView progressTxt = (TextView) myView.findViewById(R.id.progressTxt);
                    Log.d("TAG", "getCurrent 코드 진입");
                   // String link = "http://192.168.1.9/get/elec";
                    String link = "http://" + serverAddr + "/get/elec";

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer sb = new StringBuffer();
                    String line = null;
                    Log.d("TAG : ", sb.toString() + "왜 안되냐고");
                    //read server response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    Log.d("TAG : ", sb.toString());

                    myProgressBar = Integer.parseInt(sb.toString());
                    progressTxt.setText(myProgressBar + "/3200 W (" + myProgressBar / 32 + "%)");
                 //   Log.d("TAGzzz", myProgressBar+1000+"");
                    progressBar.setProgress(myProgressBar/32);
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception : " + e.getMessage());
                }
            }
        }
        insertData task = new insertData();
        Log.d("TAG", "refresh 코드 실행");
        task.execute();
    }





}

