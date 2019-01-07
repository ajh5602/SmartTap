package com.example.yeonkyung.smarttap8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/*
* 수정사항 더이상 없음(6월 14일 작성)
* 액티비티 변경시 drawable/splash.png의 파일 변경(해상도는 16:9의 파일로)
* ex)1920x1080의 이미지
* */
/*스플래시 로딩화면 액티비티*/
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            // 1.5초간 대기
            Thread.sleep(1500);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        // 메인 액티비티로 넘어간다.
        startActivity(new Intent(this, Login.class));
        finish();
    }
}