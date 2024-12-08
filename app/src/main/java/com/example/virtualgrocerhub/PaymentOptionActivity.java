package com.example.virtualgrocerhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class PaymentOptionActivity extends Activity
{
    TextView tvAmount;
    LinearLayout llDebit, llGpay, llNet, llPhone, llCash;
    ImageView imgPayMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_option);

        tvAmount = findViewById(R.id.tvAmount);
        tvAmount.setText(tvAmount.getText().toString()+getIntent().getIntExtra("amount",0));

        llDebit = findViewById(R.id.llDebit);
        llGpay = findViewById(R.id.llGpay);
        llCash = findViewById(R.id.llCash);
        llPhone = findViewById(R.id.llPhone);
        llNet = findViewById(R.id.llNet);
        imgPayMethod = findViewById(R.id.imgPayMethod);

        Glide.with(this)
                .asGif()
                .load(R.raw.pay2)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgPayMethod);

        llDebit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
                finish();
            }
        });

        llNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
                finish();
            }
        });

        llPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
                finish();
            }
        });

        llCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
                finish();
            }
        });

        llGpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}