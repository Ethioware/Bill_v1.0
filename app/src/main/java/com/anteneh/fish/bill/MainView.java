package com.anteneh.fish.bill;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;


import com.anteneh.fish.bill.model.BillViewModel;
import com.anteneh.fish.bill.model.Bill_model;

import java.util.Calendar;

public class MainView extends AppCompatActivity {
    private ImageView clear_image, setting_icon, bill_icon, stats_icon, customer_img;
    private ImageButton dot, send;
    private ConstraintLayout menu, header, main_layout, action_bar;
    private RelativeLayout content, clear_r, bill_r, setting_r, stats_r, about_r, help_r, break_r;
    private TextView stats, warn, about, help, break_menu, sum, totalPro, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, header_no, header_item, header_amount, header_price, header_product, New, bills, setting, product, total1, total2, total3, total4, total5, total6, total7, total8, total9, total10, total11, total12, total13, total14, total15;
    private EditText name, ed1, am1, p1, ed2, am2, p2, ed3, am3, p3, ed4, am4, p4, ed5, am5, p5, ed6, am6, p6, ed7, am7, p7, ed8, am8, p8, ed9, am9, p9, ed10, am10, p10, ed11, am11, p11, ed12, am12, p12, ed13, am13, p13, ed14, am14, p14, ed15, am15, p15;
    Dialog dialog;
    Calendar c;
    String date;
    String time;
    String msg = "";
    String phone;
    private BillViewModel viewModel;

