package com.gandan.android.recyclerviewpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //List<String>을 미리 선언해주고, 추후에 내용을 채운 후 adapter에 넣는다.
    List<String> dummyList = new ArrayList<>();
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    RecyclerViewTestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoListView = findViewById(R.id.btnGoListView);
        btnGoListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
        //2) 리사이클러뷰와 어댑터 변수를 선언한다.
        RecyclerView recyclerViewTest = findViewById(R.id.recyclerViewTest);
        testAdapter = new RecyclerViewTestAdapter(this, dummyList);
        //3) 리사이클러뷰와 어댑터를 결합한다.
        recyclerViewTest.setAdapter(testAdapter);
        //4) 어떻게 보일지(격자, 리스트 형태 등)를 결정하는 레이아웃매니저를 결합한다.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewTest.setLayoutManager(linearLayoutManager);
        //5) 데이터를 넣는다.
        setData(100);

        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                          setData(100);
            }
        };
        recyclerViewTest.addOnScrollListener(endlessRecyclerViewScrollListener);

    }

    //가장먼저, 리사이클러뷰에 표시할 데이터를 생성한다.
    private void setData(int offset){

        for(int i =0; i < offset; i++){
            String dummyString = "Hello" + dummyList.size();
            dummyList.add(dummyString);
        }
        testAdapter.notifyDataSetChanged();
    }
}
