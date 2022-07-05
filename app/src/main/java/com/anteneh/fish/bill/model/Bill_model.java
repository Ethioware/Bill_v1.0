package com.anteneh.fish.bill.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Bill_Table")
public class Bill_model {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Customer")
    private String title;
    @ColumnInfo(name = "Item")
    private String item;
    @ColumnInfo(name = "Amount")
    private String amount;
    @ColumnInfo(name = "Price")
    private String price;
    @ColumnInfo(name = "Total")
    private String product;
    @ColumnInfo(name = "Date")
    private String date;
    @ColumnInfo(name = "Time")
    private String time;

    public Bill_model(@NonNull String title,@NonNull String item,@NonNull String amount,@NonNull String price,@NonNull String product,@NonNull String date,@NonNull String time) {
        this.title = title;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.product = product;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}