package com.gandan.android.fragmentpractice;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerTwoFragment extends Fragment {

    FragmentRecyclerAdapter fragmentRecyclerAdapter;
    RecyclerView recyclerTwo;
    List<String> fragmentList = new ArrayList<>();

    public ViewPagerTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View twoFragmentView = inflater.inflate(R.layout.fragment_view_pager_two, container, false);
        recyclerTwo = twoFragmentView.findViewById(R.id.recyclerTwo);
        //recyclerTwo.setHasFixedSize(true);
        fragmentRecyclerAdapter = new FragmentRecyclerAdapter(getActivity(), fragmentList);
        recyclerTwo.setAdapter(fragmentRecyclerAdapter);
        recyclerTwo.setLayoutManager(new LinearLayoutManager(container.getContext()));
        return twoFragmentView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList.add("야호!");
        fragmentList.add("이 멍청아!");
        fragmentList.add("리턴타입을");
        fragmentList.add("왜 안보니");
    }


}
