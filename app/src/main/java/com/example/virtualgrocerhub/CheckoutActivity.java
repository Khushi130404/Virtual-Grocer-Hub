package com.example.virtualgrocerhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CheckoutActivity extends Activity
{
    ListView lvItem;
    TextView tvDate, tvTime, tvAmount, tvTax, tvFinalBill;
    LocalDate today;
    LocalTime time;
    CheckoutAdapter cadd;
    ShopGroceryActivity shop;
    Button btPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        lvItem = findViewById(R.id.lvItem);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvAmount = findViewById(R.id.tvAmount);
        tvTax = findViewById(R.id.tvTax);
        tvFinalBill = findViewById(R.id.tvFinalBill);
        btPay = findViewById(R.id.btPay);

        shop = new ShopGroceryActivity();

        today = LocalDate.now();
        time = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        tvTime.setText(tvTime.getText().toString()+time.format(formatter));
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tvDate.setText(tvDate.getText().toString()+today.format(formatter));

        int amount = Integer.parseInt(getIntent().getStringExtra("amount"));
        tvAmount.setText(tvAmount.getText().toString()+amount);
        tvTax.setText(tvTax.getText().toString()+(int)(0.18*amount));
        tvFinalBill.setText(tvFinalBill.getText().toString()+(int)(1.18*amount));
        cadd = new CheckoutAdapter(getApplicationContext(),R.layout.checkout_adapter,shop.bill);
        lvItem.setAdapter(cadd);


        btPay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),PaymentOptionActivity.class);
                startActivity(i);
            }
        });
    }
}