package muaz_limkok.fashionclub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapClass extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.addMarker(new MarkerOptions()
                .position(new LatLng(MainActivity.lati, MainActivity.longi))
                .title("You are here"));
        LatLng userLatLng = new LatLng(MainActivity.lati,MainActivity.longi);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(2.9399, 101.6627))
                .title("Fashion Club Event"));
    }
}
