package com.anteneh.fish.bill;

import android.app.Application;

public class Dark extends Application {
    public static final String PREF = "preference";
    public static final String CUSTOM = "custom";
    public static final String LIGHT = "light";
    public static final String DARK = "dark";
    private String custom;

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }
}
