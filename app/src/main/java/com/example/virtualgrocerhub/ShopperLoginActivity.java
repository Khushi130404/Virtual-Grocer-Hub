package com.example.virtualgrocerhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShopperLoginActivity extends Activity {

    EditText etShopper,etShopperPhone,etShopperMail,etShopperAdd;
    Button btShopperLogin,btShopperCancel;

    SharedPreferences share;
    String username,mail,address;
    Long phone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_login);

        etShopper = findViewById(R.id.etShopper);
        etShopperAdd = findViewById(R.id.etShopperAdd);
        etShopperMail = findViewById(R.id.etShopperMail);
        etShopperPhone = findViewById(R.id.etShopperPhone);
        btShopperLogin = findViewById(R.id.btShopperLogin);
        btShopperCancel = findViewById(R.id.btShopperCancel);

        share = getSharedPreferences("shopper", Context.MODE_PRIVATE);

        username = share.getString("username","null");
        phone = share.getLong("phone",0);
        address = share.getString("address","null");
        mail = share.getString("mail","null");

        btShopperLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    phone = Long.parseLong(etShopperPhone.getText().toString());
                    username = etShopper.getText().toString();
                    mail = etShopperMail.getText().toString();
                    address = etShopperAdd.getText().toString();

                    share = getSharedPreferences("shopper",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = share.edit();

                    edit.putString("username",username);
                    edit.putString("address",address);
                    edit.putString("mail",mail);
                    edit.putLong("phone",phone);

                    Toast.makeText(getApplicationContext(),"Successfull Login...!",Toast.LENGTH_LONG);
                }
                catch (NumberFormatException nfe)
                {
                    Toast.makeText(getApplicationContext(),"Please enter appropriate contact number...!",Toast.LENGTH_LONG).show();
                }
            }
        });

        btShopperCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                etShopper.setText("");
                etShopperPhone.setText("");
                etShopperAdd.setText("");
                etShopperMail.setText("");
            }
        });
    }
}