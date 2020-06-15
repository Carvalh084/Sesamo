package com.stappco.password.sesamo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.poovam.pinedittextfield.CirclePinField;
import com.stappco.password.sesamo.R;

public class WelcomeActivity extends AppCompatActivity {

    CirclePinField circlePinField;
    Button enter_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        circlePinField = findViewById(R.id.circleField);
        enter_btn = findViewById(R.id.enter_btn);

        SharedPreferences settings=getSharedPreferences("prefs",0);
        boolean firstRun=settings.getBoolean("firstRun",false);
        if(firstRun==false)//if running for first time
        //Splash will load for first time
        {
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("firstRun",true);
            editor.commit();
            Intent intent =new Intent(WelcomeActivity.this,SetPasswordActivity.class);
            startActivity(intent);
            finish();
        }
        else {
        }

        SharedPreferences prefs = getSharedPreferences(
                "sesamo", Context.MODE_PRIVATE);
        final String password1 = prefs.getString("password", "");

        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String password_confirm = circlePinField.getText().toString();

                    if (password_confirm.equals(password1)) {
                        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(WelcomeActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                }
        });

    }
}
