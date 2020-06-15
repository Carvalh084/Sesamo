package com.stappco.password.sesamo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stappco.password.sesamo.R;

public class MainActivity extends AppCompatActivity {

    CardView social_card,email_card,bank_card,commerce_card,wifi_card,website_card,others_card;
    Button delete_btn,settings_btn,refresh_btn;
    Context context;
    TextView pass_gen,copy_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        social_card = findViewById(R.id.social_card);
        email_card = findViewById(R.id.email_card);
        bank_card = findViewById(R.id.bank_card);
        commerce_card = findViewById(R.id.ecommerce_card);
        wifi_card = findViewById(R.id.wifi_card);
        website_card = findViewById(R.id.website_card);
        others_card = findViewById(R.id.others_card);
        copy_btn = findViewById(R.id.copy_btn);
        delete_btn = findViewById(R.id.delete_btn);
        settings_btn = findViewById(R.id.settings_btn);
        refresh_btn = findViewById(R.id.refresh_btn);
        pass_gen = findViewById(R.id.pass_gen);


        gen();

        social_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SocialMediaActivity.class);
                startActivity(intent);
            }
        });

        email_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmailsActivity.class);
                startActivity(intent);
            }
        });

        bank_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BankActivity.class);
                startActivity(intent);
            }
        });

        commerce_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CommerceActivity.class);
                startActivity(intent);
            }
        });

        wifi_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WifiActivity.class);
                startActivity(intent);
            }
        });

        website_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebsitesActivity.class);
                startActivity(intent);
            }
        });

        others_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OthersActivity.class);
                startActivity(intent);
            }
        });

        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gen();
            }
        });



        copy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Edit Text", pass_gen.getText().toString());
                clipboardManager.setPrimaryClip(clip);
                clip.getDescription();
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete");
                builder.setMessage("\n" +
                        "Are you sure you want to delete all passwords? This action will close the app automatically...");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearAppData();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });



    }

    public static long Code(){ //this code returns the  unique 16 digit code
    //  //creating a 16 digit code using Math.random function
        long code   =(long)((Math.random()*9*Math.pow(10,15))+Math.pow(10,15));
        return code; //returning the code
    }

    private void clearAppData() {
        try {
            // clearing app data
            if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                ((ActivityManager)getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData(); // note: it has a return value!
            } else {
                String packageName = getApplicationContext().getPackageName();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("pm clear "+ packageName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gen(){
        long code = Code();//function calling
        String unique_password = "";
        for (long i = code; i != 0; i /= 100)//a loop extracting 2 digits from the code
        {
            long digit = i % 100;//extracting two digits
            if (digit <= 90)
                digit = digit + 32;
            //converting those two digits(ascii value) to its character value
            char ch = (char) digit;
            // adding 32 so that our least value be a valid character
            unique_password = ch + unique_password;//adding the character to the string
        }
        System.out.println("unique password =" + unique_password);
        pass_gen.setText(unique_password);
    }

}




