package hama.alsaygh.kw.delivery.view.map;

import static android.os.Build.VERSION_CODES.M;

import android.Manifest;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hama.alsaygh.kw.delivery.R;
import hama.alsaygh.kw.delivery.databinding.ActivityMapBinding;
import hama.alsaygh.kw.delivery.utils.AppConstants;
import hama.alsaygh.kw.delivery.utils.CheckAndRequestPermission;
import hama.alsaygh.kw.delivery.utils.GPSTracker;
import hama.alsaygh.kw.delivery.utils.MapUtils;
import hama.alsaygh.kw.delivery.view.base.BaseActivity;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {

    ActivityMapBinding binding;
    MapsViewModel model;
    String address;

    private LatLng currentLatLng;
    private LatLng otherLatLng;
    private GoogleMap mMap;
    private GroundOverlay myMarker;
    private GroundOverlay otherMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new MapsViewModel(this);
        binding.setModel(model);

        if (getIntent() != null)
            address = getIntent().getStringExtra(AppConstants.ADDRESS);

        MySupportMapFragment mapFragment = (MySupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null)
            mapFragment.getMapAsync(this);

        if (mapFragment != null)
            mapFragment.setListener(() -> {
            });
        binding.imageViewBack.setOnClickListener(v -> {
            onBackPressed();
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        MapUtils.getInstance().setMapSetting(mMap);

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                if (Build.VERSION.SDK_INT >= M) {
                    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
                    if (!CheckAndRequestPermission.hasPermissions(MapActivity.this, permissions)) {
                        CheckAndRequestPermission.requestLocation(MapActivity.this);
                    } else {
                        getMyLocation();
                    }
                } else {
                    getMyLocation();
                }

            }
        });
    }

    private void getMyLocation() {
        final GPSTracker tracker = new GPSTracker(this);
        tracker.setMyLocationListener(new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (mMap != null) {
                    currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    tracker.stopUsingGPS();

                    if (myMarker != null)
                        myMarker.remove();
                    myMarker = mMap.addGroundOverlay(new GroundOverlayOptions()
                            .image(BitmapDescriptorFactory.fromBitmap(MapUtils.getInstance().getBitmapFromVectorDrawable(MapActivity.this, R.drawable.ic_delivery))).anchor(0, 1)
                            .position(currentLatLng, 57f, 57f));

                    getLocationFromAddress();
                    animateMap();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
        if (tracker.canGetLocation()) {

            double latitude = tracker.getLatitude();
            double longitude = tracker.getLongitude();

            if (latitude != 0 && longitude != 0) {
                if (mMap != null) {

                    currentLatLng = new LatLng(latitude, longitude);
                    if (myMarker != null)
                        myMarker.remove();
                    myMarker = mMap.addGroundOverlay(new GroundOverlayOptions()
                            .image(BitmapDescriptorFactory.fromBitmap(MapUtils.getInstance().getBitmapFromVectorDrawable(MapActivity.this, R.drawable.ic_delivery))).anchor(0, 1)
                            .position(currentLatLng, 57f, 57f));
                    getLocationFromAddress();
                    animateMap();
                }
                tracker.stopUsingGPS();
            }
        } else
            tracker.showSettingsAlert(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && !CheckAndRequestPermission.isFoundPermissionDenied(grantResults)) {
            getMyLocation();
        }
    }

    private void getLocationFromAddress() {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(address, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!list.isEmpty()) {
            otherLatLng = new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());
            if (otherMarker != null)
                otherMarker.remove();
            otherMarker = mMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(BitmapDescriptorFactory.fromBitmap(MapUtils.getInstance().getBitmapFromVectorDrawable(MapActivity.this, R.drawable.ic_other_delivery))).anchor(0, 1)
                    .position(otherLatLng, 70f, 70f));


        }
    }

    public void animateMap() {
        try {
            if (mMap != null) {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                if (myMarker != null)
                    builder.include(myMarker.getPosition());
                if (otherMarker != null)
                    builder.include(otherMarker.getPosition());

                LatLngBounds bounds = builder.build();

                if (myMarker != null && otherMarker != null) {
                    int padding = 50; // offset from edges of the map in pixels

                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                    mMap.animateCamera(cu);
                } else if (myMarker != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, MapUtils.ZOOM_MY_LOCATION));
                } else if (otherMarker != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(otherLatLng, MapUtils.ZOOM_MY_LOCATION));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}