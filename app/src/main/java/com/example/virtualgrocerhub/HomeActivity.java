package com.example.virtualgrocerhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity
{
    Button btAdmin,btShopper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btAdmin = findViewById(R.id.btAdmin);
        btShopper = findViewById(R.id.btShopper);

        btAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),AdminLoginActivity.class);
                startActivity(i);
            }
        });

        btShopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ShopperLoginActivity.class);
                startActivity(i);


            }
        });
    }
}