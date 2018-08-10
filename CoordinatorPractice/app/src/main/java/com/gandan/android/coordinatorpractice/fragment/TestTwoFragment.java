package com.gandan.android.coordinatorpractice.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gandan.android.coordinatorpractice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestTwoFragment extends Fragment {


    public TestTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_two, container, false);
    }

}
