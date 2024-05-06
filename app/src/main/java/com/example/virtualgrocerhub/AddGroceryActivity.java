package com.example.virtualgrocerhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddGroceryActivity extends Activity {

    DatabaseReference dref;

    EditText etAddName,etAddUnit,etAddPrice,etAddQty;
    Button btAddImage,btAddGrocery,btAddCancel;
    ImageView imgAdd;

    String addName,addUnit,addImage;
    Integer addPrice,addQty;
    StorageReference store=null,getStore=null;
    Uri imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery);

        etAddName = findViewById(R.id.etAddName);
        etAddPrice = findViewById(R.id.etAddPrice);
        etAddQty = findViewById(R.id.etAddQty);
        etAddUnit = findViewById(R.id.etAddUnit);
        btAddGrocery = findViewById(R.id.btAddGrocery);
        btAddCancel = findViewById(R.id.btAddCancel);
        btAddImage = findViewById(R.id.btAddImage);
        imgAdd = findViewById(R.id.imgAdd);



        btAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,111);
            }
        });

        btAddGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    addName = etAddName.getText().toString();
                    addQty = Integer.parseInt(etAddQty.getText().toString());
                    addPrice = Integer.parseInt(etAddPrice.getText().toString());
                    addUnit = etAddUnit.getText().toString();

                    dref = FirebaseDatabase.getInstance().getReference("Grocery");
                    store = FirebaseStorage.getInstance().getReferenceFromUrl("gs://fir-khushi.appspot.com/Grocery");
                    String gid = dref.push().getKey();
                    getStore = store.child(addName);

                    getStore.putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            getStore.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                @Override
                                public void onSuccess(Uri uri)
                                {
                                    addImage = uri.toString();
                                    Grocery g = new Grocery(gid,addName,addPrice,addQty,addImage,addUnit);
                                    dref.child(gid).setValue(g);

                                    Toast.makeText(getApplicationContext(), "Item successfully added to the shop", Toast.LENGTH_SHORT).show();

                                    etAddName.setText("");
                                    etAddPrice.setText("");
                                    etAddQty.setText("");
                                    etAddUnit.setText("");
                                    imgAdd.setImageDrawable(null);
                                    imgAdd.setBackground(getDrawable(R.drawable.edittext1));
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"Error : "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Please enter appropriate values...!",Toast.LENGTH_LONG).show();
                    etAddName.setText("");
                    etAddUnit.setText("");
                    etAddPrice.setText("");
                    etAddQty.setText("");
                }
            }
        });

        btAddCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                etAddUnit.setText("");
                etAddName.setText("");
                etAddQty.setText("");
                etAddPrice.setText("");
            }
        });
    }

    @Override
    public void onActivityResult(int reqCode,int resCode,Intent data)
    {
        if(reqCode==111 && resCode==RESULT_OK)
        {
            imgPath = data.getData();
            imgAdd.setBackground(null);
            imgAdd.setImageURI(imgPath);
        }

    }

    public void addGrocery()
    {
    }
}