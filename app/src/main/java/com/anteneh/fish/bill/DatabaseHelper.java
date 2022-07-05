package com.anteneh.fish.bill;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;
    public static final String DATABASE_NAME = "Bill.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "bills";
    public static final String COLUMN_TITLE = "customername";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ITEM = "items";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PRODUCT = "total";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_ITEM + " TEXT," +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_PRODUCT + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBills(String title, String item, String amount, String price, String total, String date, String time) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_ITEM, item);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_PRODUCT, total);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TIME, time);

        long insert = database.insert(TABLE_NAME, null, cv);

        if (insert == -1) {
            Log.d("files","bill not Saved");
        } else {
            Log.d("files","bill  Saved");
        }
    }

    Cursor readAll() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public void update(String title, String item, String amount, String price, String total, String date, String time, String id) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_ITEM, item);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_PRODUCT, total);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TIME, time);

        long insert = database.update(TABLE_NAME, cv, "id=?", new String[]{id});

        if (insert == -1) {
            Log.d("files","bill not Updated");
        } else {
            Log.d("files","Bill Updated");
        }
    }

    public void removeSingle(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.delete(TABLE_NAME, "id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
        }
    }
}




