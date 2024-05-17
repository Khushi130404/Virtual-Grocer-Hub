package com.example.virtualgrocerhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends Activity
{
    EditText etUser,etPass;
    Button btLogin,btCancel;
    public String user,pass;
    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etPass = findViewById(R.id.etPass);
        etUser = findViewById(R.id.etUser);
        btLogin = findViewById(R.id.btLogin);
        btCancel = findViewById(R.id.btCancel);

        share = getSharedPreferences("admin", Context.MODE_PRIVATE);
        user = share.getString("username","admin");
        pass = share.getString("password","admin123");

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etUser.getText().toString().equals(user))
                {
                    if(etPass.getText().toString().equals(pass))
                    {
                        Intent i = new Intent(getApplicationContext(), AdminRegistrationActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Incorrect Password...!",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Username...!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                etUser.setText("");
                etPass.setText("");
            }
        });
    }
}