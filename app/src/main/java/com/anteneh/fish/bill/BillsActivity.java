package com.anteneh.fish.bill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class BillsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Bill_Adapter adapter;
    List<Bill_model> Bill_list;
    ImageView image;
    DatabaseHelper helper;
    RelativeLayout layout;
    EditText search_bar;
    ImageView cancel,search_draw;
    View sep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        init();
        dark();
        Bill_list = new ArrayList<>();
        helper = new DatabaseHelper(this);
        getAll();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Bill_Adapter(this, BillsActivity.this, Bill_list);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
        cancel.setOnClickListener(view -> {search_bar.setText("");});
        search_draw.setOnClickListener(view -> {Intent back = new Intent(BillsActivity.this, MainView.class);
        startActivity(back);});
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent in = new Intent(BillsActivity.this,MainView.class);
        startActivity(in);

    }

    private void dark(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean is_dark = preferences.getBoolean("enable_night", false);
        if (is_dark){
            search_bar.setBackgroundResource(R.drawable.search_dark);
            layout.setBackgroundColor(Color.DKGRAY);
            search_draw.setImageResource(R.drawable.back_dark);
            cancel.setImageResource(R.drawable.cancel_dark);
            search_bar.setHintTextColor(Color.LTGRAY);
            search_bar.setTextColor(Color.WHITE);
            sep.setBackgroundColor(Color.parseColor("#CC909090"));
        }else {
            search_bar.setBackgroundResource(R.drawable.search_bg);
            layout.setBackgroundColor(Color.WHITE);
            search_draw.setImageResource(R.drawable.back);
            cancel.setImageResource(R.drawable.cancel_search);
            search_bar.setHintTextColor(Color.parseColor("#CCA0A0A0"));
        }
    }

    void getAll() {
        Cursor cursor = helper.readAll();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "DataBase is Empty?!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Bill_list.add(new Bill_model(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6),cursor.getString(7)));
            }
        }
    }
    private void init() {
        sep = findViewById(R.id.separater);
        recyclerView = findViewById(R.id.recycler_layout);
        image = findViewById(R.id.back);
        layout = findViewById(R.id.billActivity_layout);
        search_bar = findViewById(R.id.search_bar);
        cancel = findViewById(R.id.cancel);
        search_draw = findViewById(R.id.search_draw);
    }



    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            Bill_model model = adapter.getList().get(position);
            adapter.removeBill(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar.make(layout, "Deleted successfully!", Snackbar.LENGTH_LONG).setAction("UNDO", view -> {
                adapter.restore(model, position);
                recyclerView.scrollToPosition(position);
            }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if (!(event == DISMISS_EVENT_ACTION)) {
                        DatabaseHelper helper = new DatabaseHelper(BillsActivity.this);
                        helper.removeSingle(model.getId());
                    }
                }
            });
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(BillsActivity.this);
            boolean is_dark = preferences.getBoolean("enable_night", false);
            if (is_dark){
                snackbar.setActionTextColor(Color.GREEN);
                snackbar.show();
            }else {
                snackbar.setActionTextColor(Color.parseColor("#DE24FF"));
                snackbar.show();
            }

        }
    };

    @Override
    protected void onResume() {
        dark();
        super.onResume();
    }
}





