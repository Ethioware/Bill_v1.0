package com.anteneh.fish.bill.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anteneh.fish.bill.data.BillRepo;

import java.util.List;

public class BillViewModel extends AndroidViewModel {

    public static BillRepo repo;
    public final LiveData<List<Bill_model>> bills;
    //Logging 3:51
    public BillViewModel(@NonNull Application application) {
        super(application);
        repo = new BillRepo(application);
        bills = repo.getAllBills();
    }
    // an api to transfer info of repo to views
    public LiveData<List<Bill_model>> getAll(){return bills;}
    public static void insert(Bill_model model){repo.insert(model);}
}