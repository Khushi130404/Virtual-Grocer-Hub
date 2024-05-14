package com.example.virtualgrocerhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class AdminActivity extends Activity {

    Button btAdd,btShow,btDelete,btAdmin;
    Random random;
    String phone;
    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btAdd = findViewById(R.id.btAdd);
        btDelete = findViewById(R.id.btDelete);
        btShow = findViewById(R.id.btShow);
        btAdmin = findViewById(R.id.btAdmin);

        random = new Random();


        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),AddGroceryActivity.class);
                startActivity(i);
            }
        });

        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),ShowGroceryActivity.class);
                startActivity(i);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),DeleteUpdateAdminActivity.class);
                startActivity(i);
            }
        });

        btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                share = getSharedPreferences("adminReg", Context.MODE_PRIVATE);

                phone = share.getString("phone","0");
                int otp = random.nextInt(9999);
                Intent i = new Intent(getApplicationContext(),AdminOTPActivity.class);
                i.putExtra("otp",otp);
                SmsManager sms=SmsManager.getDefault();
                String message = "Your OTP for Virtual GroccerHub aplication is "+otp;
                sms.sendTextMessage(phone, null, message, null,null);
                startActivity(i);
            }
        });
    }
}