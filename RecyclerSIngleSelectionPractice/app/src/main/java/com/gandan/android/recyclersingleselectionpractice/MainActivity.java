package com.gandan.android.recyclersingleselectionpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Item> contact = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initContact("Sherlock", "man", "England");
        initContact("IronMan", "man", "America");
        initContact("Summer", "woman", "America");
        initContact("ParkSinHye", "woman", "Korea");
        initContact("MoonJaeIn", "man", "Korea");

        recyclerAdapter = new RecyclerAdapter(this, contact);
        recyclerMain = findViewById(R.id.recycler_main);
        recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerMain.setAdapter(recyclerAdapter);


    }

    private void initContact(String name, String gender, String address){
        Item item = new Item();
        item.setName(name);
        item.setGender(gender);
        item.setAddress(address);
        contact.add(item);

    }
}
