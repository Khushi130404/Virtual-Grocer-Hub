package com.example.virtualgrocerhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowGroceryActivity extends Activity {

    ListView lvGrocery;
    List<Grocery> gc;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grocery);

        lvGrocery = findViewById(R.id.lvGrocery);
        gc = new ArrayList<Grocery>();
        dbRef = FirebaseDatabase.getInstance().getReference("Grocery");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snap : snapshot.getChildren())
                {
                    Grocery g = snap.getValue(Grocery.class);
                    gc.add(g);
                }

                if(!gc.isEmpty())
                {
                    try
                    {
                        CustomizeAdapter cad = new CustomizeAdapter(ShowGroceryActivity.this,R.layout.grocery_admin,gc);
                        lvGrocery.setAdapter(cad);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"GC empty",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(getApplicationContext(),"Bye",Toast.LENGTH_SHORT).show();
            }
        });
    }
}