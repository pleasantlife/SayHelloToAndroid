package com.gandan.android.daummappractice;

import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.daum.android.map.MapViewController;
import net.daum.android.map.MapViewEventListener;
import net.daum.android.map.MapViewTouchEventListener;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    LinearLayout daumMapView;
    MapView initMapView;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.gandan.android.daummappractice", PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        dialog = new AlertDialog.Builder(this).setTitle("안녕?").create();


        daumMapView = findViewById(R.id.daumMapView);
        initMapView = findViewById(R.id.mapView);
        initMapView.setHDMapTileEnabled(true);
        initMapView.setMapType(MapView.MapType.Hybrid);
        initMapView.setShowCurrentLocationMarker(true);
        initMapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.4982086, 127.02776360000007), 1, true);

        dialog.show();

        final MapView.MapViewEventListener listener = new MapView.MapViewEventListener() {
            @Override
            public void onMapViewInitialized(MapView mapView) {

            }

            @Override
            public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
                Log.e("moved", "centerPoint");
            }

            @Override
            public void onMapViewZoomLevelChanged(MapView mapView, int i) {

            }

            @Override
            public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
                Toast.makeText(MainActivity.this, "Single Tap!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

            }

            @Override
            public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

            }
        };


        MapViewEventListener eventListener = new MapViewEventListener() {
            //이 이후에 net.daum.android.map.MapView.MapView를 붙여야 리스너가 제대로 동작함.
            @Override
            public void onLoadMapView() {
                Log.e("Load", "Complete?");
                initMapView.setMapViewEventListener(listener);
            }
        };



        initMapView.setMapViewEventListener(eventListener);
        //mapViewEventListener.onMapViewCenterPointMoved(initMapView, initMapView.getMapCenterPoint());
        //mapViewEventListener.onMapViewSingleTapped(initMapView, initMapView.getMapCenterPoint());
    }
}
