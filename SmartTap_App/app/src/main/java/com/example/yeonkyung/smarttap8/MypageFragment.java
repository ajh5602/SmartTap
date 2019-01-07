package com.example.yeonkyung.smarttap8;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MypageFragment extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mypage_layout, container, false);

        final EditText myp_nameText = myView.findViewById(R.id.myp_nameText);
        final EditText myp_idText = myView.findViewById(R.id.myp_idText);
        final EditText myp_pwText = myView.findViewById(R.id.myp_pwText);
        final EditText myp_pwTextOK = myView.findViewById(R.id.myp_pwTextOK);

        final TextView myp_checkpasswordtv = myView.findViewById(R.id.myp_checkpasswordtv);


        Button myp_NoBtn = myView.findViewById(R.id.myp_NoBtn);
        final Button myp_OkBtn = myView.findViewById(R.id.myp_OkBtn);


        //비밀번호 일치 검사
        myp_pwTextOK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = myp_pwText.getText().toString();
                String confirm = myp_pwTextOK.getText().toString();

                if(password.equals(confirm)){
                    myp_checkpasswordtv.setText("비밀번호가 일치합니다.");
                    myp_checkpasswordtv.setTextColor(Color.BLACK);
                }else {
                    myp_checkpasswordtv.setText("비밀번호가 틀립니다.");
                    myp_checkpasswordtv.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //취소Btn
        myp_NoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
            }
        });
        //완료Btn
        myp_OkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이름 확인
                if(myp_nameText.getText().toString().length() ==0){
                    Toast.makeText(getActivity(), "이름을 입력하세요!",Toast.LENGTH_SHORT).show();
                    myp_nameText.requestFocus();
                    return;
                }

                //아이디 확인
                if(myp_idText.getText().toString().length() ==0){
                    Toast.makeText(getActivity(), "아이디를 입력하세요!",Toast.LENGTH_SHORT).show();
                    myp_idText.requestFocus();
                    return;
                }

                if(myp_pwText.getText().toString().length() ==0){
                    Toast.makeText(getActivity(), "비밀번호를 입력하세요!",Toast.LENGTH_SHORT).show();
                    myp_pwText.requestFocus();
                    return;
                }

                if(myp_pwTextOK.getText().toString().length() ==0){
                    Toast.makeText(getActivity(), "비밀번호가 일치하지 않습니다!",Toast.LENGTH_SHORT).show();
                    myp_pwTextOK.setText("");
                    myp_pwTextOK.setText("");
                    myp_pwTextOK.requestFocus();
                    return;
                }

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
            }
        });


        return myView;
    }

}
