package com.gandan.android.retrofitpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  레트로핏을 이용하여 받아온 데이터를 화면으로 나타낼 액티비티.
 *  (RecyclerView 사용)
 */

public class MainActivity extends AppCompatActivity {

    //레트로핏 연습에서 사용할 SERVER_URL을 미리 지정해준다.
    //해당 URL은 공개적인 URL이며, 여러 클래스에서 사용할 수도 있기 때문에 static으로 선언해두었다.
    public static String SERVER_URL = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        setRetrofit();
        postRetrofit();
    }

    //레트로핏 코드를 적어넣기 전에 AndroidManifest.xml에 인터넷 퍼미션을 꼭 주도록 한다!
    private void setRetrofit(){
        //Retrofit.Builder()를 이용해 Retrofit을 생성한다.
        Retrofit retrofitBuilder = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build();
        //Retrofit이 서버와 통신을 수행하며 데이터를 연결할 클래스를 지정해준다.
        CRUDService crudService = retrofitBuilder.create(CRUDService.class);
        //앞서 인터페이스에서 정한 통신방법(GET)과 메소드(getPostList)에 맞춰 Call로 통신을 수행한다.
        Call<List<PostInfo>> getPostList = crudService.getPostList();
        //Callback 패턴을 적용하여 통신이 완료되었을 때 response를 받아올 수 있다.
        getPostList.enqueue(new Callback<List<PostInfo>>() {
            @Override
            public void onResponse(Call<List<PostInfo>> call, Response<List<PostInfo>> response) {
                //연결이 성공했을 때
                if(call.isExecuted()){
                    //해줄 명령을 적으면 된다.
                    Log.e("response:", response.body().get(0).getTitle().toString()+"");

                }
            }

            @Override
            public void onFailure(Call<List<PostInfo>> call, Throwable t) {
                //연결이 실패했을 때, Throwable 객체 변수인 't'를 통해 에러메세지를 받아올 수 있다.
                Log.e("failure:", t.getMessage().toString()+"");

            }
        });
    }

    private void postRetrofit(){
        Retrofit retrofitBuilder = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build();
        CRUDService crudService = retrofitBuilder.create(CRUDService.class);
        PostInfo newInfo = new PostInfo();
        newInfo.setTitle("foo");
        newInfo.setBody("bar");
        newInfo.setUserId(16);
        /**POST를 할 때 List에 넣은 object들이 아닌 하나의 object만 POST하기 때문에
         * getRetrofit()떄처럼 Call<List<PostInfo>>로 Call을 하면,
         * 'Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $' 에러가 난다.
         * 말 그대로, Array타입을 기대했으나 Object 타입이 와서 에러가 났다는 것.
         *
        */
        Call<PostInfo> postPostList = crudService.postPostList(newInfo);
        postPostList.enqueue(new Callback<PostInfo>() {
            @Override
            public void onResponse(Call<PostInfo> call, Response<PostInfo> response) {
                Log.e("response:", response.body().getTitle().toString()+"");
            }

            @Override
            public void onFailure(Call<PostInfo> call, Throwable t) {
                Log.e("failure:", t.getMessage().toString()+"");
            }
        });
    }
}
