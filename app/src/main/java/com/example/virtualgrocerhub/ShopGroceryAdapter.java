package com.example.virtualgrocerhub;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.List;

public class ShopGroceryAdapter extends ArrayAdapter
{

    Context cont;
    int resource;
    List<Grocery> grocery;
    TextView tvAmount;

    public ShopGroceryAdapter(Context cont, int resource, List grocery,TextView tvAmount)
    {
        super(cont, resource, grocery);
        this.cont = cont;
        this.grocery = grocery;
        this.resource = resource;
        this.tvAmount = tvAmount;
    }

    @Override
    public View getView(final int position, View convetView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(cont);
        View view = inflater.inflate(resource,null,false);
        Grocery gc = grocery.get(position);

        Button btPlus = view.findViewById(R.id.btPlus);
        Button btMinus = view.findViewById(R.id.btMinus);
        ImageView imgGrocery = view.findViewById(R.id.imgGrocery);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvPrice = view.findViewById(R.id.tvPrice);
        TextView tvUnit = view.findViewById(R.id.tvUnit);
        TextView tvQty = view.findViewById(R.id.tvQty);

        tvName.setText(gc.getgName());
        tvPrice.setText(""+gc.getPrice());
        tvUnit.setText(gc.getUnit());

        Glide.with(cont)
                .load(gc.getImage())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgGrocery);

        btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int qty = Integer.parseInt(tvQty.getText().toString());
                if(qty>0)
                {
                    qty--;
                    tvQty.setText(""+qty);
                    tvAmount.setText(""+(Integer.parseInt(tvAmount.getText().toString())-gc.getPrice()));
                }
            }
        });

        btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int qty = Integer.parseInt(tvQty.getText().toString());
                qty++;
                tvQty.setText(""+qty);
                tvAmount.setText(""+(Integer.parseInt(tvAmount.getText().toString())+gc.getPrice()));
            }
        });

        return view;
    }
}
