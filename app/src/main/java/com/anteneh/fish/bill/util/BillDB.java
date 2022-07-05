package com.anteneh.fish.bill.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.anteneh.fish.bill.data.BillDao;
import com.anteneh.fish.bill.model.Bill_model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Bill_model.class},version = 1,exportSchema = false)
public abstract class BillDB extends RoomDatabase {

    private static final String DATABASE_NAME = "Bill.DB";
    private static final int THREAD = 4;
    public abstract BillDao dao();
    private static volatile BillDB databaseInstance;
    public static final ExecutorService writeTo =
            Executors.newFixedThreadPool(THREAD);
    public static BillDB getInstance(final Context context){
        if (databaseInstance == null) {
            synchronized (BillDB.class){
                if (databaseInstance == null) {
                    databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            BillDB.class,DATABASE_NAME).addCallback(callback).build();
                }
            }
        }
        return databaseInstance;
    };

    private static final RoomDatabase.Callback callback =
            new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    writeTo.execute(()->{
                        BillDao dao = databaseInstance.dao();
                        dao.deleteAll();
                        Bill_model model = new Bill_model(" Ant man","Moya","3","5","15.0","21/6/22","5:56");
                        dao.insert(model);
                        Bill_model model2 = new Bill_model(" and the wasp","Moya","3","5","15.0","21/6/22","5:50");
                        dao.insert(model2);
                    });
                }
            };
}