package com.gandan.android.googlemappractice;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 *      안드로이드 스튜디오에서 새 액티비티를 생성할 때 Google Maps Activity를 선택하면 자동으로 구글맵 액티비티를 생성해준다.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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


        //경도, 위도 순으로 파라미터를 입력한 LatLng 객체를 생성하여 지도의 '최초' 중심점을 잡는다.
        LatLng meritzGangnam = new LatLng(37.497082, 127.028704);

        //마커(핀)를 표시할 때는 map.addMarker메소드를 이용한다.
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("Mertiz Gangnam Tower");
        markerOptions.position(meritzGangnam);

        mMap.addMarker(markerOptions);
        //setMapType 메소드를 통해 위성지도, 하이브리드, 일반지도 모드로 설정할 수 있다.
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //카메라가 최초로 비추는 곳에 대한 각도, 위치, 줌레벨 설정등을 할 수 있다.
        CameraPosition cameraPosition = CameraPosition.builder().zoom(15).target(meritzGangnam).bearing(0)
                .tilt(0).build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(meritzGangnam));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "latitude"+latLng.latitude+" "+"longitude"+latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
