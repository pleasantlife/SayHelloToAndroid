package com.gandan.android.daummappractice;

import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.android.map.MapViewController;
import net.daum.android.map.MapViewEventListener;
import net.daum.android.map.MapViewTouchEventListener;
import net.daum.android.map.coord.MapCoord;
import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements MapView.POIItemEventListener, View.OnClickListener {

    LinearLayout daumMapView, zoomIn, zoomOut;
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
        dialog = new AlertDialog.Builder(this).setTitle("안녕?").setMessage("로딩중이야").create();


        daumMapView = findViewById(R.id.daumMapView);
        initMapView = findViewById(R.id.initMapView);
        initMapView.setHDMapTileEnabled(true);
        initMapView.setMapType(MapView.MapType.Hybrid);
        initMapView.setShowCurrentLocationMarker(true);
        zoomIn = findViewById(R.id.zoomIn);
        zoomIn.setOnClickListener(this);
        zoomOut = findViewById(R.id.zoomOut);
        zoomOut.setOnClickListener(this);
        initMapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.4982086, 127.02776360000007), 1, true);

        MapPoint point = MapPoint.mapPointWithGeoCoord(37.4982086, 127.02776360000007);

        final MapPOIItem mapPOIItem = new MapPOIItem();
        mapPOIItem.setTag(0);
        mapPOIItem.setItemName("최초 시작 지점");
        mapPOIItem.setMapPoint(point);
        mapPOIItem.setCustomImageAutoscale(true);
        mapPOIItem.setCustomSelectedImageResourceId(R.drawable.map_marker_icon);
        mapPOIItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        /*mapPOIItem.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);*/
        mapPOIItem.setCustomImageResourceId(R.drawable.map_marker_icon);
        mapPOIItem.setShowCalloutBalloonOnTouch(true);


        MapPOIItem[] items = new MapPOIItem[3];
        MapPOIItem itemOne = new MapPOIItem();
        itemOne.setItemName("광화문");
        MapPoint pointOne = MapPoint.mapPointWithGeoCoord(37.5759369,126.9768157);
        itemOne.setMapPoint(pointOne);
        itemOne.setMarkerType(MapPOIItem.MarkerType.BluePin);
        itemOne.setShowCalloutBalloonOnTouch(true);
        items[0] = itemOne;

        MapPOIItem itemTwo = new MapPOIItem();
        itemTwo.setItemName("신논현역");
        MapPoint pointTwo = MapPoint.mapPointWithGeoCoord(37.504724,127.0248328);
        itemTwo.setMapPoint(pointTwo);
        itemTwo.setMarkerType(MapPOIItem.MarkerType.BluePin);
        itemTwo.setShowCalloutBalloonOnTouch(true);
        items[1] = itemTwo;

        MapPOIItem itemThree = new MapPOIItem();
        itemThree.setItemName("63빌딩");
        MapPoint pointThree = MapPoint.mapPointWithGeoCoord(37.5193776,126.9390509);
        itemThree.setMapPoint(pointThree);
        itemThree.setMarkerType(MapPOIItem.MarkerType.BluePin);
        itemThree.setShowCalloutBalloonOnTouch(true);
        items[2] = itemThree;


        initMapView.addPOIItem(mapPOIItem);
        initMapView.addPOIItems(items);
        initMapView.setPOIItemEventListener(this);


        dialog.show();



        final MapView.MapViewEventListener listener = new MapView.MapViewEventListener() {
            @Override
            public void onMapViewInitialized(MapView mapView) {
                //setMarker();
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
                Log.e("latitude", mapPoint.getMapPointGeoCoord().latitude+"");
                Log.e("longitude", mapPoint.getMapPointGeoCoord().longitude+"");
            }

            @Override
            public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
                getAddress(mapPoint);
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
                Log.e("EventListener", "Attached");
                initMapView.setMapViewEventListener(listener);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };

        initMapView.setMapViewEventListener(eventListener);
    }

    //지도점(MapPoint)의 주소를 가져옴.
    private void getAddress(MapPoint mapPoint){
        MapReverseGeoCoder geoCoder = new MapReverseGeoCoder(getString(R.string.api_key), mapPoint,
                new MapReverseGeoCoder.ReverseGeoCodingResultListener() {
                    @Override
                    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
                        Toast.makeText(MainActivity.this, s+"", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
                        Log.e("Err", "GeoCoding");
                    }
                }, this);
        geoCoder.startFindingAddress();
    }

    //Implement해야 함. onCreate단에서 하면 동작하지 않음..
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        mapView.setMapCenterPoint(mapPOIItem.getMapPoint(), true);
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        Toast.makeText(this, "Clicked " + mapPOIItem.getItemName() + " Callout Balloon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.zoomIn:
                initMapView.setZoomLevel(initMapView.getZoomLevel()-1, true);
                break;
            case R.id.zoomOut:
                initMapView.setZoomLevel(initMapView.getZoomLevel()+1, true);
                break;
        }
    }
}
