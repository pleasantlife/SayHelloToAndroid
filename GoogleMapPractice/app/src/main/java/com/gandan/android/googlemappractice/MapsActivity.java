package com.gandan.android.googlemappractice;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

/**
 *      안드로이드 스튜디오에서 새 액티비티를 생성할 때 Google Maps Activity를 선택하면 자동으로 구글맵 액티비티를 생성해준다.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ClusterManager<ClusterItem> clusterManager;
    private MarkerManager markerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //안드로이드에 기본적으로 내장된 '맵프래그먼트'를 이용한다.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * 생성된 지도를 한 번 조작하는 메소드 입니다.(Manipulates the map once available.)
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        markerManager = new MarkerManager(mMap);
        clusterManager = new ClusterManager(this, mMap, markerManager);


        //경도, 위도 순으로 파라미터를 입력한 LatLng 객체를 생성하여 지도의 '최초' 중심점을 잡는다.
        LatLng meritzGangnam = new LatLng(37.497082, 127.028704);

        //마커(핀)를 표시할 때는 map.addMarker메소드를 이용한다.
        MarkerOptions meritzMarker = new MarkerOptions();
        meritzMarker.title("Mertiz Gangnam Tower");
        meritzMarker.position(meritzGangnam);

        MarkerOptions cgvMarker = new MarkerOptions();
        cgvMarker.title("CGV Gangnam");
        cgvMarker.position(new LatLng(37.5005811, 127.026329));

        MarkerOptions gangnamStationMarker = new MarkerOptions();
        gangnamStationMarker.title("Gangnam Station");
        gangnamStationMarker.position(new LatLng(37.497909, 127.027627));

        mMap.addMarker(meritzMarker);
        mMap.addMarker(cgvMarker);
        mMap.addMarker(gangnamStationMarker);


        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);

        addItem();


        //setMapType 메소드를 통해 위성지도, 하이브리드, 일반지도 모드로 설정할 수 있다.
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //카메라가 최초로 비추는 곳에 대한 각도, 위치, 줌레벨 설정등을 할 수 있다.
        CameraPosition cameraPosition = CameraPosition.builder().zoom(15).target(meritzGangnam).bearing(0)
                .tilt(0).build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(meritzGangnam));

        setOverLayImage();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e("latitude", latLng.latitude+"");
                Log.e("longitude", latLng.longitude+"");
            }
        });

        /*mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getPosition().latitude == 37.497082){
                    Toast.makeText(MapsActivity.this, "강남 메리츠 마커 클릭", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });*/
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void addItem(){
        double lat = 37.497909;
        double lng = 127.027627;

        for (int i =0; i < 10; i++){
            double offset = 1 / 100d;
            lat = lat + offset;
            lng = lng + offset;
            MyItem item = new MyItem(lat, lng);
            clusterManager.addItem(item);
        }
    }

    private void setOverLayImage(){
        LatLngBounds latLngBounds = new LatLngBounds(
                //남서쪽
                new LatLng(37.575748292159425, 126.97426971048117),
                //북동쪽
                new LatLng(37.58316795383823, 126.97923883795738)
        );

        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions().
                image(BitmapDescriptorFactory.fromResource(R.drawable.gyeongbokgoong))
                .anchor(0,1)
                .positionFromBounds(latLngBounds);

        mMap.addGroundOverlay(groundOverlayOptions);
    }

    class MyItem implements ClusterItem {

        private double lat;
        private double lng;
        private LatLng latLng;
        private String title;
        private String snippet;

        public MyItem(double lat, double lng, String title, String snippet){

        }

        public MyItem(double lat, double lng){
            latLng = new LatLng(lat, lng);
        }

        @Override
        public LatLng getPosition() {
            return latLng;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getSnippet() {
            return snippet;
        }
    }
}
