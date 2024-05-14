package com.example.virtualgrocerhub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminRegistrationActivity extends Activity
{
    EditText etAdminName, etAdminMail, etAdminAdd,etAdminPhone;
    Button btAdminLogin,btAdminCancel;
    SharedPreferences share;
    String username,address,mail,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

        etAdminName = findViewById(R.id.etAdminName);
        etAdminAdd = findViewById(R.id.etAdminAdd);
        etAdminMail = findViewById(R.id.etAdminMail);
        etAdminPhone = findViewById(R.id.etAdminPhone);
        btAdminLogin = findViewById(R.id.btAdminLogin);
        btAdminCancel = findViewById(R.id.btAdminCancel);

        share = getSharedPreferences("adminReg", Context.MODE_PRIVATE);

        username = share.getString("username","null");
        phone = share.getString("phone","0");
        address = share.getString("address","null");
        mail = share.getString("mail","null");

        if(username.equals("null") && phone.equals("0") && address.equals("null") && mail.equals("null"))
        {
            btAdminLogin.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        phone = etAdminPhone.getText().toString();
                        username = etAdminName.getText().toString();
                        mail = etAdminMail.getText().toString();
                        address = etAdminAdd.getText().toString();

                        share = getSharedPreferences("adminReg",Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = share.edit();

                        edit.putString("username",username);
                        edit.putString("address",address);
                        edit.putString("mail",mail);
                        edit.putString("phone",phone);
                        edit.apply();

                        Toast.makeText(getApplicationContext(),"Successfull Login...!",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(),AdminLoginActivity.class);
                        startActivity(i);
                        finish();

                    }
                    catch (NumberFormatException nfe)
                    {
                        Toast.makeText(getApplicationContext(),"Please enter appropriate contact number...!",Toast.LENGTH_LONG).show();
                    }
                }
            });

            btAdminCancel.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    etAdminName.setText("");
                    etAdminPhone.setText("");
                    etAdminAdd.setText("");
                    etAdminMail.setText("");
                }
            });
        }
        else
        {
            Intent i = new Intent(getApplicationContext(),AdminLoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}