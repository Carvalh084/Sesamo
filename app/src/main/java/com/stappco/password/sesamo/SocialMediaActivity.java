package com.stappco.password.sesamo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stappco.password.sesamo.R;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import third.part.android.util.Base64;

public class SocialMediaActivity extends AppCompatActivity {

    Button back_btn,add_btn,close_btn,eye;
    TextView save;
    CardView save_card;
    EditText editTitle,editPassword,editNotes;
    String AES = "AES";
    String outputString;
    String outputString2;
    String outputString3;

    ArrayList<ExampleItemSocial> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        loadData();
        buildRecyclerView();
        //setInsertButton();

        back_btn = findViewById(R.id.back_btn);
        add_btn = findViewById(R.id.add_btn);
        save = findViewById(R.id.save);
        save_card = findViewById(R.id.save_card);
        editTitle = findViewById(R.id.title);
        editPassword = findViewById(R.id.password);
        editNotes = findViewById(R.id.notes);
        mRecyclerView = findViewById(R.id.recyclerview);
        close_btn = findViewById(R.id.close);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocialMediaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_card.setVisibility(View.VISIBLE);
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_card.setVisibility(View.GONE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_card.setVisibility(View.GONE);
                EditText line1 = findViewById(R.id.title);
                EditText line2 = findViewById(R.id.password);
                EditText line3 = findViewById(R.id.notes);

                insertItem(line1.getText().toString(), line2.getText().toString(),line3.getText().toString());

                saveData();
                line1.getText().clear();
                line2.getText().clear();
                line3.getText().clear();
            }
        });

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();

    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ExampleItemSocial>>() {}.getType();
        mExampleList = gson.fromJson(json, type);
        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }
    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void insertItem(String line1, String line2,String line3) {
        mExampleList.add(new ExampleItemSocial(line1,line2,line3));
        mAdapter.notifyItemInserted(mExampleList.size());
    }
}
