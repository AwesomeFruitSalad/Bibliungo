package com.chromicle.collapp.activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.chromicle.collapp.R;
import com.chromicle.collapp.utils.BookUtils;
import com.chromicle.collapp.utils.UserUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;

    DatabaseReference booksDb;

    @BindView(R.id.listBookName)
    TextView listBookName;

    @BindView(R.id.listBookDes)
    TextView listBookDes;

    @BindView(R.id.listBookAmount)
    TextView listBookAmount;

    @BindView(R.id.listBookOwner)
    TextView listBookOwner;

    String bookName, bookDes, bookAmount, bookOwner;
    String ID;
    LatLng loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        ID = getIntent().getStringExtra("ID");

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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        int i = 0;

        addFirebaseInstance();
        booksDb.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            BookUtils poojaValue = postSnapshot.getValue(BookUtils.class);

                            if (poojaValue != null) {

                                bookName = poojaValue.getBookName();
                                bookAmount = poojaValue.getBookAmount();
                                bookOwner = poojaValue.getUser();
                                bookDes = poojaValue.getBookDes();

                                loc = new LatLng(Double.valueOf(poojaValue.getBookLat()), Double.valueOf(poojaValue.getBookLong()));
                                mMap.addMarker(new MarkerOptions().position(loc).title(bookName));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MapsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng arg0)
            {
                listBookName.setText(bookName);
                listBookAmount.setText(bookAmount);
                listBookDes.setText(bookDes);
                listBookOwner.setText(bookOwner);
            }
        });

        loc = new LatLng(13.007,74.792);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }


    private void addFirebaseInstance() {
        UserUtils user = new UserUtils();
        String dbUserName = user.getDbUserName();
        booksDb = FirebaseDatabase.getInstance().getReference(ID);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return true;
    }
}
