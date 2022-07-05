package com.anteneh.fish.bill;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.anteneh.fish.bill.model.BillViewModel;
import com.anteneh.fish.bill.model.Bill_model;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  private ConstraintLayout c;
  private ImageView img,logo;
  private TextView ltxt;
  private BillViewModel viewModel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(BillViewModel.class);
    viewModel.getAll().observe(this, bill_models -> {
      for (Bill_model model:bill_models) {
        Log.d("TAG","Name" + model.getTitle());
      }
    });
    init();
    dark();
    Thread t = new Thread(() -> {
      try {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
        img.startAnimation(animation);
        logo.startAnimation(animation);
        ltxt.startAnimation(animation);
        sleep(4500);
      }catch (Exception e){
        e.printStackTrace();
      }finally {
        Intent in = new Intent(MainActivity.this, MainView.class);
        startActivity(in);

      }
    });
    t.start();
  }
private void init(){
  c= findViewById(R.id.Start_up);
  img = findViewById(R.id.start_img);
  logo = findViewById(R.id.logo);
  ltxt = findViewById(R.id.logo_txt);
}
private void dark(){
  SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
  boolean is_dark = preferences.getBoolean("enable_night", false);
  if (is_dark){
      logo.setBackgroundResource(R.drawable.logo_back_light);
      ltxt.setTextColor(Color.WHITE);
      c.setBackgroundColor(Color.BLACK);
  }else {
    logo.setBackgroundResource(R.drawable.logo_back);
    ltxt.setTextColor(Color.BLACK);
    c.setBackgroundColor(Color.WHITE);
  }
}
  @Override
  public void onBackPressed() {
  }

}