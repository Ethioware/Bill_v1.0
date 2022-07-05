package com.anteneh.fish.bill.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.anteneh.fish.bill.model.Bill_model;

import java.util.List;
@Dao
public interface BillDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Bill_model model);
    @Query("DELETE FROM Bill_Table")
    void deleteAll();
    @Query("SELECT * FROM Bill_Table ORDER BY Time ASC")
    LiveData<List<Bill_model>> getAllBills();
}