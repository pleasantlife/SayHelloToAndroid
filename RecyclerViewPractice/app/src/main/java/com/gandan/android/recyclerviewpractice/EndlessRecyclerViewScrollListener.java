package com.gandan.android.recyclerviewpractice;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by XPS on 2018-02-08.
 */


/**
 *  Pagenation을 하기 위한 EndlessRecyclerViewScrollListener를 만들었다.
 *  이 클래스는 리사이클러의 스크롤뷰 상태를 감지하는 OnScrollListener를 상속받았다.
 */
public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    //한 페이지 당 보여질 아이템 수 설정.
    private int visibleThreshold = 5;
    //시작 페이지 설정.
    private int currentPage = 0;
    //이전 아이템의 갯수 설정.
    private int previousTotalItemCount = 0;
    //로딩중인지 확인
    private boolean loading = true;
    //페이지 시작.
    private int startingPageIndex = 0;

    RecyclerView.LayoutManager layoutManager;

    public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }
    //마지막에 보인 아이템의 포지션 값 얻어오기
    public int getLastVisibleItem(int[] lastVisibleItemPositions){
        int maxSize = 0;
        for (int i=0; i < lastVisibleItemPositions.length; i++){
            if(i == 0){
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize){
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    //리사이클러뷰가 스크롤이 될 떄 이를 감지하는 함수.
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = layoutManager.getItemCount();

        //현재는 리니어레이아웃만 사용했으므로, 리니어레이아웃에 대한 if문만 있다.
        if(layoutManager instanceof LinearLayoutManager){
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }

        if(totalItemCount < previousTotalItemCount){
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if(totalItemCount == 0){
                this.loading = true;
            }
        }

        if(loading && (totalItemCount > previousTotalItemCount)){
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if(!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, recyclerView);
        }
    }

    //새로고침 시 가장 처음의 상태로 되돌린다.
    public void resetState(){
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }


    //아이템을 더 부를 경우 itemsCount와 page를 설정하여 아래 메소드에서 구현해준다.
    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

}
