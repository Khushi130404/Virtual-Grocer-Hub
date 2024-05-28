package com.example.virtualgrocerhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopGroceryActivity extends Activity
{
    ListView lvGrocery;
    static TextView tvAmount;
    static ShopGroceryAdapter ad;
    static List<Grocery> grocery;
    DatabaseReference dbRef;
    static List<Integer> qty;
    static List<ItemBill> bill;
    boolean visit = false;
    Button btCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_grocery);

        lvGrocery = findViewById(R.id.lvGrocery);
        tvAmount = findViewById(R.id.tvAmount);
        btCheckout = findViewById(R.id.btCheckout);

        grocery = new ArrayList<Grocery>();
        bill = new ArrayList<>();
        qty = new ArrayList<>();
        dbRef = FirebaseDatabase.getInstance().getReference("Grocery");

        dbRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                grocery.clear();
                for(DataSnapshot snap : snapshot.getChildren())
                {
                    Grocery gc = snap.getValue(Grocery.class);
                    grocery.add(gc);
                    if(!visit)
                    {
                        qty.add(0);
                    }
                }
                visit = true;
                if(!grocery.isEmpty())
                {
                    ad = new ShopGroceryAdapter(ShopGroceryActivity.this,R.layout.shop_grocery_adepter,grocery,tvAmount,qty,bill);
                    lvGrocery.setAdapter(ad);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Empty list",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        btCheckout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!tvAmount.getText().toString().equals("0"))
                {
                    Intent i = new Intent(getApplicationContext(), CheckoutActivity.class);
                    i.putExtra("amount",tvAmount.getText().toString());
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No item selected...!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}