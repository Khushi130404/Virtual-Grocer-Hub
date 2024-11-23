package com.example.virtualgrocerhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

public class HomeActivity extends Activity
{
    Button btAdmin,btShopper;
    LinearLayout llHome;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btAdmin = findViewById(R.id.btAdmin);
        btShopper = findViewById(R.id.btShopper);
        llHome = findViewById(R.id.llHome);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.your_custom_color));
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.home_anim);
        llHome.startAnimation(translateAnimation);
        btAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(i);
            }
        });

        btShopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ShopGroceryActivity.class);
                startActivity(i);
            }
        });
    }
}