package com.example.virtualgrocerhub;

public class ItemBill
{
    String gId;
    String item;
    int qty;
    int rate;

    public String getItem() {
        return item;
    }

    public String getGId() {
        return gId;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setGId(String gId) {
        this.gId = gId;
    }

}
