package com.gandan.android.googlemappractice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapViewFragment extends Fragment {

    MapView fragmentMapView;
    GoogleMap gmap;


    public MapViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_view, container, false);

        fragmentMapView = view.findViewById(R.id.fragmentMapView);

        //아래 두 줄이 MapView를 별도로 생성했을 때 꼭 필요한 부분!
        fragmentMapView.onCreate(savedInstanceState);
        fragmentMapView.onResume();

        fragmentMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title("AHA!");
                markerOptions.position(new LatLng(37.575983, 126.976934));
                gmap.addMarker(markerOptions);

                CameraPosition cameraPosition = new CameraPosition.Builder().zoom(15).bearing(0)
                        .target(new LatLng(37.575983, 126.976934))
                        .tilt(0).build();

                gmap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        MapsInitializer.initialize(getActivity());

        return view;
    }

}
