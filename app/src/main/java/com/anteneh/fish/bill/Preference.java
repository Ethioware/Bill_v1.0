package com.anteneh.fish.bill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


public class Preference extends PreferenceActivity {

    private CheckBoxPreference check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        dark();

    }

    private void dark() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean is_dark = preferences.getBoolean("enable_night", false);
        if (is_dark) {
            getListView().setBackgroundColor(Color.parseColor("#ffffff"));
        } else
            getListView().setBackgroundColor(Color.parseColor("#ffffff"));
        CheckBoxPreference change = (CheckBoxPreference) findPreference("enable_night");
        change.setOnPreferenceChangeListener(new android.preference.Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(android.preference.Preference p, Object o) {
                boolean yep = (boolean)o;
                if (yep) {
                    getListView().setBackgroundColor(Color.parseColor("#ffffff"));
                    Intent intent = new Intent(Preference.this, MainView.class);
                    startActivity(intent);
                }
                else{
                    getListView().setBackgroundColor(Color.parseColor("#ffffff"));
                    Intent intent = new Intent(Preference.this, MainView.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        dark();
        super.onResume();
    }
}
