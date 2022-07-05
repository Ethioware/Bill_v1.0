package com.anteneh.fish.bill.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.anteneh.fish.bill.model.Bill_model;
import com.anteneh.fish.bill.util.BillDB;

import java.util.List;

public class BillRepo {
    private BillDao dao;
    private LiveData<List<Bill_model>> allBills;

    public BillRepo(Application application){
        BillDB dataBase = BillDB.getInstance(application);
        dao = dataBase.dao();
        allBills = dao.getAllBills();
    }
    public LiveData<List<Bill_model>> getAllBills(){return allBills;}
    public void insert(Bill_model model){
        BillDB.writeTo.execute(()->{
            dao.insert(model);
        });
    }
}