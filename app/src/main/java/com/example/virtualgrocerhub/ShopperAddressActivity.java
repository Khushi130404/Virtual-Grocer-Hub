package com.example.virtualgrocerhub;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.Locale;

public class ShopperAddressActivity extends AppCompatActivity implements LocationListener
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_address);

        rbCurret = findViewById(R.id.rbCurrent);
        rbOther = findViewById(R.id.rbOther);
        etCurrent = findViewById(R.id.etCurrent);
        etOther = findViewById(R.id.etOther);
        btProceed = findViewById(R.id.btProceed);

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

    }

    @Override
    public void onLocationChanged(@NonNull Location location)
    {}
}