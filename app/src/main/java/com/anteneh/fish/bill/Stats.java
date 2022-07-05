package com.anteneh.fish.bill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Stats extends AppCompatActivity {
    private ImageView back;
    private boolean is_dark;
    RelativeLayout main;
    TextView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        init();
        dark();
        back.setOnClickListener(view -> {Intent in = new Intent(Stats.this, MainView.class);
            startActivity(in);
        });
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(Stats.this, MainView.class);
        startActivity(in);
    }

    private void dark(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        is_dark = preferences.getBoolean("enable_night", false);
        if (is_dark){
            main.setBackgroundColor(Color.parseColor("#E6000000"));
            header.setTextColor(Color.parseColor("#E6FAFF"));

        }else {

        }
    }
    private void init(){
        header = findViewById(R.id.stats_header);
        back = findViewById(R.id.back);
        main = findViewById(R.id.stats_main);
    }
}