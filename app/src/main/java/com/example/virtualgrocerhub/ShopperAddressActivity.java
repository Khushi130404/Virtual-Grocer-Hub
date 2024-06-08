package com.example.virtualgrocerhub;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import androidx.appcompat.app.AppCompatActivity;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class ShopperAddressActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback
{
    RadioButton rbCurret, rbOther;
    EditText etCurrent, etOther;
    Button btProceed;
    String address;
    LocationManager man;
    Location loc;
    Boolean isNet,isGps;
    double latitude = 0, longitude = 0;
    Geocoder geo;
    Address addr;
    //ImageView imgMap;
    private static GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_address);

        rbCurret = findViewById(R.id.rbCurrent);
        rbOther = findViewById(R.id.rbOther);
        etCurrent = findViewById(R.id.etCurrent);
        etOther = findViewById(R.id.etOther);
        btProceed = findViewById(R.id.btProceed);
        //imgMap = findViewById(R.id.imgMap);

        man = (LocationManager) getSystemService(LOCATION_SERVICE);
        isNet = man.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        isGps = man.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isNet || isGps)
        {
            if (isNet)
            {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }

                man.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, (LocationListener) this);

                if (man != null)
                {
                    loc = man.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    if(loc != null)
                    {
                        latitude = loc.getLatitude();
                        longitude = loc.getLongitude();
                    }
                }
            }
            if (isGps)
            {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }

                man.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, (LocationListener) this);

                if (man != null)
                {
                    loc = man.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if(loc != null)
                    {
                        latitude = loc.getLatitude();
                        longitude = loc.getLongitude();
                    }
                }
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Cannot get location...!",Toast.LENGTH_LONG).show();
        }


        try
        {
            geo = new Geocoder(this, Locale.getDefault());
            List<Address> list = geo.getFromLocation(latitude,longitude,1);
            addr = list.get(0);
            etCurrent.setText(addr.getAddressLine(0));
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"err : "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

        rbCurret.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                rbOther.setChecked(false);
                rbCurret.setChecked(true);
            }
        });

        rbOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rbCurret.setChecked(false);
                rbOther.setChecked(true);
            }
        });

        btProceed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(rbCurret.isChecked() || rbOther.isChecked())
                {
                    if(rbCurret.isChecked())
                    {

                        address = etCurrent.getText().toString();
                        Intent i = new Intent(getApplicationContext(),PaymentOptionActivity.class);
                        int amount = getIntent().getIntExtra("amount",0);
                        i.putExtra("amount",amount);
                        i.putExtra("address",address);
                        startActivity(i);
                    }
                    else
                    {
                        address = etOther.getText().toString();
                        if(address.equals(""))
                        {
                            Toast.makeText(getApplicationContext(),"Please enter address...!",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent i = new Intent(getApplicationContext(),PaymentOptionActivity.class);
                            int amount = getIntent().getIntExtra("amount",0);
                            i.putExtra("amount",amount);
                            i.putExtra("address",address);
                            startActivity(i);
                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please select the radio button", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null)
        {
            mapFragment.getMapAsync(this);
        }
    }

    public static void finishActivity()
    {}

    @Override
    public void onLocationChanged(@NonNull Location location)
    {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Toast.makeText(this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_LONG).show();

        if (mMap != null)
        {
            LatLng newLocation = new LatLng(latitude, longitude);
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(newLocation).title("Current Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 1f)); // 15f is the zoom level
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap)
    {
        mMap = googleMap;
        LatLng location = new LatLng(latitude, longitude);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 20f));
        //Toast.makeText(getApplicationContext(),"Map : "+latitude,Toast.LENGTH_LONG).show();
    }
}