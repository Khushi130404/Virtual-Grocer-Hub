package com.example.virtualgrocerhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentOptionActivity extends Activity
{
    TextView tvAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_option);

        tvAmount = findViewById(R.id.tvAmount);
        tvAmount.setText(tvAmount.getText().toString()+getIntent().getIntExtra("amount",0));
    }
}