package com.coffee;

/**
 * Created by Vladimir Salguero on 25/09/2015.
 */
public class CafeMenu {

    private String mName;
    private double mPrice;
    private boolean mAtari;

    public CafeMenu(String name, double price) {
        mName = name;
        mPrice = price;
        mAtari = false;
    }

    public String getName() {
        return mName;
    }


    public double getPrice() {
        return mPrice;
    }

    public boolean getAtari() {
        return mAtari;
    }

}