    //// adding custom conteacts full video 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(MainView.this.getApplication()).create(BillViewModel.class);
        init();
        dotClick();
        btnClick();
        newBill();
        settings();
        toBill();
        toStats();
        calculator();
        c = Calendar.getInstance();
        date = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR);
        time = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));
        dark();
        EditText[] edit_lst = {name, ed1, am1, p1, ed2, am2, p2, ed3, am3, p3, ed4, am4, p4, ed5, am5, p5, ed6, am6, p6, ed7, am7, p7, ed8, am8, p8, ed9, am9, p9, ed10, am10, p10, ed11, am11, p11, ed12, am12, p12, ed13, am13, p13, ed14, am14, p14, ed15, am15, p15};
        invisibleMenu(edit_lst);
    }
    private void calculator(){
        customer_img.setOnClickListener(view -> {
            Intent i = new Intent(MainView.this,mini_calculator.class);
            startActivity(i);
        });
    }
    @Override
    public void onBackPressed() {
        invisible(menu);
    }

    private void pop() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean is_dark = preferences.getBoolean("enable_night", false);
        TextView confirm;
        TextView cancel;
        EditText num;
        if (is_dark) {
            dialog = new Dialog(MainView.this);
            dialog.setContentView(R.layout.send_message_dark);
            dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(MainView.this, R.drawable.contant_light));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            confirm = dialog.findViewById(R.id.confirm_send);
            cancel = dialog.findViewById(R.id.cancel_send);
            num = dialog.findViewById(R.id.phone_num);
            dialog.getWindow().getAttributes().windowAnimations = R.style.anim;
            warn = dialog.findViewById(R.id.empty_warning);
        } else {
            dialog = new Dialog(MainView.this);
            dialog.setContentView(R.layout.send_message);
            dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(MainView.this, R.drawable.contact_dark));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            dialog.getWindow().getAttributes().windowAnimations = R.style.anim;
            confirm = dialog.findViewById(R.id.confirm_send);
            cancel = dialog.findViewById(R.id.cancel_send);
            num = dialog.findViewById(R.id.phone_num);
            warn = dialog.findViewById(R.id.empty_warning);
        }
        dialog.show();
        confirm.setOnClickListener(v -> {
            phone = num.getText().toString();
            if (!phone.isEmpty()) {
                invisible(warn);
                sendMe();
                dialog.dismiss();
            } else {
                visible(warn);
                dialog.show();
            }
        });
        cancel.setOnClickListener(v -> {
            invisible(warn);
            dialog.dismiss();
        });

    }

    private String spacing(String s) {
        switch (s.length()) {
            case 1:
                s = s + "\t ";
            case 2:
                s = s + "  ";
            case 3:
                s = s + " ";
            case 4:
                s = s + " ";
            case 5:
                s = s + "  ";
            case 6:
                s = s + "  ";
            case 7:
                s = s + " ";
            default:
                break;
        }
        return s;
    }

    private void send() {
        send.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    pop();
                } else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                }
            }
        });
    }

    private void sendMe() {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean is_dark = preferences.getBoolean("enable_night", false);
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(phone + "".trim(), null, msg, null, null);
            if (is_dark){
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.sent_dark, findViewById(R.id.sent_layout_dark));
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, (int) (layout.getY() / 2) - (int) (layout.getX()) + 200);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
            }else{
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.sent, findViewById(R.id.sent_layout_day));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.BOTTOM, 0, (int) (layout.getY() / 2) - (int) (layout.getX()) + 200);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainView.this, "Sending Failed", Toast.LENGTH_SHORT).show();

        }
    }

    private String pad(int i) {
        if (i < 10)
            return "0" + i;
        return String.valueOf(i);
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

    private void toDark(EditText[] t) {
        for (EditText tv : t) {
            tv.setBackgroundResource(R.drawable.edit_light);
        }
    }

    private void toDark(RelativeLayout[] r) {
        for (RelativeLayout rel : r) {
            rel.setBackgroundColor(Color.DKGRAY);
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
        TextView[] text_lst = {setting, bills, stats, New, about, help, break_menu, product, sum, totalPro, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, header_no, header_item, header_amount, header_price, header_product, total1, total2, total3, total4, total5, total6, total7, total8, total9, total10, total11, total12, total13, total14, total15};
        ConstraintLayout[] con_lst = {main_layout, action_bar};
        if (is_dark) {
            RelativeLayout[] rel_lst = {clear_r, about_r, stats_r, setting_r, about_r, break_r, bill_r, help_r};
            EditText[] edit_lst = {name, ed1, am1, p1, ed2, am2, p2, ed3, am3, p3, ed4, am4, p4, ed5, am5, p5, ed6, am6, p6, ed7, am7, p7, ed8, am8, p8, ed9, am9, p9, ed10, am10, p10, ed11, am11, p11, ed12, am12, p12, ed13, am13, p13, ed14, am14, p14, ed15, am15, p15};
            dot.setImageResource(R.drawable.menu_light);
            customer_img.setImageResource(R.drawable.customer_light);
            send.setImageResource(R.drawable.send_dark);
            dialog = new Dialog(MainView.this);
            dialog.setContentView(R.layout.send_message_dark);
            dialog.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(MainView.this, R.drawable.dialog_dark));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            toDark(text_lst);
            toDark(con_lst);
            toDark(edit_lst);
            toDark(rel_lst);
            sum.setBackgroundResource(R.drawable.dark_button);
        } else {
            EditText[] edit_lst = {name, ed1, am1, p1, ed2, am2, p2, ed3, am3, p3, ed4, am4, p4, ed5, am5, p5, ed6, am6, p6, ed7, am7, p7, ed8, am8, p8, ed9, am9, p9, ed10, am10, p10, ed11, am11, p11, ed12, am12, p12, ed13, am13, p13, ed14, am14, p14, ed15, am15, p15};
            dot.setImageResource(R.drawable.ic_menu);
            invisibleMenu(edit_lst);
            customer_img.setImageResource(R.drawable.customer);
            action_bar.setBackgroundColor(Color.parseColor("#00000000"));
            send.setImageResource(R.drawable.send2);
            sum.setBackgroundResource(R.drawable.light_button);
            toLight(text_lst);
            toLight(con_lst);
            toLight(edit_lst);
        }
    }

    private void init() {
        warn = findViewById(R.id.empty_warning);
        clear_r = findViewById(R.id.clear_menu_layout);
        bill_r = findViewById(R.id.bills_menu_layout);
        setting_r = findViewById(R.id.settings_menu_layout);
        about_r = findViewById(R.id.about_menu_layout);
        help_r = findViewById(R.id.help_menu_layout);
        stats_r = findViewById(R.id.stats_menu_layout);
        break_r = findViewById(R.id.break_menu_layout);
        help = findViewById(R.id.help_menu);
        break_menu = findViewById(R.id.break_menu);
        stats = findViewById(R.id.stats_menu);
        about = findViewById(R.id.about_menu);
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
        customer_img = findViewById(R.id.custom_image);
        header_item = findViewById(R.id.item_header);
        header_amount = findViewById(R.id.amount_header);
        header_price = findViewById(R.id.price_header);
        header_product = findViewById(R.id.total_header);
        action_bar = findViewById(R.id.action_bar);
        header_no = findViewById(R.id.header_no);
        bill_icon = findViewById(R.id.bills_menu_image);
        bills = findViewById(R.id.bills_menu);
        stats_icon = findViewById(R.id.stats_menu_image);
        stats = findViewById(R.id.stats_menu);
        setting = findViewById(R.id.settings_menu);
        setting_icon = findViewById(R.id.settings_menu_image);
        content = findViewById(R.id.content_holder);
        main_layout = findViewById(R.id.main_layout);
        header = findViewById(R.id.header_tab);
        send = findViewById(R.id.send);
        dot = findViewById(R.id.dots);
        menu = findViewById(R.id.menu);
        New = findViewById(R.id.clear);
        clear_image = findViewById(R.id.clear_image);
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
        sum = findViewById(R.id.sum);
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

    private void toBill() {
        bills.setOnClickListener(view -> {
            Intent intent = new Intent(MainView.this, BillsActivity.class);
            invisible(menu);
            startActivity(intent);
        });
        bill_icon.setOnClickListener(view -> {
            Intent intent = new Intent(MainView.this, BillsActivity.class);
            invisible(menu);
            startActivity(intent);

        });
        name.setOnClickListener(view -> invisible(menu));
    }

    private void toStats() {
        stats.setOnClickListener(view -> {
            Intent intent = new Intent(MainView.this, Stats.class);
            startActivity(intent);
            invisible(menu);
        });
        stats_icon.setOnClickListener(view -> {
            Intent intent = new Intent(MainView.this, Stats.class);
            startActivity(intent);
            invisible(menu);
        });
        name.setOnClickListener(view -> invisible(menu));
    }

    private void invisibleMenu(EditText[] e){
        for (EditText editText:e){
            editText.setOnClickListener(v -> invisible(menu));
        }
    }

    private static void invisible(View v) {
        v.setVisibility(View.GONE);
    }

    private static void visible(View v) {
        v.setVisibility(View.VISIBLE);
    }

    private void newBill() {
        New.setOnClickListener(view -> {
            EditText[] edit = {name, ed1, am1, p1, ed2, am2, p2, ed3, am3, p3, ed4, am4, p4, ed5, am5, p5, ed6, am6, p6, ed7, am7, p7, ed8, am8, p8, ed9, am9, p9, ed10, am10, p10, ed11, am11, p11, ed12, am12, p12, ed13, am13, p13, ed14, am14, p14, ed15, am15, p15};
            TextView[] tv = {product, total1, total2, total3, total4, total5, total6, total7, total8, total9, total10, total11, total12, total13, total14, total15};
            clear(edit);
            clearText(tv);
            invisible(menu);
        });

        clear_image.setOnClickListener(view -> {
            EditText[] edit = {name, ed1, am1, p1, ed2, am2, p2, ed3, am3, p3, ed4, am4, p4, ed5, am5, p5, ed6, am6, p6, ed7, am7, p7, ed8, am8, p8, ed9, am9, p9, ed10, am10, p10, ed11, am11, p11, ed12, am12, p12, ed13, am13, p13, ed14, am14, p14, ed15, am15, p15};
            TextView[] tv = {product, total1, total2, total3, total4, total5, total6, total7, total8, total9, total10, total11, total12, total13, total14, total15};
            clear(edit);
            clearText(tv);
            invisible(menu);
        });
    }

    private static void clear(EditText[] e) {
        for (EditText ed : e) {
            ed.setText("");
        }
    }

    private static void clearText(TextView[] t) {
        for (TextView tv : t) {
            tv.setText("");
        }
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

    String to(EditText e) {
        return e.getText().toString();
    }

    String to(TextView e) {
        return e.getText().toString();
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
            String header = "ItemName" + " | " + "Amount" + " | " + "Price   " + " | " + "Total";
            if (is_full(name)) {
                Intent reply = new Intent();
                if (full1) {
                    double row1 = add(am1, p1);
                    total1.setText(String.valueOf(row1));
                    product.setText(String.valueOf(row1));
                    /*DatabaseHelper helper = new DatabaseHelper(MainView.this);
                    helper.addBills(name.getText().toString(), ed1.getText().toString(), am1.getText().toString(), p1.getText().toString(), total1.getText().toString(), date, time);*/
                    Bill_model model1 = new Bill_model(name.getText().toString(), ed1.getText().toString(), am1.getText().toString(), p1.getText().toString(), total1.getText().toString(), date, time);
                    BillViewModel.insert(model1);
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n\n\t\t\t\t\t\tTotal = " + to(total1);
                    send();
                }
                if (full2) {
                    double row2 = add(am2, p2);
                    total2.setText(String.valueOf(row2));
                    product.setText(String.valueOf(row2));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n\n\t\t\t\t\t\tTotal = " + to(total2);
                    send();
                }
                if (full3) {
                    double row3 = add(am3, p3);
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row3));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n\n\t\t\t\t\t\tTotal = " + to(total3);
                    send();
                }
                if (full4) {
                    double row4 = add(am4, p4);
                    total4.setText(String.valueOf(row4));
                    product.setText(String.valueOf(row4));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n\n\t\t\t\t\t\tTotal = " + to(total4);
                    send();
                }
                if (full5) {
                    double row5 = add(am5, p5);
                    total5.setText(String.valueOf(row5));
                    product.setText(String.valueOf(row5));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n\n\t\t\t\t\t\tTotal = " + to(total5);
                    send();
                }
                if (full6) {
                    double row6 = add(am6, p6);
                    total6.setText(String.valueOf(row6));
                    product.setText(String.valueOf(row6));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n\n\t\t\t\t\t\tTotal = " + to(total6);
                    send();
                }
                if (full7) {
                    double row7 = add(am7, p7);
                    total7.setText(String.valueOf(row7));
                    product.setText(String.valueOf(row7));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "7." + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n\n\t\t\t\t\t\tTotal = " + to(total7);
                    send();
                }
                if (full8) {
                    double row8 = add(am8, p8);
                    total8.setText(String.valueOf(row8));
                    product.setText(String.valueOf(row8));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "8. " + spacing(to(ed8)) + " |  " + spacing(to(am8)) + " | " + spacing(to(p8)) + " | " + spacing(to(total8)) + "\n\n\t\t\t\t\t\tTotal = " + to(total8);
                    send();
                }
                if (full9) {
                    double row9 = add(am9, p9);
                    total9.setText(String.valueOf(row9));
                    product.setText(String.valueOf(row9));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "9. " + spacing(to(ed9)) + " |  " + spacing(to(am9)) + " | " + spacing(to(p9)) + " | " + spacing(to(total9)) + "\n\n\t\t\t\t\t\tTotal = " + to(total9);
                    send();
                }
                if (full10) {
                    double row10 = add(am10, p10);
                    total10.setText(String.valueOf(row10));
                    product.setText(String.valueOf(row10));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "10." + spacing(to(ed10)) + "|  " + spacing(to(am10)) + " | " + spacing(to(p10)) + " | " + spacing(to(total10)) + "\n\n\t\t\t\t\t\tTotal = " + to(total10);
                    send();
                }
                if (full11) {
                    double row11 = add(am11, p11);
                    total11.setText(String.valueOf(row11));
                    product.setText(String.valueOf(row11));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "11." + spacing(to(ed11)) + "|  " + spacing(to(am11)) + " | " + spacing(to(p11)) + " | " + spacing(to(total11)) + "\n\n\t\t\t\t\t\tTotal = " + to(total11);
                    send();
                }
                if (full12) {
                    double row12 = add(am12, p12);
                    total12.setText(String.valueOf(row12));
                    product.setText(String.valueOf(row12));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "12." + spacing(to(ed12)) + "|  " + spacing(to(am12)) + " | " + spacing(to(p12)) + " | " + spacing(to(total12)) + "\n\n\t\t\t\t\t\tTotal = " + to(total12);
                    send();
                }
                if (full13) {
                    double row13 = add(am13, p13);
                    total13.setText(String.valueOf(row13));
                    product.setText(String.valueOf(row13));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "13." + spacing(to(ed13)) + "|  " + spacing(to(am13)) + " | " + spacing(to(p13)) + " | " + spacing(to(total13)) + "\n\n\t\t\t\t\t\tTotal = " + to(total13);
                    send();
                }
                if (full14) {
                    double row14 = add(am14, p14);
                    total14.setText(String.valueOf(row14));
                    product.setText(String.valueOf(row14));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "14." + spacing(to(ed14)) + "|  " + spacing(to(am14)) + " | " + spacing(to(p14)) + " | " + spacing(to(total14)) + "\n\n\t\t\t\t\t\tTotal = " + to(total14);
                    send();
                }
                if (full15) {
                    double row15 = add(am15, p15);
                    total15.setText(String.valueOf(row15));
                    product.setText(String.valueOf(row15));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "15." + spacing(to(ed15)) + "|  " + spacing(to(am15)) + " | " + spacing(to(p15)) + " | " + spacing(to(total15)) + "\n\n\t\t\t\t\t\tTotal = " + to(total15);
                    send();
                }
                if (full1 && full2) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    product.setText(String.valueOf(row1 + row2));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
                }
                if (full1 && full2 && full3) {
                    double row1 = add(am1, p1);
                    double row2 = add(am2, p2);
                    double row3 = add(am3, p3);
                    total1.setText(String.valueOf(row1));
                    total2.setText(String.valueOf(row2));
                    total3.setText(String.valueOf(row3));
                    product.setText(String.valueOf(row1 + row2 + row3));
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n" + "7." + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n" + "7." + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n" + "8. " + spacing(to(ed8)) + " |  " + spacing(to(am8)) + " | " + spacing(to(p8)) + " | " + spacing(to(total8)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n" + "7. " + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n" + "8. " + spacing(to(ed8)) + " |  " + spacing(to(am8)) + " | " + spacing(to(p8)) + " | " + spacing(to(total8)) + "\n" + "9. " + spacing(to(ed9)) + " |  " + spacing(to(am9)) + " | " + spacing(to(p9)) + " | " + spacing(to(total9)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n" + "7." + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n" + "8. " + spacing(to(ed8)) + " |  " + spacing(to(am8)) + " | " + spacing(to(p8)) + " | " + spacing(to(total8)) + "\n" + "9. " + spacing(to(ed9)) + " |  " + spacing(to(am9)) + " | " + spacing(to(p9)) + " | " + spacing(to(total9)) + "\n" + "10." + spacing(to(ed10)) + "|  " + spacing(to(am10)) + " | " + spacing(to(p10)) + " | " + spacing(to(total10)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n" + "7." + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n" + "8. " + spacing(to(ed8)) + " |  " + spacing(to(am8)) + " | " + spacing(to(p8)) + " | " + spacing(to(total8)) + "\n" + "9. " + spacing(to(ed9)) + " |  " + spacing(to(am9)) + " | " + spacing(to(p9)) + " | " + spacing(to(total9)) + "\n" + "10." + spacing(to(ed10)) + "|  " + spacing(to(am10)) + " | " + spacing(to(p10)) + " | " + spacing(to(total10)) + "\n" + "11." + spacing(to(ed11)) + "|  " + spacing(to(am11)) + " | " + spacing(to(p11)) + " | " + spacing(to(total11)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n" + "7." + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n" + "8. " + spacing(to(ed8)) + " |  " + spacing(to(am8)) + " | " + spacing(to(p8)) + " | " + spacing(to(total8)) + "\n" + "9. " + spacing(to(ed9)) + " |  " + spacing(to(am9)) + " | " + spacing(to(p9)) + " | " + spacing(to(total9)) + "\n" + "10." + spacing(to(ed10)) + "|  " + spacing(to(am10)) + " | " + spacing(to(p10)) + " | " + spacing(to(total10)) + "\n" + "11." + spacing(to(ed11)) + "|  " + spacing(to(am11)) + " | " + spacing(to(p11)) + " | " + spacing(to(total11)) + "\n" + "12. " + spacing(to(ed12)) + "|  " + spacing(to(am12)) + " | " + spacing(to(p12)) + " | " + spacing(to(total12)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n" + "7." + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n" + "8. " + spacing(to(ed8)) + " |  " + spacing(to(am8)) + " | " + spacing(to(p8)) + " | " + spacing(to(total8)) + "\n" + "9. " + spacing(to(ed9)) + " |  " + spacing(to(am9)) + " | " + spacing(to(p9)) + " | " + spacing(to(total9)) + "\n" + "10." + spacing(to(ed10)) + "|  " + spacing(to(am10)) + " | " + spacing(to(p10)) + " | " + spacing(to(total10)) + "\n" + "11." + spacing(to(ed11)) + "|  " + spacing(to(am11)) + " | " + spacing(to(p11)) + " | " + spacing(to(total11)) + "\n" + "12." + spacing(to(ed12)) + "|  " + spacing(to(am12)) + " | " + spacing(to(p12)) + " | " + spacing(to(total12)) + "\n" + "13." + spacing(to(ed13)) + "|  " + spacing(to(am13)) + " | " + spacing(to(p13)) + " | " + spacing(to(total13)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n" + "7." + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n" + "8. " + spacing(to(ed8)) + " |  " + spacing(to(am8)) + " | " + spacing(to(p8)) + " | " + spacing(to(total8)) + "\n" + "9. " + spacing(to(ed9)) + " |  " + spacing(to(am9)) + " | " + spacing(to(p9)) + " | " + spacing(to(total9)) + "\n" + "10." + spacing(to(ed10)) + "|  " + spacing(to(am10)) + " | " + spacing(to(p10)) + " | " + spacing(to(total10)) + "\n" + "11." + spacing(to(ed11)) + "|  " + spacing(to(am11)) + " | " + spacing(to(p11)) + " | " + spacing(to(total11)) + "\n" + "12." + spacing(to(ed12)) + "|  " + spacing(to(am12)) + " | " + spacing(to(p12)) + " | " + spacing(to(total12)) + "\n" + "13." + spacing(to(ed13)) + "|  " + spacing(to(am13)) + " | " + spacing(to(p13)) + " | " + spacing(to(total13)) + "\n" + "14." + spacing(to(ed14)) + "|  " + spacing(to(am14)) + " | " + spacing(to(p14)) + " | " + spacing(to(total14)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
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
                    this.msg = "\t\t\t\t\t\t\t\t\t Date:" + date + "\n\t\t\t\t\t\t\t\t\t Time:" + time + "\n\nCustomerName: " + to(name).toUpperCase() + "\n\n" + header + "\n\n" + "1. " + spacing(to(ed1)) + " |  " + spacing(to(am1)) + " | " + spacing(to(p1)) + " | " + spacing(to(total1)) + "\n" + "2. " + spacing(to(ed2)) + " |  " + spacing(to(am2)) + " | " + spacing(to(p2)) + " | " + spacing(to(total2)) + "\n" + "3. " + spacing(to(ed3)) + " |  " + spacing(to(am3)) + " | " + spacing(to(p3)) + " | " + spacing(to(total3)) + "\n" + "4. " + spacing(to(ed4)) + " |  " + spacing(to(am4)) + " | " + spacing(to(p4)) + " | " + spacing(to(total4)) + "\n" + "5. " + spacing(to(ed5)) + " |  " + spacing(to(am5)) + " | " + spacing(to(p5)) + " | " + spacing(to(total5)) + "\n" + "6. " + spacing(to(ed6)) + " |  " + spacing(to(am6)) + " | " + spacing(to(p6)) + " | " + spacing(to(total6)) + "\n" + "7." + spacing(to(ed7)) + " |  " + spacing(to(am7)) + " | " + spacing(to(p7)) + " | " + spacing(to(total7)) + "\n" + "8. " + spacing(to(ed8)) + " |  " + spacing(to(am8)) + " | " + spacing(to(p8)) + " | " + spacing(to(total8)) + "\n" + "9. " + spacing(to(ed9)) + " |  " + spacing(to(am9)) + " | " + spacing(to(p9)) + " | " + spacing(to(total9)) + "\n" + "10." + spacing(to(ed10)) + "|  " + spacing(to(am10)) + " | " + spacing(to(p10)) + " | " + spacing(to(total10)) + "\n" + "11." + spacing(to(ed11)) + "|  " + spacing(to(am11)) + " | " + spacing(to(p11)) + " | " + spacing(to(total11)) + "\n" + "12." + spacing(to(ed12)) + "|  " + spacing(to(am12)) + " | " + spacing(to(p12)) + " | " + spacing(to(total12)) + "\n" + "13." + spacing(to(ed13)) + "|  " + spacing(to(am13)) + " | " + spacing(to(p13)) + " | " + spacing(to(total13)) + "\n" + "14." + spacing(to(ed14)) + "|  " + spacing(to(am14)) + " | " + spacing(to(p14)) + " | " + spacing(to(total14)) + "\n" + "15." + spacing(to(ed15)) + "|  " + spacing(to(am15)) + " | " + spacing(to(p15)) + " | " + spacing(to(total15)) + "\n\n\t\t\t\t\t\tTotal = " + to(product);
                    send();
                } else if (full1||full2||full3||full4||full5||full6||full7||full8||full9||full10||full11||full12||full13||full14||full15){
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    boolean is_dark = preferences.getBoolean("enable_night", false);
                    if (is_dark) {
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.saved_toast_night, findViewById(R.id.saved_layout));
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.BOTTOM, 0, (int) (view.getY() / 2) - (int) (view.getX()) + 150);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();
                    } else {
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.saved_toast, findViewById(R.id.saved_layout_day));
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.BOTTOM, 0, (int) (view.getY() / 2) - (int) (view.getX()) + 150);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();
                    }

                }
            } else {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                boolean is_dark = preferences.getBoolean("enable_night", false);
                if (is_dark) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_night, findViewById(R.id.toast_layout));
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, (int) (view.getY() / 2) - (int) (view.getX()) + 200);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                } else {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast, findViewById(R.id.toast_layout_day));
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, (int) (view.getY() / 2) - (int) (view.getX()) + 200);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                }

            }

        });
    }

    private void dotClick() {
        dot.setOnClickListener(view -> visible(menu));
        main_layout.setOnClickListener(view -> invisible(menu));
        content.setOnClickListener(view -> invisible(menu));
        send.setOnClickListener(view -> invisible(menu));
        header.setOnClickListener(view -> invisible(menu));
    }

    private void settings() {
        setting.setOnClickListener(view -> {
            Intent in = new Intent(MainView.this, Settings.class);
            startActivity(in);
            invisible(menu);
        });
        setting_icon.setOnClickListener(view -> {
            Intent in = new Intent(MainView.this, Settings.class);
            startActivity(in);
            invisible(menu);
        });
    }

    @Override
    protected void onResume() {
        dark();
        super.onResume();
    }
}





