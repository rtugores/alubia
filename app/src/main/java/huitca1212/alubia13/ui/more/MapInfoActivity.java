package huitca1212.alubia13.ui.more;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import huitca1212.alubia13.R;

public class MapInfoActivity extends AppCompatActivity implements OnMapReadyCallback {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_info);
		SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap map) {
		LatLng lagunaDeNegrillos = new LatLng(42.2397558, -5.6599392);
		map.addMarker(new MarkerOptions().position(lagunaDeNegrillos).title(getString(R.string.home_title)));
		map.moveCamera(CameraUpdateFactory.newLatLng(lagunaDeNegrillos));
	}
}