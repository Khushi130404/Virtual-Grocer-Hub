package com.example.virtualgrocerhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentOptionActivity extends Activity
{
    TextView tvAmount;
    LinearLayout llDebit, llGpay, llNet, llPhone, llCash;
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

        llDebit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
            }
        });

        llNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
            }
        });

        llPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
            }
        });

        llCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
            }
        });

        llGpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), FinalPaymentActivity.class);
                startActivity(i);
            }
        });
    }
}