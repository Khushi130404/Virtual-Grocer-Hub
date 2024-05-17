package com.example.virtualgrocerhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutAdapter extends ArrayAdapter
{
    Context cont;
    int resource;
    List<ItemBill> bill;


    public CheckoutAdapter(Context cont, int resource, List<ItemBill> bill)
    {
        super(cont, resource, bill);
        this.cont = cont;
        this.resource = resource;
        this.bill = bill;
    }

    @Override
    public View getView(final int position, View convetView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(cont);
        View view = inflater.inflate(resource,null,false);
        ItemBill item = bill.get(position);

        TextView tvItem = view.findViewById(R.id.tvItem);
        TextView tvQty = view.findViewById(R.id.tvQty);
        TextView tvRate = view.findViewById(R.id.tvRate);
        TextView tvAmount = view.findViewById(R.id.tvAmount);

        tvItem.setText(item.getItem());
        tvQty.setText(""+item.getQty());
        tvRate.setText(""+item.getRate());
        tvAmount.setText(""+(item.getQty()*item.getRate()));
        return view;
    }
}