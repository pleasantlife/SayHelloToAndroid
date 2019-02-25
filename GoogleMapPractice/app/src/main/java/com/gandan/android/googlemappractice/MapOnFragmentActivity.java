package com.gandan.android.googlemappractice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MapOnFragmentActivity extends AppCompatActivity {

    LinearLayout mapViewLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_on_fragment);

        mapViewLinear = findViewById(R.id.mapViewLinear);

        Fragment fragment = new MapViewFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mapViewLinear, fragment);
        fragmentTransaction.commit();

    }
}
