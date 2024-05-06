package com.example.virtualgrocerhub;

public class Grocery
{
    String gId;
    String gName;
    int price;
    int qty;
    String image;
    String unit;

    public Grocery()
    {}

    public Grocery(String gId, String gName, int price, int qty, String image, String unit) {
        this.gId = gId;
        this.gName = gName;
        this.price = price;
        this.qty = qty;
        this.image = image;
        this.unit = unit;
    }

    public String getgId() {
        return gId;
    }

    public void setgId(String gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
