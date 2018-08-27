package com.gandan.android.recyclersingleselectionpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    List<Item> contact = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerMain;
    boolean single;
    RadioGroup radioGroup;
    RadioButton radioSingle, radioMulti;
    EditText inputText;
    Button btnInputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        radioSingle = findViewById(R.id.radio_single);
        radioMulti = findViewById(R.id.radio_multi);
        inputText = findViewById(R.id.input_text);
        btnInputData = findViewById(R.id.btn_input_data);

        recyclerAdapter = new RecyclerAdapter(this, contact, single);
        recyclerMain = findViewById(R.id.recycler_main);
        recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerMain.setAdapter(recyclerAdapter);


        btnInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initContact(inputText.getText().toString(), "man", "Korea");
                inputText.setText("");
                recyclerAdapter.notifyDataSetChanged();
            }
        });


        initContact("Sherlock", "man", "England");
        initContact("IronMan", "man", "America");
        initContact("Summer", "woman", "America");
        initContact("ParkSinHye", "woman", "Korea");
        initContact("MoonJaeIn", "man", "Korea");


        single = false;
        radioMulti.setChecked(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioSingle.isChecked()){
                    radioMulti.setChecked(false);
                    single = true;
                } else {
                    radioMulti.setChecked(true);
                    single = false;
                }
                for(Item it : contact){
                    it.setSelect(false);
                }
                Toast.makeText(MainActivity.this, "선택 방식 초기화!", Toast.LENGTH_SHORT).show();
                recyclerAdapter.notifyDataSetChanged();
            }
        });


        RxEventBus.getInstance().getItemEvent().subscribe(new Observer<Item>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Item item) {
                if(single) {
                    for (Item it : contact) {
                        if (it.equals(item)) {
                            it.setSelect(true);
                        } else {
                            it.setSelect(false);
                        }
                    }
                } else {
                    for(Item it : contact){
                        if(it.equals(item)){
                            it.setSelect(true);
                        }
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
