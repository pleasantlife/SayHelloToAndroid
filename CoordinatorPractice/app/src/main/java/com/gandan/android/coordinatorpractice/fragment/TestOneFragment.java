package com.gandan.android.coordinatorpractice.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gandan.android.coordinatorpractice.CustomScrollListener;
import com.gandan.android.coordinatorpractice.R;
import com.gandan.android.coordinatorpractice.ScrollListenerInterface;
import com.gandan.android.coordinatorpractice.adapter.TestRecyclerView;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestOneFragment extends Fragment {

    ScrollListenerInterface scrollListenerInterface;

    RecyclerView oneRecycler;
    int scrollX, scrollY, tempLastVisible;
    LinearLayoutManager linearLayoutManager;

    public TestOneFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View oneFragmentView = inflater.inflate(R.layout.fragment_test_one, container, false);
        oneRecycler = oneFragmentView.findViewById(R.id.oneRecycler);
        oneRecycler.setAdapter(new TestRecyclerView());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        oneRecycler.setLayoutManager(linearLayoutManager);
        tempLastVisible = linearLayoutManager.findLastVisibleItemPosition();


        scrollListenerInterface = (ScrollListenerInterface) getActivity();

        oneRecycler.addOnScrollListener(new CustomScrollListener(scrollListenerInterface, linearLayoutManager));


        return oneFragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //scrollListenerInterface = (ScrollListenerInterface) context;

    }

}
