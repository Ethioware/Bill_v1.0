package com.anteneh.fish.bill;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;

public class Update extends AppCompatActivity {
    private TextView sum, totalPro, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, header_no, header_item, header_amount, header_price, header_product, product, total1, total2, total3, total4, total5, total6, total7, total8, total9, total10, total11, total12, total13, total14, total15;
    private EditText name, ed1, am1, p1, ed2, am2, p2, ed3, am3, p3, ed4, am4, p4, ed5, am5, p5, ed6, am6, p6, ed7, am7, p7, ed8, am8, p8, ed9, am9, p9, ed10, am10, p10, ed11, am11, p11, ed12, am12, p12, ed13, am13, p13, ed14, am14, p14, ed15, am15, p15;
    private ConstraintLayout menu, main_layout, action_bar;
    String id;
    Calendar c;
    String date;
    String time;

    @Override
    public void onBackPressed() {
        Intent in = new Intent(Update.this, MainView.class);
        startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        init();
        //dotClick();
        btnClick();
        //newBill();
        //settings();
        //toBill();
        receive();
        //send();
        dark();
        c = Calendar.getInstance();
        date = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR);
        time = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));
    }

    private void toDark(TextView[] t) {
        for (TextView tv : t) {
            tv.setTextColor(Color.parseColor("#E6FFFFFF"));
        }
    }

    private void toDark(ConstraintLayout[] t) {
        for (ConstraintLayout tv : t) {
            tv.setBackgroundColor(Color.parseColor("#1E1E1E"));
        }
    }

    private void toDark(EditText[] t) {
        for (EditText tv : t) {
            tv.setBackgroundResource(R.drawable.edit_light);
        }
    }

    private void toLight(TextView[] t) {
        for (TextView tv : t) {
            tv.setTextColor(Color.parseColor("#99000000"));
        }
    }

    private void toLight(ConstraintLayout[] t) {
        for (ConstraintLayout tv : t) {
            tv.setBackgroundColor(Color.parseColor("#CCFFFFFF"));
        }
    }

    private void toLight(EditText[] t) {
        for (EditText tv : t) {
            tv.setBackgroundResource(R.drawable.edit_background);
        }
    }

    private void dark() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean is_dark = preferences.getBoolean("enable_night", false);
        if (is_dark) {
            TextView[] text_lst = {product, sum, totalPro, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, header_no, header_item, header_amount, header_price, header_product, total1, total2, total3, total4, total5, total6, total7, total8, total9, total10, total11, total12, total13, total14, total15};
            ConstraintLayout[] con_lst = {main_layout};
            EditText[] edit_lst = {name, ed1, am1, p1, ed2, am2, p2, ed3, am3, p3, ed4, am4, p4, ed5, am5, p5, ed6, am6, p6, ed7, am7, p7, ed8, am8, p8, ed9, am9, p9, ed10, am10, p10, ed11, am11, p11, ed12, am12, p12, ed13, am13, p13, ed14, am14, p14, ed15, am15, p15};
            action_bar.setBackgroundColor(Color.parseColor("#00000000"));
            toDark(text_lst);
            toDark(con_lst);
            toDark(edit_lst);
            sum.setBackgroundResource(R.drawable.dark_button);
        } else {
            TextView[] text_lst = {product, sum, totalPro, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, header_no, header_item, header_amount, header_price, header_product, total1, total2, total3, total4, total5, total6, total7, total8, total9, total10, total11, total12, total13, total14, total15};
            ConstraintLayout[] con_lst = {main_layout};
            EditText[] edit_lst = {name, ed1, am1, p1, ed2, am2, p2, ed3, am3, p3, ed4, am4, p4, ed5, am5, p5, ed6, am6, p6, ed7, am7, p7, ed8, am8, p8, ed9, am9, p9, ed10, am10, p10, ed11, am11, p11, ed12, am12, p12, ed13, am13, p13, ed14, am14, p14, ed15, am15, p15};
            action_bar.setBackgroundColor(Color.parseColor("#00000000"));
            sum.setBackgroundResource(R.drawable.light_button);
            toLight(text_lst);
            toLight(con_lst);
            toLight(edit_lst);
        }
    }

    private String pad(int i) {
        if (i < 10)
            return "0" + i;
        return String.valueOf(i);
    }

    private void receive() {
        Intent i = getIntent();
        name.setText(i.getStringExtra("title"));
        ed1.setText(i.getStringExtra("item1"));
        am1.setText(i.getStringExtra("amount1"));
        p1.setText(i.getStringExtra("price1"));
        total1.setText(i.getStringExtra("product1"));
        id = i.getStringExtra("id");
    }

    private void init() {
        totalPro = findViewById(R.id.total_pro);
        n1 = findViewById(R.id.no1);
        n2 = findViewById(R.id.no2);
        n3 = findViewById(R.id.no3);
        n4 = findViewById(R.id.no4);
        n5 = findViewById(R.id.no5);
        n6 = findViewById(R.id.no6);
        n7 = findViewById(R.id.no7);
        n8 = findViewById(R.id.no8);
        n9 = findViewById(R.id.no9);
        n10 = findViewById(R.id.no10);
        n11 = findViewById(R.id.no11);
        n12 = findViewById(R.id.no12);
        n13 = findViewById(R.id.no13);
        n14 = findViewById(R.id.no14);
        n15 = findViewById(R.id.no15);
        header_item = findViewById(R.id.item_header);
        header_amount = findViewById(R.id.amount_header);
        header_price = findViewById(R.id.price_header);
        header_product = findViewById(R.id.total_header);
        action_bar = findViewById(R.id.action_bar);
        header_no = findViewById(R.id.header_no);
        main_layout = findViewById(R.id.main_layout);
        main_layout = findViewById(R.id.main_layout);
        menu = findViewById(R.id.menu);
        total1 = findViewById(R.id.total1);
        total2 = findViewById(R.id.total2);
        total3 = findViewById(R.id.total3);
        total4 = findViewById(R.id.total4);
        total5 = findViewById(R.id.total5);
        total6 = findViewById(R.id.total6);
        total7 = findViewById(R.id.total7);
        total8 = findViewById(R.id.total8);
        total9 = findViewById(R.id.total9);
        total10 = findViewById(R.id.total10);
        total11 = findViewById(R.id.total11);
        total12 = findViewById(R.id.total12);
        total13 = findViewById(R.id.total13);
        total14 = findViewById(R.id.total14);
        total15 = findViewById(R.id.total15);

        name = findViewById(R.id.customer_name);
        product = findViewById(R.id.product);
        sum = findViewById(R.id.update);
        // row 1 //
        ed1 = findViewById(R.id.ed1);
        am1 = findViewById(R.id.am1);
        p1 = findViewById(R.id.p1);
        // //
        ed2 = findViewById(R.id.ed2);
        am2 = findViewById(R.id.am2);
        p2 = findViewById(R.id.p2);
        // //
        ed3 = findViewById(R.id.ed3);
        am3 = findViewById(R.id.am3);
        p3 = findViewById(R.id.p3);
        // //
        ed4 = findViewById(R.id.ed4);
        am4 = findViewById(R.id.am4);
        p4 = findViewById(R.id.p4);
        // //
        ed5 = findViewById(R.id.ed5);
        am5 = findViewById(R.id.am5);
        p5 = findViewById(R.id.p5);
        // //
        ed6 = findViewById(R.id.ed6);
        am6 = findViewById(R.id.am6);
        p6 = findViewById(R.id.p6);
        // //
        ed7 = findViewById(R.id.ed7);
        am7 = findViewById(R.id.am7);
        p7 = findViewById(R.id.p7);
        // //
        ed8 = findViewById(R.id.ed8);
        am8 = findViewById(R.id.am8);
        p8 = findViewById(R.id.p8);
        // //
        ed9 = findViewById(R.id.ed9);
        am9 = findViewById(R.id.am9);
        p9 = findViewById(R.id.p9);
        // //
        ed10 = findViewById(R.id.ed10);
        am10 = findViewById(R.id.am10);
        p10 = findViewById(R.id.p10);
        // //
        ed11 = findViewById(R.id.ed11);
        am11 = findViewById(R.id.am11);
        p11 = findViewById(R.id.p11);
        // //
        ed12 = findViewById(R.id.ed12);
        am12 = findViewById(R.id.am12);
        p12 = findViewById(R.id.p12);
        // //
        ed13 = findViewById(R.id.ed13);
        am13 = findViewById(R.id.am13);
        p13 = findViewById(R.id.p13);
        // //
        ed14 = findViewById(R.id.ed14);
        am14 = findViewById(R.id.am14);
        p14 = findViewById(R.id.p14);
        // //
        ed15 = findViewById(R.id.ed15);
        am15 = findViewById(R.id.am15);
        p15 = findViewById(R.id.p15);

    }

    private static void invisible(View v) {
        v.setVisibility(View.GONE);
    }

    private static double add(EditText a, EditText b) {
        double x = Double.parseDouble(a.getText().toString());
        double y = Double.parseDouble(b.getText().toString());
        return x * y;
    }

    private static boolean row_full(EditText item_name, EditText amount, EditText price) {
        return is_full(item_name) && is_full(amount) && is_full(price);
    }

    private static boolean is_full(EditText editText) {
        return !editText.getText().toString().equals("");
    }


    @SuppressLint("SetTextI18n")
    private void btnClick() {
        sum.setOnClickListener(view -> {
            invisible(menu);
            boolean full1 = row_full(ed1, am1, p1);
            boolean full2 = row_full(ed2, am2, p2);
            boolean full3 = row_full(ed3, am3, p3);
            boolean full4 = row_full(ed4, am4, p4);
            boolean full5 = row_full(ed5, am5, p5);
            boolean full6 = row_full(ed6, am6, p6);
            boolean full7 = row_full(ed7, am7, p7);
            boolean full8 = row_full(ed8, am8, p8);
            boolean full9 = row_full(ed9, am9, p9);
            boolean full10 = row_full(ed10, am10, p10);
            boolean full11 = row_full(ed11, am11, p11);
            boolean full12 = row_full(ed12, am12, p12);
            boolean full13 = row_full(ed13, am13, p13);
            boolean full14 = row_full(ed14, am14, p14);
            boolean full15 = row_full(ed15, am15, p15);

            if (is_full(name)) {
                if (full1) {
                    double row1 = add(am1, p1);
                    total1.setText(String.valueOf(row1));
                    product.setText(String.valueOf(row1));
                    DatabaseHelper helper = new DatabaseHelper(Update.this);
                    helper.update(name.getText().toString(), ed1.getText().toString(), am1.getText().toString(), p1.getText().toString(), total1.getText().toString(), date, time, id);
                    Intent in = new Intent(Update.this, BillsActivity.class);
                    startActivity(in);
                    finish();
                }
                if (full2) {
                    double row2 = add(am2, p2);
                    total2.setText(String.valueOf(row2));
                    product.setText(String.valueOf(row2));
                }
                if (full3) {
                    double row3 = add(am3, p3);
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row3));
                }
                if (full4) {
                    double row4 = add(am4, p4);
                    total4.setText(String.valueOf(row4));
                    product.setText(String.valueOf(row4));
                }
                if (full5) {
                    double row5 = add(am5, p5);
                    total5.setText(String.valueOf(row5));
                    product.setText(String.valueOf(row5));
                }
                if (full6) {
                    double row6 = add(am6, p6);
                    total6.setText(String.valueOf(row6));
                    product.setText(String.valueOf(row6));
                }
                if (full7) {
                    double row7 = add(am7, p7);
                    total7.setText(String.valueOf(row7));
                    product.setText(String.valueOf(row7));
                }
                if (full8) {
                    double row8 = add(am8, p8);
                    total8.setText(String.valueOf(row8));
                    product.setText(String.valueOf(row8));
                }
                if (full9) {
                    double row9 = add(am9, p9);
                    total9.setText(String.valueOf(row9));
                    product.setText(String.valueOf(row9));
                }
                if (full10) {
                    double row10 = add(am10, p10);
                    total10.setText(String.valueOf(row10));
                    product.setText(String.valueOf(row10));
                }
                if (full11) {
                    double row11 = add(am11, p11);
                    total11.setText(String.valueOf(row11));
                    product.setText(String.valueOf(row11));
                }
                if (full12) {
                    double row12 = add(am12, p12);
                    total12.setText(String.valueOf(row12));
                    product.setText(String.valueOf(row12));
                }
                if (full13) {
                    double row13 = add(am13, p13);
                    total13.setText(String.valueOf(row13));
                    product.setText(String.valueOf(row13));
                }
                if (full14) {
                    double row14 = add(am14, p14);
                    total14.setText(String.valueOf(row14));
                    product.setText(String.valueOf(row14));
                }
                if (full15) {
                    double row15 = add(am15, p15);
                    total15.setText(String.valueOf(row15));
                    product.setText(String.valueOf(row15));
                }
                if (full1 && full2) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    product.setText(String.valueOf(row1 + row2));
                }
                if (full1 && full2 && full3) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3));
                }
                if (full1 && full2 && full3 && full4) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    total4.setText(String.valueOf(row4));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4));
                }
                if (full1 && full2 && full3 && full4 && full5) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6 && full7) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    double row7 = add(am7, p7);
                    total7.setText(String.valueOf(row7));
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6 + row7));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6 && full7 && full8) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    double row7 = add(am7, p7);
                    double row8 = add(am8, p8);
                    total8.setText(String.valueOf(row8));
                    total7.setText(String.valueOf(row7));
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6 && full7 && full8 && full9) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    double row7 = add(am7, p7);
                    double row8 = add(am8, p8);
                    double row9 = add(am9, p9);
                    total9.setText(String.valueOf(row9));
                    total8.setText(String.valueOf(row8));
                    total7.setText(String.valueOf(row7));
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6 && full7 && full8 && full9 && full10) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    double row7 = add(am7, p7);
                    double row8 = add(am8, p8);
                    double row9 = add(am9, p9);
                    double row10 = add(am10, p10);
                    total10.setText(String.valueOf(row10));
                    total9.setText(String.valueOf(row9));
                    total8.setText(String.valueOf(row8));
                    total7.setText(String.valueOf(row7));
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6 && full7 && full8 && full9 && full10 && full11) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    double row7 = add(am7, p7);
                    double row8 = add(am8, p8);
                    double row9 = add(am9, p9);
                    double row10 = add(am10, p10);
                    double row11 = add(am11, p11);
                    total11.setText(String.valueOf(row11));
                    total10.setText(String.valueOf(row10));
                    total9.setText(String.valueOf(row9));
                    total8.setText(String.valueOf(row8));
                    total7.setText(String.valueOf(row7));
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6 && full7 && full8 && full9 && full10 && full11 && full12) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    double row7 = add(am7, p7);
                    double row8 = add(am8, p8);
                    double row9 = add(am9, p9);
                    double row10 = add(am10, p10);
                    double row11 = add(am11, p11);
                    double row12 = add(am12, p12);
                    total12.setText(String.valueOf(row12));
                    total11.setText(String.valueOf(row11));
                    total10.setText(String.valueOf(row10));
                    total9.setText(String.valueOf(row9));
                    total8.setText(String.valueOf(row8));
                    total7.setText(String.valueOf(row7));
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6 && full7 && full8 && full9 && full10 && full11 && full12 && full13) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    double row7 = add(am7, p7);
                    double row8 = add(am8, p8);
                    double row9 = add(am9, p9);
                    double row10 = add(am10, p10);
                    double row11 = add(am11, p11);
                    double row12 = add(am12, p12);
                    double row13 = add(am13, p13);
                    total13.setText(String.valueOf(row13));
                    total12.setText(String.valueOf(row12));
                    total11.setText(String.valueOf(row11));
                    total10.setText(String.valueOf(row10));
                    total9.setText(String.valueOf(row9));
                    total8.setText(String.valueOf(row8));
                    total7.setText(String.valueOf(row7));
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6 && full7 && full8 && full9 && full10 && full11 && full12 && full13 && full14) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    double row7 = add(am7, p7);
                    double row8 = add(am8, p8);
                    double row9 = add(am9, p9);
                    double row10 = add(am10, p10);
                    double row11 = add(am11, p11);
                    double row12 = add(am12, p12);
                    double row13 = add(am13, p13);
                    double row14 = add(am14, p14);
                    total14.setText(String.valueOf(row14));
                    total13.setText(String.valueOf(row13));
                    total12.setText(String.valueOf(row12));
                    total11.setText(String.valueOf(row11));
                    total10.setText(String.valueOf(row10));
                    total9.setText(String.valueOf(row9));
                    total8.setText(String.valueOf(row8));
                    total7.setText(String.valueOf(row7));
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14));
                }
                if (full1 && full2 && full3 && full4 && full5 && full6 && full7 && full8 && full9 && full10 && full11 && full12 && full13 && full14 && full15) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    double row4 = add(am4, p4);
                    double row5 = add(am5, p5);
                    double row6 = add(am6, p6);
                    double row7 = add(am7, p7);
                    double row8 = add(am8, p8);
                    double row9 = add(am9, p9);
                    double row10 = add(am10, p10);
                    double row11 = add(am11, p11);
                    double row12 = add(am12, p12);
                    double row13 = add(am13, p13);
                    double row14 = add(am14, p14);
                    double row15 = add(am15, p15);
                    total15.setText(String.valueOf(row15));
                    total14.setText(String.valueOf(row14));
                    total13.setText(String.valueOf(row13));
                    total12.setText(String.valueOf(row12));
                    total11.setText(String.valueOf(row11));
                    total10.setText(String.valueOf(row10));
                    total9.setText(String.valueOf(row9));
                    total8.setText(String.valueOf(row8));
                    total7.setText(String.valueOf(row7));
                    total6.setText(String.valueOf(row6));
                    total5.setText(String.valueOf(row5));
                    total4.setText(String.valueOf(row4));
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14 + row15));
                } else if (full1||full2||full3||full4||full5||full6||full7||full8||full9||full10||full11||full12||full13||full14||full15) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    boolean is_dark = preferences.getBoolean("enable_night", false);
                    if (is_dark) {
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.update_toast_dark, findViewById(R.id.update_layout));
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.BOTTOM, 0, (int) (view.getY() / 2) - (int) (view.getX()) + 150);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();
                    } else {
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.update_toast, findViewById(R.id.update_layout_day));
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.BOTTOM, 0, (int) (view.getY() / 2) - (int) (view.getX()) + 150);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();
                    }
                }
            } else {
                Log.d("error_updatename empty","update NAME empty");

            }
        });
    }

}





