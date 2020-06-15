package com.stappco.password.sesamo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.poovam.pinedittextfield.LinePinField;
import com.stappco.password.sesamo.R;

public class SetPasswordActivity extends AppCompatActivity {

    LinePinField linePinField, linePinField2;
    Button confirm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        linePinField = findViewById(R.id.lineField);
        linePinField2 = findViewById(R.id.lineField2);
        confirm_btn = findViewById(R.id.confirm_btn);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String password = linePinField.getText().toString();
                final String password_confirm = linePinField2.getText().toString();

                if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(password_confirm)) {
                    if (password.equals(password_confirm)) {
                        SharedPreferences prefs = getSharedPreferences(
                                "sesamo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("password", password);
                        editor.apply();

                        Intent intent = new Intent(SetPasswordActivity.this,MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(SetPasswordActivity.this, "ERROR: The passwords don't match", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(SetPasswordActivity.this, "ERROR: Field empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}