package com.gandan.android.collapsetoolbarpractice;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *  CollapseToolbarLayout은 프로그래밍코드가 아닌 xml코드로 세팅할 수 있다.
 */

public class MainActivity extends AppCompatActivity {

    AppBarLayout appBar;
    NestedScrollView scrollTest;
    LinearLayout leftSidebar;
    RecyclerView recyclerTest;
    ArrayList<SignData> signDataList = new ArrayList<>();

    RelativeLayout.LayoutParams lp;
    GridLayoutManager gridLayoutManager;
    TextView dummyHeaderTxtView;
    SignAdapter signAdapter;

    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //맨 위로 올라간 듯한 효과를 주고 싶을 때, 또는 특정 방향으로의 스크롤 감지 시
        //아래의 코드로 간편하게 appBar를 띄울 수 있다...
        //appBar.setExpanded(true);
        //appBar.setExpanded(false);

        setSignData();

        appBar = findViewById(R.id.appbar);
        scrollTest = findViewById(R.id.scrollTest);

        leftSidebar = findViewById(R.id.leftSidebar);
        lp = (RelativeLayout.LayoutParams) leftSidebar.getLayoutParams();
        recyclerTest = findViewById(R.id.recyclerTest);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerTest.setLayoutManager(gridLayoutManager);
        signAdapter = new SignAdapter(this, signDataList);
        recyclerTest.setAdapter(signAdapter);

        dummyHeaderTxtView = findViewById(R.id.dummyHeaderTxtView);


        setAppbarAnimate();

    }

    public void setSignData() {

        //임시로 데이터 넣어둠.
        final String[] imageforRecycler =
                {"https://cdn.pixabay.com/photo/2017/10/10/04/55/times-square-2835995__480.jpg",
                        "https://cdn.pixabay.com/photo/2014/12/29/15/51/times-square-582930__480.jpg",
                        "https://cdn.pixabay.com/photo/2014/12/03/16/36/billboard-555597__480.jpg",
                        "https://cdn.pixabay.com/photo/2017/10/10/04/55/times-square-2835995__480.jpg",
                        "https://cdn.pixabay.com/photo/2014/12/29/15/51/times-square-582930__480.jpg",
                        "https://cdn.pixabay.com/photo/2014/12/03/16/36/billboard-555597__480.jpg"};

        final String[] textforRecycler = {"1번째 간판", "2번째 간판", "3번째 간판", "4번째 간판", "5번째 간판", "6번째 간판"};
        final String[] locationforRecycler = {"1번째 집", "2번째 집", "3번째 집", "4번째 집", "5번째 집", "6번째 집"};

        for(int i=0; i <=4; i++) {
            while (index <= 5) {
                SignData signData = new SignData();
                signData.setSignImage(imageforRecycler[index]);
                signData.setSignName(textforRecycler[index]);
                signData.setSignLocation(locationforRecycler[index]);
                signDataList.add(signData);
                index++;
                //Log.e("데이터 아이들어가니?", signData.getSignImage());
            }
            return;
        }
        signAdapter.notifyDataSetChanged();
    }

    private void setAppbarAnimate(){
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int displayHeight = displayMetrics.heightPixels;

                int initMargin = 42 * (getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);

                boolean isShow = true;
                int scrollRange = -1;

                if(gridLayoutManager.getHeight() + dummyHeaderTxtView.getMeasuredHeight() >= displayHeight
                        - (152 * ((float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT))){

                    float ratio = ((float) verticalOffset / (float) appBarLayout.getTotalScrollRange()) * 180;
                    lp.topMargin = initMargin - Math.abs((int) (-ratio));
                    leftSidebar.setLayoutParams(lp);
                } else {
                    scrollTest.setNestedScrollingEnabled(false);
                }
            }
        });
    }
}
