package com.example.virtualgrocerhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeAdminActivity extends Activity
{
    EditText etAdminPass,etAdminUser;
    Button btAdminChange,btAdminCancel;
    SharedPreferences share;
    androidx.cardview.widget.CardView cardChangeAdmin;
    TextView tvChangeAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_admin);

        etAdminUser = findViewById(R.id.etAdminUser);
        etAdminPass = findViewById(R.id.etAdminPass);
        btAdminChange = findViewById(R.id.btChangeAdmin);
        btAdminCancel = findViewById(R.id.btCancelAdmin);
        cardChangeAdmin = findViewById(R.id.cardChangeAdmin);
        tvChangeAdmin = findViewById(R.id.tvChangeAdmin);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.admin_anim);
        cardChangeAdmin.startAnimation(fadeIn);

        Animation textDown = AnimationUtils.loadAnimation(this, R.anim.admin_text_amin);
        tvChangeAdmin.startAnimation(textDown);

        share = getSharedPreferences("admin", Context.MODE_PRIVATE);

        AdminLoginActivity log = new AdminLoginActivity();
        log.user = share.getString("username","admin");
        log.pass = share.getString("pass","admin123");

        btAdminChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                share = getSharedPreferences("admin",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = share.edit();

                edit.putString("username",etAdminUser.getText().toString());
                edit.putString("password",etAdminPass.getText().toString());
                edit.commit();

                Toast.makeText(getApplicationContext(),"Admin is successfully updated...!",Toast.LENGTH_LONG).show();
            }
        });

        btAdminCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                etAdminUser.setText("");
                etAdminPass.setText("");
            }
        });
    }
}