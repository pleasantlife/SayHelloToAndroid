package com.gandan.android.paginglibrarypractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RetrofitInit retrofitInit = new RetrofitInit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<ListingResponse> response = retrofitInit.redditService.getTop("subreddit", "hello", 10);
        response.enqueue(new Callback<ListingResponse>() {
            @Override
            public void onResponse(Call<ListingResponse> call, Response<ListingResponse> response) {
                Log.e("response", response.body().getBefore()+"");
            }

            @Override
            public void onFailure(Call<ListingResponse> call, Throwable t) {
                Log.e("error", t.getMessage()+"");
            }
        });
    }
}
