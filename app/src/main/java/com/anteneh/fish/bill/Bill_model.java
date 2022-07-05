package com.anteneh.fish.bill;

public class Bill_model {

    private String id;
    private String title;
    private String item;
    private String amount;
    private String price;
    private String product;
    private String date;
    private String time;

    public Bill_model(String id, String title, String item, String amount, String price, String product, String date, String time) {
        this.id = id;
        this.title = title;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.product = product;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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