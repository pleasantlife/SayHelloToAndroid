package com.gandan.android.retrofitpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gandan.android.retrofitpractice.GetActivity.SERVER_URL;

public class QueryActivity extends AppCompatActivity {

    LinearLayout queryContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        queryContents = findViewById(R.id.queryContents);
        queryRetrofit();
    }

    private void queryRetrofit(){
        Retrofit retrofitBuilder = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build();
        CRUDService crudService = retrofitBuilder.create(CRUDService.class);
        Call<PostInfo> postList = crudService.getPost(1);
        postList.enqueue(new Callback<PostInfo>() {
            @Override
            public void onResponse(Call<PostInfo> call, Response<PostInfo> response) {
                if(response.isSuccessful()){
                    String responseBody = response.body().getBody();
                    TextView queryBody = new TextView(getApplicationContext());
                    queryBody.setText(responseBody);
                    queryContents.addView(queryBody);
                    }
                }

            @Override
            public void onFailure(Call<PostInfo> call, Throwable t) {

            }
        });

    }
}
