package com.example.virtualgrocerhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CustomizeAdapter extends ArrayAdapter<Grocery>
{
    Context cont;
    int grocery_item;
    List<Grocery> grocery;

    public CustomizeAdapter(Context cont, int grocery_item, List<Grocery> grocery)
    {
        super(cont,grocery_item,grocery);
        this.cont = cont;
        this.grocery_item = grocery_item;
        this.grocery = grocery;
    }

    @Override
    public View getView(final int position, View convetView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(cont);
        View view = inflater.inflate(grocery_item,null,false);
        Grocery gc = grocery.get(position);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvPrice = view.findViewById(R.id.tvPrice);
        TextView tvQty = view.findViewById(R.id.tvQty);
        TextView tvUnit = view.findViewById(R.id.tvUnit);
        ImageView img = view.findViewById(R.id.img);

        tvName.setText(tvName.getText()+gc.getgName());
        tvPrice.setText(tvPrice.getText()+""+gc.getPrice());
        tvQty.setText(tvQty.getText()+""+gc.getQty());
        tvUnit.setText(tvUnit.getText()+gc.getUnit());

        img.setBackground(null);

        Glide.with(cont)
                .load(gc.getImage())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);

        return  view;
    }
}
