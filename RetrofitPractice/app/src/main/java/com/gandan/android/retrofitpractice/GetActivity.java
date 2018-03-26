package com.gandan.android.retrofitpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  레트로핏을 이용하여 받아온 데이터를 화면으로 나타낼 액티비티.
 *  (RecyclerView 사용)
 */

public class GetActivity extends AppCompatActivity {

    //레트로핏 연습에서 사용할 SERVER_URL을 미리 지정해준다.
    //해당 URL은 공개적인 URL이며, 여러 클래스에서 사용할 수도 있기 때문에 static으로 선언해두었다.
    public static String SERVER_URL = "https://jsonplaceholder.typicode.com/";

    LinearLayout getContents, queryContents, responseContents, deepQueryContents;
    Button btnGoQueryActivity, btnQueryResponse, btnObservable;
    EditText editTitle, editQuery, editCommentsId;
    TextView queryUrl, observableComments;
    List<PostInfo> commentList = new ArrayList<>();
    List<PostInfo> flowableList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        editTitle = findViewById(R.id.editTitle);
        editQuery = findViewById(R.id.editQuery);
        getContents = findViewById(R.id.getContents);
        queryContents = findViewById(R.id.queryContents);
        responseContents = findViewById(R.id.responseContents);
        deepQueryContents = findViewById(R.id.deepQueryContents);
        btnGoQueryActivity = findViewById(R.id.btnGoQueryActivity);
        observableComments = findViewById(R.id.observableComments);
        btnObservable = findViewById(R.id.btnObservable);
        btnObservable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observableRetrofit();
            }
        });
        editCommentsId = findViewById(R.id.editCommentsId);
        queryUrl = findViewById(R.id.queryUrl);
        btnQueryResponse = findViewById(R.id.btnQueryResponse);
        btnQueryResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryRetrofit();
            }
        });
        btnGoQueryActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRetrofit();
            }
        });
        getRetrofit();
        restApiRetrofit();
        flowableRetrofit();
        retrofitWithOkhttp();
        multipartPostRetrofit();
        voidRetrofit();
        completableRetrofit();
    }

    //레트로핏 코드를 적어넣기 전에 AndroidManifest.xml에 인터넷 퍼미션을 꼭 주도록 한다!
    private void getRetrofit(){
        //Retrofit.Builder()를 이용해 Retrofit을 생성한다.
        //addConverterFactory를 하고 괄호 안에 GsonConverterFactory를 생성해준 이유는
        //Gson이 json 데이터를, 지정된 자바의 클래스를 선언한 변수를 통해 클래스에 넣어줄 수 있기 때문이다.
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
                    TextView[] textView = new TextView[10];
                    for(int i=0; i < 10; i++){
                        String responseContent = response.body().get(i).getTitle();
                        TextView textResponseTitle = new TextView(getApplicationContext());
                        textResponseTitle.setText(responseContent);
                        getContents.addView(textResponseTitle);
                        textView[i] = textResponseTitle;
                    }

                }
            }

            @Override
            public void onFailure(Call<List<PostInfo>> call, Throwable t) {
                //연결이 실패했을 때, Throwable 객체 변수인 't'를 통해 에러메세지를 받아올 수 있다.
                Log.e("failure:", t.getMessage().toString()+"");

            }
        });
    }

    private void restApiRetrofit(){
        Retrofit retrofitBuilder = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build();
        CRUDService crudService = retrofitBuilder.create(CRUDService.class);
        Call<PostInfo> postList = crudService.getPost(1);
        postList.enqueue(new Callback<PostInfo>() {
            @Override
            public void onResponse(Call<PostInfo> call, Response<PostInfo> response) {
                if(call.isExecuted()){

                    String responseBody = response.body().getTitle();
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



    private void postRetrofit(){
        Retrofit retrofitBuilder = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build();
        CRUDService crudService = retrofitBuilder.create(CRUDService.class);
        PostInfo newInfo = new PostInfo();
        String newTitle = editTitle.getText().toString();
        newInfo.setTitle(newTitle);
        newInfo.setBody("bar");
        newInfo.setUserId(16);
        /**
         * POST를 할 때 List에 넣은 object들이 아닌 하나의 object만 POST하기 때문에
         * getRetrofit()떄처럼 Call<List<PostInfo>>로 Call을 하면,
         * 'Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $' 에러가 난다.
         * 말 그대로, Array타입을 기대했으나 Object 타입이 와서 에러가 났다는 것.
         *
        */
        Call<PostInfo> postPostList = crudService.postPostList(newInfo);
        postPostList.enqueue(new Callback<PostInfo>() {
            @Override
            public void onResponse(Call<PostInfo> call, Response<PostInfo> response) {
                if(call.isExecuted()) {
                    String responseTitle = response.body().getTitle();
                    TextView txtResponse = new TextView(getApplicationContext());
                    txtResponse.setText(responseTitle);
                    responseContents.addView(txtResponse);
                }
            }

            @Override
            public void onFailure(Call<PostInfo> call, Throwable t) {
                Log.e("failure:", t.getMessage().toString()+"");
            }
        });
    }

    private void queryRetrofit(){
        Retrofit retrofitBuilder = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build();
        CRUDService crudService = retrofitBuilder.create(CRUDService.class);
        Call<List<PostInfo>> queryList = crudService.getPostQuery(Integer.parseInt(editQuery.getText().toString()));
        queryList.enqueue(new Callback<List<PostInfo>>() {
            @Override
            public void onResponse(Call<List<PostInfo>> call, Response<List<PostInfo>> response) {
                queryUrl.setText(call.request().toString()+"");
                TextView[] textQuery = new TextView[response.body().size()];
                if(call.isExecuted()){
                    for(PostInfo postInfo : response.body()){
                        String query = postInfo.getTitle();
                        TextView newQuery = new TextView(getApplicationContext());
                        newQuery.setText(query);
                        deepQueryContents.addView(newQuery);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<PostInfo>> call, Throwable t) {
                Log.e("t", t.getMessage()+"");
            }
        });
    }

    //Retrofit2에서는 RxJavaAdapter를 통해 RxJava를 지원한다.
    //따라서 Call이 아닌 Observable을 통해 데이터를 받을 수도 있다.
    private void observableRetrofit(){
        final int commentsId = Integer.parseInt(editCommentsId.getText().toString());
        //addCallAdapterFactory에 RxJava2CallAdapterFacotry를 지정해준다.
        Retrofit observableRetrofit = new Retrofit.Builder().baseUrl(SERVER_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        CRUDService crudService = observableRetrofit.create(CRUDService.class);
        Observable<List<PostInfo>> info = crudService.getComments(commentsId);
        //observeOn에 AndroidSchedulers.mainThread()를 지정해주지 않으면 android.os.NetworkOnMainThreadException 에러가 난다.
        info.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<PostInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<PostInfo> postInfos) {
                commentList = postInfos;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                observableComments.setText(commentList.get(commentsId).getBody());
            }
        });
    }

    //Retrofit에서 지원하는 RxJava2 Adapter를 이용해 Flowable로도 데이터를 받을 수 있다.
    private void flowableRetrofit(){
        Retrofit flowableRetrofit = new Retrofit.Builder().baseUrl(SERVER_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        CRUDService crudService = flowableRetrofit.create(CRUDService.class);
        Flowable<List<PostInfo>> flowInfo = crudService.getFlowableComments(3);
        flowInfo.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FlowableSubscriber<List<PostInfo>>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(List<PostInfo> postInfos) {
                flowableList = postInfos;
                Log.e("postInfos", postInfos.toString()+"");
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.e("complete", flowableList.toString());

            }
        });
    }

    private void retrofitWithOkhttp(){
        Retrofit retrofitOkhttp = new Retrofit.Builder().baseUrl(SERVER_URL).client(new OkHttpClient.Builder().retryOnConnectionFailure(true).build()).addConverterFactory(GsonConverterFactory.create()).build();

    }

    private void multipartPostRetrofit(){
        Retrofit retrofitMultipart = new Retrofit.Builder().baseUrl(SERVER_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        CRUDService service = retrofitMultipart.create(CRUDService.class);
        PostInfo multipartInfo = new PostInfo();
        String titleString = "이것은 타이틀입니다.";
        String bodyString = "이것은 내용입니다.";

        //Multipart Annotation을 이용하는경우, 한글을 업로드하면 앞뒤로 따옴표가 붙는다.
        //따라서 RequestBody 객체를 통해 plain text임을 명기시켜야한다.
        RequestBody titlePlain = RequestBody.create(MediaType.parse("text/plain"), titleString);
        MultipartBody.Part bodyPlain = MultipartBody.Part.createFormData("body", String.valueOf(RequestBody.create(MediaType.parse("text/plain"), bodyString)));
        Observable<String> multiPost = service.multiPartPost(titlePlain, bodyPlain);
        multiPost.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e("string", s+"");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void voidRetrofit(){
        //로그아웃 등 response를 반드시 받을 필요가 없을 때에는 Void type으로 Retrofit Call이나 Observable을 호출할 수 있다.
        Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        CRUDService service = retrofit.create(CRUDService.class);
        Observable<Void> logout = service.logoutPost("logout");
        logout.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Void>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Void aVoid) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Toast.makeText(GetActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //리턴 받을 것이 없다거나, 리턴이 필요하지 않은 경우 'Completable'을 사용할 수 있다.
    private void completableRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        CRUDService service = retrofit.create(CRUDService.class);
        Completable comLogout = service.logout("Completable Logout!");
        //Completable을 사용했다면, 일반적인 Observer가 아닌 CompletableObserver를 사용할 수 있다.
        comLogout.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Toast.makeText(GetActivity.this, "완전히 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(GetActivity.this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //만약 Http 200대가 아닌 Error가 발생했을 경우, Json으로 들어오는 ErrorBody를 받고 싶다면 아래와 같이 하면 된다.
    private void errorHandle(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build();
        CRUDService service = retrofit.create(CRUDService.class);
        Call<String> error = service.getError("title");
        error.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    //에러가 났을 경우 Json을 String 타입으로 받아올 수 있다.
                    String errorJson = response.errorBody().string();
                    //Gson은 String 타입인 Json을 Deserialize 해줄 수 있다.
                    Gson gson = new Gson();
                    String errorString = gson.fromJson(errorJson, String.class);
                    Log.e("errorString", errorString+"");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
