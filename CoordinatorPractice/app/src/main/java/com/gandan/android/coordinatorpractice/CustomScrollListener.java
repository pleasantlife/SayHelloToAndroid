package com.gandan.android.coordinatorpractice;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CustomScrollListener extends RecyclerView.OnScrollListener {

    ScrollListenerInterface scrollListenerInterface;
    LinearLayoutManager linearLayoutManager;


    public CustomScrollListener(ScrollListenerInterface scrollListenerInterface, LinearLayoutManager linearLayoutManager){
        this.scrollListenerInterface = scrollListenerInterface;
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        scrollListenerInterface.scroll(linearLayoutManager.findFirstCompletelyVisibleItemPosition());
    }
}
