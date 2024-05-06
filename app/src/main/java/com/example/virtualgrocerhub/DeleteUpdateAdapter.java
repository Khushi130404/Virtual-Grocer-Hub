package com.example.virtualgrocerhub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class DeleteUpdateAdapter extends ArrayAdapter<Grocery>
{
    Context cont;
    int grocery_item;
    List<Grocery> grocery;
    Button btDelete,btUpdate;
    DatabaseReference dbRef;

    public DeleteUpdateAdapter(Context cont, int grocery_item, List<Grocery> grocery)
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
        btDelete = view.findViewById(R.id.btDelete);
        btUpdate = view.findViewById(R.id.btUpdate);

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

        btDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showDialog(position);
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(cont.getApplicationContext(),UpdateActivity.class);
                i.putExtra("gId",grocery.get(position).getgId());
                cont.startActivity(i);
                notifyDataSetChanged();
            }
        });

        return  view;
    }

    void showDialog(int position)
    {
        AlertDialog.Builder al = new AlertDialog.Builder(getContext());
        al.setTitle("Do you want to delete ???");

        al.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(getContext(),"No action",Toast.LENGTH_SHORT).show();
            }
        });

        al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                deleteGrocery(position);
            }
        });

        AlertDialog alert = al.create();
        alert.show();
    }

    void deleteGrocery(int position)
    {
        dbRef = FirebaseDatabase.getInstance().getReference("Grocery");
        Query delQ = dbRef.child(grocery.get(position).getgId());

        delQ.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    Toast.makeText(cont.getApplicationContext(),""+grocery.get(position).getgId(),Toast.LENGTH_LONG).show();
                    snapshot.getRef().removeValue();
                    StorageReference storeRef = FirebaseStorage.getInstance().getReferenceFromUrl(grocery.get(position).getImage());
                    storeRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused)
                        {
                            notifyDataSetChanged();
                            Toast.makeText(cont.getApplicationContext(),"Item successfully deleted...!",Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(cont.getApplicationContext(),"Deletion Failed...!",Toast.LENGTH_LONG).show();
                        }
                    });

                }
                else
                {
                    Toast.makeText(cont.getApplicationContext(),"Not found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(cont.getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
