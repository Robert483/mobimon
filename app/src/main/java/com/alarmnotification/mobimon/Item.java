package com.alarmnotification.mobimon;

import android.graphics.Bitmap;

/**
 * Created by Ryan L. Vu on 6/11/2016.
 */
public abstract class Item {

    private int    price;
    private String name;
    private String description;
    private Bitmap image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
