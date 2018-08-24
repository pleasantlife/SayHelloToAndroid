package com.gandan.android.recyclersingleselectionpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SingleSelector;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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


        MultiSelector singleSelector = new SingleSelector();
        recyclerAdapter = new RecyclerAdapter(this, contact);
        recyclerMain = findViewById(R.id.recycler_main);
        recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerMain.setAdapter(recyclerAdapter);

        RxEventBus.getInstance().getItemEvent().subscribe(new Observer<Item>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Item item) {
                for(Item it : contact){
                    if(it.equals(item)){
                        it.setSelect(true);
                    } else {
                        it.setSelect(false);
                    }
                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void initContact(String name, String gender, String address){
        Item item = new Item();
        item.setName(name);
        item.setGender(gender);
        item.setAddress(address);
        contact.add(item);

    }
}
