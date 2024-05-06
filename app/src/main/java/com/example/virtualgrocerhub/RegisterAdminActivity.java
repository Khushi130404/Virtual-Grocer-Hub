package com.example.virtualgrocerhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterAdminActivity extends Activity
{
    EditText etAdminName, etAdminMail, etAdminAdd,etAdminPhone;
    Button btAdminLogin,btAdminCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        etAdminName = findViewById(R.id.etAdminName);
        etAdminAdd = findViewById(R.id.etAdminAdd);
        etAdminMail = findViewById(R.id.etAdminMail);
        etAdminPhone = findViewById(R.id.etAdminPhone);
        btAdminLogin = findViewById(R.id.btAdminLogin);
        btAdminCancel = findViewById(R.id.btAdminCancel);



    }
}