package com.gandan.android.navermappractice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;

import static com.nhn.android.maps.NMapView.VIEW_MODE_HYBRID;

public class MainActivity extends NMapActivity {

    NMapView naverMapView;
    NMapController naverMapController;
    NGeoPoint naverGeoPoint;
    NMapLocationManager naverMapLocationManager;
    public static final String NAVER_CLIENT_ID = "37liq2CcjWXx2KCgSm9b";
    String permissions[] = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionCheck();
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        naverMapView = findViewById(R.id.naverMapView);
        naverMapView.setClientId(NAVER_CLIENT_ID);
        naverMapView.setAutoRotateEnabled(true, true);
        naverMapView.setBuiltInZoomControls(true, null);
        naverGeoPoint = new NGeoPoint(127.028704, 37.497082);
        naverMapController = naverMapView.getMapController();
        naverMapController.setMapViewMode(VIEW_MODE_HYBRID);
        naverMapController.setMapCenter(naverGeoPoint, 16);
        naverMapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v instanceof NMapView) {
                    naverMapView.setBuiltInZoomControls(true, null);
                    //naverGeoPoint.set(lat, lng);
                    //naverMapController.setMapCenter(naverGeoPoint, 16);
                    return true;
                }
                return false;
            }
        });

    }


    private void permissionCheck(){

        if(ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, 161);
            }
        }
    }
}
