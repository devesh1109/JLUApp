package module.project.androidbraintech.jluapp.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import module.project.androidbraintech.jluapp.R;

public class CampusMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
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

        // Add a marker in Sydney and move the camera
        LatLng mJlu = new LatLng(23.203201, 77.294883);
        LatLng mBtech = new LatLng(23.203334, 77.294511);
        LatLng mHotel = new LatLng(23.203345, 77.295022);
        LatLng mMechLab = new LatLng(23.203044, 77.294975);
        LatLng mBed = new LatLng(23.203010, 77.295287);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.addMarker(new MarkerOptions().position(mBtech).title("School Of Engineering and Technology"));
        mMap.addMarker(new MarkerOptions().position(mHotel).title("School of Hotel Management"));
        mMap.addMarker(new MarkerOptions().position(mMechLab).title("Mechanical Engineering Laboratory"));
        mMap.addMarker(new MarkerOptions().position(mBed).title("School of Education"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mJlu,19));
    }
}
