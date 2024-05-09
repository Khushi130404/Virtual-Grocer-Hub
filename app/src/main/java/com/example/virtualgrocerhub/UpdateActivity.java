package com.example.virtualgrocerhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.google.firebase.storage.UploadTask;

import java.util.List;

public class UpdateActivity extends Activity
{
    EditText etChangeName,etChangeUnit,etChangePrice,etChangeQty;
    Button btChangeGrocery,btChangeCancel,btChangeImage;
    ImageView imgChange;
    DatabaseReference dbRef;
    Uri imagePath;
    boolean flag = false;
    String imgDel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent ii = getIntent();
        String gId = ii.getStringExtra("gId");

        etChangeName = findViewById(R.id.etUpdateName);
        etChangePrice = findViewById(R.id.etUpdatePrice);
        etChangeQty = findViewById(R.id.etUpdateQty);
        etChangeUnit = findViewById(R.id.etUpdateUnit);
        imgChange = findViewById(R.id.imgUpdate);
        btChangeCancel = findViewById(R.id.btUpdateCancel);
        btChangeImage = findViewById(R.id.btUpdateImage);
        btChangeGrocery = findViewById(R.id.btUpdateGrocery);

        dbRef = FirebaseDatabase.getInstance().getReference("Grocery");
        Query updateQ = dbRef.child(gId);



        updateQ.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    Grocery gc = snapshot.getValue(Grocery.class);
                    imgDel = gc.getImage();

                    if(!gc.equals(null))
                    {
                        etChangeName.setText(gc.getgName());
                        etChangePrice.setText(""+gc.getPrice());
                        etChangeQty.setText(""+gc.getQty());
                        etChangeUnit.setText(gc.getUnit());

                        imgChange.setBackground(null);
                        Glide.with(getApplicationContext())
                                .load(gc.getImage())
                                .override(800,400)
                                .fitCenter()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imgChange);
                        imagePath = Uri.parse(imgChange.toString());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Null",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Byeee",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(getApplicationContext(),"Error : "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        btChangeImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,111);
            }
        });

        btChangeGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DatabaseReference dbChild = dbRef.child(gId);
                StorageReference store = FirebaseStorage.getInstance().getReferenceFromUrl("gs://fir-khushi.appspot.com/Grocery");
                final StorageReference getStore = store.child(etChangeName.getText().toString());
                final StorageReference storeRef = FirebaseStorage.getInstance().getReferenceFromUrl(imgDel);

                dbChild.child("gName").setValue(etChangeName.getText().toString());
                dbChild.child("price").setValue(Integer.parseInt(etChangePrice.getText().toString()));
                dbChild.child("qty").setValue(Integer.parseInt(etChangeQty.getText().toString()));
                dbChild.child("unit").setValue(etChangeUnit.getText().toString());

                if(flag)
                {
                    storeRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused)
                        {
                            Toast.makeText(getApplicationContext(),"Item successfully updated...!",Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"Updation Failed...!",Toast.LENGTH_LONG).show();
                        }
                    });

                    getStore.putFile(imagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            getStore.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri)
                                {
                                    dbChild.child("image").setValue(uri.toString());
                                    etChangeName.setText("");
                                    etChangeUnit.setText("");
                                    etChangeQty.setText("");
                                    etChangePrice.setText("");
                                    imgChange.setBackground(getDrawable(R.drawable.edittext1));
                                    imgChange.setImageDrawable(null);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });

                }
                else
                {
                    etChangeName.setText("");
                    etChangeUnit.setText("");
                    etChangeQty.setText("");
                    etChangePrice.setText("");
                    imgChange.setBackground(getDrawable(R.drawable.edittext1));
                    imgChange.setImageDrawable(null);
                }
            }
        });

        btChangeCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                etChangeName.setText("");
                etChangeUnit.setText("");
                etChangeQty.setText("");
                etChangePrice.setText("");
                imgChange.setBackground(getDrawable(R.drawable.edittext1));
                imgChange.setImageDrawable(null);
            }
        });
    }

    @Override
    public void onActivityResult(int reqCode,int resCode,Intent data)
    {
        if(reqCode==111 && resCode==RESULT_OK)
        {
            imagePath = data.getData();
            imgChange.setBackground(null);
            imgChange.setImageURI(imagePath);
            flag = true;
        }
    }
}