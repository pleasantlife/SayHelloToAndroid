package com.gandan.android.recyclerviewpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<String> dummyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewTest = findViewById(R.id.recyclerViewTest);
        RecyclerViewTestAdapter testAdapter = new RecyclerViewTestAdapter(this, dummyList);
        recyclerViewTest.setAdapter(testAdapter);
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(this));

        setData();

    }

    private void setData(){

        for(int i =0; i < 10; i++){
            String dummyString = "Hello" + i;
            dummyList.add(dummyString);
        }
    }
}
