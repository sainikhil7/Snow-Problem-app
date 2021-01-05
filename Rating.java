package com.example.slip_admin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Rating extends AppCompatActivity {

    Button Upload;
    ImageView img;
    private Uri filePath;
    private int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);


        Spinner spinner3 = findViewById(R.id.spinner3);
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("0:Bad");
        arrayList2.add("1:Not Bad");
        arrayList2.add("2:Average");
        arrayList2.add("3:Better");
        arrayList2.add("4:Good");
        arrayList2.add("5:Excellent");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(arrayAdapter2);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                //nikhil
                //Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Button button6;
        button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

        Upload = (Button) findViewById(R.id.button4);
        img  = (ImageView)findViewById(R.id.imageView2);
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });



    }
    private void FileChooser(){
        /***
         Intent intent = new Intent();
         intent.setType("image/*");
         intent.setAction(Intent.ACTION_GET_CONTENT);
         startActivityForResult(intent,1);


         Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
         intent.addCategory(Intent.CATEGORY_OPENABLE);
         intent.setType("image/*");
         startActivityForResult(intent, 1);
         ***/
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST = 71);



    }
    @Override
    /***
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if(requestCode == 1 && requestCode == RESULT_OK && data!= null && data.getData()!=null){
     img.setImageURI(imguri);
     }
     }
     ***/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
