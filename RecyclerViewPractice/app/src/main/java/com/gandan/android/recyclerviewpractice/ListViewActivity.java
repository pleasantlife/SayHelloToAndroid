package com.gandan.android.recyclerviewpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 *  간단한 리스트뷰를 만들어보자.
 */

public class ListViewActivity extends AppCompatActivity {


    //리스트뷰에 표시하기 위한 String 배열
    static final String[] LIST_MENU = {"List Item 1", "List Item 2", "List Item 3"} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Button btnGoRecyclerView = findViewById(R.id.btnGoRecyclerView);
        btnGoRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //리스트뷰 선언
        ListView listViewTest = findViewById(R.id.listViewTest);
        //String 배열만 표시할 것이라 arrayAdapter로 충분.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, LIST_MENU);
        //리스트뷰에 만들어준 ArrayAdapter를 결합.
        listViewTest.setAdapter(arrayAdapter);
    }
}
