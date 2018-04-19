package com.gandan.android.daummappractice;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout daumMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mapView = new MapView(this);
        daumMapView = findViewById(R.id.daumMapView);
        daumMapView.addView(mapView);
        mapView.setMapCenterPoint(MapPoint.mapPointWithCONGCoord(37.497082, 126.028704), true);
    }
}
