package com.gandan.android.transitionpractice;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerMain = findViewById(R.id.recyclerMain);
        recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerMain.setAdapter(new RecycleAdapter(this));


    }
}
