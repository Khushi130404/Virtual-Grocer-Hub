package com.example.virtualgrocerhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutAdapter extends ArrayAdapter
{
    Context cont;
    int resource;
    List<ItemBill> bill;
    List<Integer> qty;
    List<Grocery> gc;

    public CheckoutAdapter(Context cont, int resource, List<ItemBill> bill, List<Integer> qty, List<Grocery> gc)
    {
        super(cont, resource, bill);
        this.cont = cont;
        this.resource = resource;
        this.bill = bill;
        this.qty = qty;
        this.gc = gc;
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
        ImageView imgBin = view.findViewById(R.id.imgBin);

        tvItem.setText(item.getItem());
        tvQty.setText(""+item.getQty());
        tvRate.setText(""+item.getRate());
        tvAmount.setText(""+(item.getQty()*item.getRate()));

        imgBin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                for(int i=0; i<gc.size(); i++)
                {
                    if(gc.get(i).getgId().equals(bill.get(position).getGId()))
                    {
                        ShopGroceryActivity shop = new ShopGroceryActivity();
                        CheckoutActivity check = new CheckoutActivity();
                        check.amount = Integer.parseInt(shop.tvAmount.getText().toString())-(qty.get(i)*bill.get(position).getRate());
                        shop.tvAmount.setText(""+check.amount);
                        check.tvAmount.setText("Amount : ₹"+check.amount);
                        check.tvTax.setText("Tax : ₹"+(int)(0.18*check.amount));
                        check.tvFinalBill.setText("Final Bill : ₹"+(int)(1.18*check.amount));
                        qty.set(i,0);
                        shop.ad.notifyDataSetChanged();
                        break;
                    }
                }
                bill.remove(position).setQty(0);

                notifyDataSetChanged();
            }
        });
        return view;
    }
}