package com.anteneh.fish.bill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Settings extends AppCompatActivity {
    private TextView sec_pass, set_temp, set_save, set_store, set_location, appearance_txt, appearance, header, file_text, sec_text, themes, font, language, graph;
    private RelativeLayout appearance_menu, main, file_menu, sec_menu, app_tab, file_tab, sec_tab;
    private ImageView night, appearance_down, file_down, sec_down, back;
    private View sec_line, l, l2, l3, f, f2, f3, sv, fv, app_view;
    private boolean is_dark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
        drop_down();
        dark();
        themes.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.this, Preference.class);
            startActivity(intent);
        });
        back.setOnClickListener(view -> {
            Intent in = new Intent(Settings.this, MainView.class);
            startActivity(in);
        });

    }

    @Override
    public void onBackPressed() {
        invisible(sec_menu);
        invisible(file_menu);
        invisible(appearance_menu);
        Intent in = new Intent(Settings.this, MainView.class);
        startActivity(in);
    }

    private void dark() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        is_dark = preferences.getBoolean("enable_night", false);
        if (is_dark) {
            TextView[] txt_lst = {sec_pass, sec_text, set_store, set_temp, set_save, set_location, appearance_txt, appearance, header, file_text, sec_text, themes, graph, language, font};
            View[] v_lst = {sec_line, l, l2, l3, f, f2, f3};
            appearance_menu.setBackgroundResource(R.drawable.settings_dropdown_dark);
            file_menu.setBackgroundResource(R.drawable.settings_dropdown_dark);
            sec_menu.setBackgroundResource(R.drawable.settings_dropdown_dark);
            main.setBackgroundColor(Color.parseColor("#E6000000"));
            app_tab.setBackgroundColor(Color.parseColor("#4D4D4D"));
            sec_tab.setBackgroundColor(Color.parseColor("#4D4D4D"));
            file_tab.setBackgroundColor(Color.parseColor("#4D4D4D"));
            app_view.setBackgroundColor(Color.parseColor("#1FFF0F"));
            sv.setBackgroundColor(Color.parseColor("#1FFF0F"));
            fv.setBackgroundColor(Color.parseColor("#1FFF0F"));
            appearance_down.setImageResource(R.drawable.down_dark);
            file_down.setImageResource(R.drawable.down_dark);
            sec_down.setImageResource(R.drawable.down_dark);
            night.setImageResource(R.drawable.dark_d);
            toDark(txt_lst);
            toneon(v_lst);
        } else {

        }
    }

    private void init() {
        sec_line = findViewById(R.id.sec_line);
        sec_pass = findViewById(R.id.sec_pass);
        fv = findViewById(R.id.fv);
        sv = findViewById(R.id.sv);
        file_tab = findViewById(R.id.file_tab);
        sec_tab = findViewById(R.id.sec_tab);
        f = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        set_save = findViewById(R.id.set_autosave);
        set_temp = findViewById(R.id.set_templates);
        set_store = findViewById(R.id.set_storage);
        set_location = findViewById(R.id.set_file_location);
        app_view = findViewById(R.id.appearance_view);
        app_tab = findViewById(R.id.apperance_tab);
        night = findViewById(R.id.night);
        appearance_txt = findViewById(R.id.appearance_txt);
        back = findViewById(R.id.back);
        themes = findViewById(R.id.themes_menu);
        sec_down = findViewById(R.id.sec_down);
        font = findViewById(R.id.set_font);
        language = findViewById(R.id.set_language);
        graph = findViewById(R.id.set_Graph);
        sec_menu = findViewById(R.id.sec_drop_down);
        sec_text = findViewById(R.id.sec_text);
        appearance_down = findViewById(R.id.appearance_down);
        file_down = findViewById(R.id.file_down);
        appearance = findViewById(R.id.appearance_text);
        file_text = findViewById(R.id.file_text);
        appearance_menu = findViewById(R.id.appearance_drop_down);
        file_menu = findViewById(R.id.file_drop_down);
        header = findViewById(R.id.settings_header);
        main = findViewById(R.id.settings_layout);
        l = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
    }

    private void toDark(TextView[] t) {
        for (TextView tv : t) {
            tv.setTextColor(Color.parseColor("#E6FAFF"));
        }
    }

    private void toDark(ConstraintLayout[] t) {
        for (ConstraintLayout tv : t) {
            tv.setBackgroundColor(Color.parseColor("#1E1E1E"));
        }
    }

    private void toneon(View[] views) {
        for (View v : views) {
            v.setBackgroundColor(Color.parseColor("#CCFFFFFF"));
        }
    }

    private void invisible(View v) {
        v.setVisibility(View.GONE);
    }

    private void visible(View v) {
        v.setVisibility(View.VISIBLE);
    }


    private void drop_down() {
        appearance_down.setOnClickListener(view -> {
            visible(appearance_menu);
            invisible(file_menu);
            if (is_dark) {
                appearance_down.setImageResource(R.drawable.up_dark);
                invisible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
                appearance_down.setImageResource(R.drawable.down_dark);
            } else {
                appearance_down.setImageResource(R.drawable.up);
                invisible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
                appearance_down.setImageResource(R.drawable.down);
            }
        });
        appearance.setOnClickListener(view -> {
            if (is_dark) {
                visible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
                appearance_down.setImageResource(R.drawable.up_dark);
                file_down.setImageResource(R.drawable.down_dark);
            } else {
                visible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
                appearance_down.setImageResource(R.drawable.up);
                file_down.setImageResource(R.drawable.down);
            }
        });
        sec_down.setOnClickListener(view -> {
            if (is_dark) {
                file_down.setImageResource(R.drawable.down_dark);
                appearance_down.setImageResource(R.drawable.down_dark);
                sec_down.setImageResource(R.drawable.down_dark);
                invisible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
            } else {
                file_down.setImageResource(R.drawable.down);
                appearance_down.setImageResource(R.drawable.down);
                sec_down.setImageResource(R.drawable.down);
                invisible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
            }
        });

        sec_text.setOnClickListener(view -> {
            if (is_dark) {
                visible(sec_menu);
                invisible(appearance_menu);
                invisible(file_menu);
                file_down.setImageResource(R.drawable.down_dark);
                appearance_down.setImageResource(R.drawable.down_dark);
                sec_down.setImageResource(R.drawable.up_dark);
            } else {
                visible(sec_menu);
                invisible(appearance_menu);
                invisible(file_menu);
                file_down.setImageResource(R.drawable.down);
                appearance_down.setImageResource(R.drawable.down);
                sec_down.setImageResource(R.drawable.up);
            }
        });

        file_text.setOnClickListener(view -> {
            if (is_dark) {
                visible(file_menu);
                invisible(appearance_menu);
                invisible(sec_menu);
                file_down.setImageResource(R.drawable.up_dark);
                appearance_down.setImageResource(R.drawable.down_dark);
                sec_down.setImageResource(R.drawable.down_dark);
            } else {
                visible(file_menu);
                invisible(appearance_menu);
                invisible(sec_menu);
                file_down.setImageResource(R.drawable.up);
                appearance_down.setImageResource(R.drawable.down);
                sec_down.setImageResource(R.drawable.down);
            }
        });
        file_down.setOnClickListener(view -> {
            if (is_dark) {
                file_down.setImageResource(R.drawable.down_dark);
                appearance_down.setImageResource(R.drawable.down_dark);
                sec_down.setImageResource(R.drawable.down_dark);
                invisible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
            } else {
                file_down.setImageResource(R.drawable.down);
                appearance_down.setImageResource(R.drawable.down);
                sec_down.setImageResource(R.drawable.down);
                invisible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
            }
        });
        header.setOnClickListener(view -> {
            if (is_dark) {
                invisible(file_menu);
                invisible(appearance_menu);
                invisible(sec_menu);
                appearance_down.setImageResource(R.drawable.down_dark);
                file_down.setImageResource(R.drawable.down_dark);
                sec_down.setImageResource(R.drawable.down_dark);
            } else {
                invisible(file_menu);
                invisible(appearance_menu);
                invisible(sec_menu);
                appearance_down.setImageResource(R.drawable.down);
                file_down.setImageResource(R.drawable.down);
                sec_down.setImageResource(R.drawable.down);
            }
        });
        main.setOnClickListener(view -> {
            if (is_dark) {
                invisible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
                appearance_down.setImageResource(R.drawable.down_dark);
                file_down.setImageResource(R.drawable.down_dark);
                sec_down.setImageResource(R.drawable.down_dark);
            } else {
                invisible(appearance_menu);
                invisible(file_menu);
                invisible(sec_menu);
                appearance_down.setImageResource(R.drawable.down);
                file_down.setImageResource(R.drawable.down);
                sec_down.setImageResource(R.drawable.down);
            }
        });

    }
}