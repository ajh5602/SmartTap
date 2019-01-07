package com.example.yeonkyung.smarttap8;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FaqFragment extends Fragment {

    View myView;
    ListView faqlistView;
    ArrayAdapter<String> adapter;
    int Addition = 1;
    Button faq_add_btn, faqBackBtn;

    ArrayList<faqdata> fdata = new ArrayList<>();
    ArrayList<String> ftitle = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.faq_layout, container, false);


        faqlistView = myView.findViewById(R.id.faq_listview);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ftitle);

        faqlistView.setAdapter(adapter);
        faq_add_btn = myView.findViewById(R.id.faq_add_btn);
        faqlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                final int position = i;
                dlg.setTitle("삭제")
                        .setMessage("삭제하시겠습니까?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ftitle.remove(position);
                                fdata.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();

                return true;
            }
        });

        faqlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getActivity(), Faq_List_detail.class);

                intent.putExtra("fdata", fdata.get(i));

            }
        });



        faq_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Faq_List_add.class);
                getActivity().startActivityForResult(intent, Addition);
            }
        });


        //홈으로
        Button faqBackBtn = myView.findViewById(R.id.faqBackBtn);

        faqBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
            }
        });

        return myView;
    }



    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Addition) {
            if (requestCode == RESULT_OK) {
                faqdata d = data.getParcelableExtra("faqdata");
                ftitle.add(d.getFtitle());
                fdata.add(d);
                adapter.notifyDataSetChanged();
            }
        }
    }
}