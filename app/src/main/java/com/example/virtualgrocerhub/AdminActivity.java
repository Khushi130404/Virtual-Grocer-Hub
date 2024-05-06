package com.example.virtualgrocerhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends Activity {

    Button btAdd,btShow,btDelete,btUpdate,btAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btAdd = findViewById(R.id.btAdd);
        btDelete = findViewById(R.id.btDelete);
        btUpdate = findViewById(R.id.btUpdate);
        btShow = findViewById(R.id.btShow);
        btAdmin = findViewById(R.id.btAdmin);

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
                Intent i = new Intent(getApplicationContext(),ChangeAdminActivity.class);
                startActivity(i);
            }
        });
    }
}