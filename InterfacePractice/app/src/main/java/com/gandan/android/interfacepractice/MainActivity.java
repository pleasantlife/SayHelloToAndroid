package com.gandan.android.interfacepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataListener, AnotherListener {

    List<String> hundredList = new ArrayList<>();
    RecyclerView recyclerInterface;
    RecyclerInterfaceAdapter recyclerInterfaceAdapter;
    TextView txtInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addData();
        txtInterface = findViewById(R.id.txtInterface);
        recyclerInterface = findViewById(R.id.recyclerInterface);
        //MainActivity에서 implements로 구현했기 때문에, 리사이클러어댑터뷰에 this로 인터페이스를 넘겨준다.
        //recyclerInterfaceAdapter = new RecyclerInterfaceAdapter(this, hundredList, this);
        //혹은 새로 만들어서 넘겨줄 수도 있다.
        /*recyclerInterfaceAdapter = new RecyclerInterfaceAdapter(this, hundredList, new DataListener() {
            @Override
            public void sendData(String data) {
                txtInterface.setText(data);
            }
        });*/

        //람다로 좀 더 간단하게 넘길 수도 있다.
        recyclerInterfaceAdapter = new RecyclerInterfaceAdapter(this, hundredList, data -> txtInterface.setText(data));
        recyclerInterfaceAdapter.setAnotherListener(this);
        recyclerInterface.setAdapter(recyclerInterfaceAdapter);
        recyclerInterface.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addData(){
        for(int i=0; i < 100; i++){
            String value = "테스트 데이터" + i;
            hundredList.add(value);
        }
    }

    @Override
    public void sendData(String data) {
        txtInterface.setText(data);
    }

    @Override
    public void listen(String data) {
        Toast.makeText(this, data+"", Toast.LENGTH_SHORT).show();
    }
}
