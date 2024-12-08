package com.example.virtualgrocerhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.stream.DoubleStream;

public class AdminOTPActivity extends Activity
{

    Button btVerify,btSendAgain;
    EditText[] et;
    int etId[] = {R.id.et1,R.id.et2,R.id.et3,R.id.et4};
    Random random;
    SharedPreferences share;
    TextView tvPhone;
    String phone;
    androidx.cardview.widget.CardView cardOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_otp);

        int otp = getIntent().getIntExtra("otp",0000);
        btVerify = findViewById(R.id.btVerify);
        tvPhone = findViewById(R.id.tvPhone);
        btSendAgain = findViewById(R.id.btSendAgain);
        cardOTP = findViewById(R.id.cardOTP);

        share = getSharedPreferences("adminReg",MODE_PRIVATE);
        phone = share.getString("phone","0");

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.admin_anim);
        cardOTP.startAnimation(fadeIn);

        tvPhone.setText("+91 "+phone);

        et = new EditText[4];
        random = new Random();

        for(int i=0; i<etId.length; i++)
        {
            et[i] = findViewById(etId[i]);
            if(i!=0)
            {
                setupOtpEditText(et[i-1], et[i]);
            }
        }

        setupOtpEditText(et[etId.length-1], null);

        btSendAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int newOtp = random.nextInt(9999);
                while(newOtp<1000)
                {
                    newOtp = random.nextInt(9999);
                }

                Intent i = new Intent(getApplicationContext(),AdminOTPActivity.class);
                i.putExtra("otp",newOtp);

                SmsManager sms=SmsManager.getDefault();
                String message = "Your OTP for Virtual GroccerHub aplication is "+newOtp;
                sms.sendTextMessage(phone, null, message, null,null);
                startActivity(i);
                finish();
            }
        });

        btVerify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String verify= "";
                for(int i=0; i<4; i++)
                {
                    verify+=et[i].getText().toString();
                }
                if(verify.equals(""+otp))
                {
                    Toast.makeText(getApplicationContext(), "Correct OTP", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), ChangeAdminActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Incorrect OTP...!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupOtpEditText(final EditText currentEditText, final EditText nextEditText)
    {
        currentEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (s.length() == 1 && nextEditText != null)
                {
                    nextEditText.requestFocus();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // No action needed before text is changed
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                // No action needed after text is changed
            }
        });
    }
}