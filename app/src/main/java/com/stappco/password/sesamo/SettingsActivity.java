package com.stappco.password.sesamo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codemybrainsout.ratingdialog.RatingDialog;

public class SettingsActivity extends AppCompatActivity {

    TextView change_pass_btn,rate_btn,credits_btn,change_btn;
    Button back_btn;
    EditText old_change_pass_text,new_change_pass_text,new_confirm_pass_text;
    RelativeLayout change_rl,credits_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        change_pass_btn = findViewById(R.id.change_pass);
        rate_btn = findViewById(R.id.rate_btn);
        credits_btn = findViewById(R.id.credits_btn);
        back_btn = findViewById(R.id.back_btn);
        change_btn = findViewById(R.id.change_btn);
        old_change_pass_text = findViewById(R.id.old_pass_change_text);
        new_change_pass_text = findViewById(R.id.new_pass_change_text);
        new_confirm_pass_text = findViewById(R.id.new_confirm_pass_change_text);
        change_rl = findViewById(R.id.change_master_rl);
        credits_rl = findViewById(R.id.credits_rl);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        change_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_rl.setVisibility(View.VISIBLE);
                credits_rl.setVisibility(View.GONE);
            }
        });

        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = getSharedPreferences(
                        "sesamo", Context.MODE_PRIVATE);
                final String password1 = prefs.getString("password", "");

                String old = old_change_pass_text.getText().toString();
                String new_pass = new_change_pass_text.getText().toString();
                String confirm_pass = new_confirm_pass_text.getText().toString();

                    if (!TextUtils.isEmpty(old) && !TextUtils.isEmpty(new_pass) && !TextUtils.isEmpty(confirm_pass)) {
                        if (old.equals(password1) && new_pass.equals(confirm_pass)) {
                            SharedPreferences prefs1 = getSharedPreferences(
                                    "sesamo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("password", new_pass);
                            editor.apply();
                            change_rl.setVisibility(View.GONE);

                            Toast.makeText(SettingsActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(SettingsActivity.this, "ERROR: old password is incorrect or the new ones don't match", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SettingsActivity.this, "ERROR: Field empty", Toast.LENGTH_SHORT).show();
                    }
            }

        });

        rate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_rl.setVisibility(View.GONE);
                credits_rl.setVisibility(View.GONE);
                final RatingDialog ratingDialog = new RatingDialog.Builder(SettingsActivity.this)
                        .threshold(3)
                        .icon(getResources().getDrawable(R.drawable.sesame))
                        .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                            @Override
                            public void onFormSubmitted(String feedback) {

                            }
                        }).build();

                ratingDialog.show();
            }
        });

        credits_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credits_rl.setVisibility(View.VISIBLE);
                change_rl.setVisibility(View.GONE);
            }
        });

    }
}
