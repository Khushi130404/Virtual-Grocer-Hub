package com.example.virtualgrocerhub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class FinalPaymentActivity extends Activity
{
    DatabaseReference dbRef;
    ShopGroceryActivity shop;
    ImageView imgPayGif;
    Button btShopping;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_payment);

        imgPayGif = findViewById(R.id.imgPayGif);
        btShopping = findViewById(R.id.btShopping);

        Glide.with(this)
                .asGif()
                .load(R.raw.pay1)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgPayGif);

        dbRef = FirebaseDatabase.getInstance().getReference("Grocery");
        shop = new ShopGroceryActivity();

        for(ItemBill item : shop.bill)
        {
            DatabaseReference dbChild = dbRef.child(item.getGId());

            dbChild.addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    if(snapshot.exists())
                    {
                        Grocery gc = snapshot.getValue(Grocery.class);

                        int qty = gc.getQty()- item.getQty();

                        if(qty!=0)
                        {
                            dbChild.child("qty").setValue(qty);
                        }
                        else
                        {
                            snapshot.getRef().removeValue();
                            StorageReference storeRef = FirebaseStorage.getInstance().getReferenceFromUrl(gc.getImage());
                            storeRef.delete();
                            shop.ad.notifyDataSetChanged();
                        }

                        shop.qty = new ArrayList<>();
                        shop.bill = new ArrayList<>();
                        shop.visit = false;
                        shop.tvAmount.setText("0");

                        CheckoutActivity.finishActivity();
                        ShopperAddressActivity.finishActivity();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {
                    Toast.makeText(getApplicationContext(), "Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            btShopping.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    finish();
                }
            });
        }
    }
}