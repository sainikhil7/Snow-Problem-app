package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import java.io.IOException;
import java.util.UUID;


public class HomeActivity extends AppCompatActivity {
    Button get_place,Submit,Upload, sub;
    private TextView textView;
    ImageView img;
    public String LatLong;
    public String address;
    EditText email;
    EditText name;
    StorageReference mStorageRef;
    private StorageTask uploadTask;
    public Uri imguri;
    private Uri filePath;
    public static final String NAME = "Name";
    public static final String EMAIL = "Email";
    DatabaseReference databaseArtists;
    ListView listviewArtists;
    List<Artist> artistList;
    String add;
    String Latlon;
    String Cars,Time;





    StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 71;

    int PLACE_PICKER_REQUEST = 1;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        storageReference = FirebaseStorage.getInstance().getReference("Nikhil Images");
        databaseArtists = FirebaseDatabase.getInstance().getReference("Nikhil App");
        mStorageRef = FirebaseStorage.getInstance().getReference("Nikhil Images");
        textView = (TextView)findViewById(R.id.textView);
        get_place = (Button)findViewById(R.id.location);
        Submit = (Button)findViewById(R.id.submit);
        Upload = (Button) findViewById(R.id.ImageUpload);

        name = (EditText)findViewById(R.id.name);
        img  = (ImageView)findViewById(R.id.imageView);
        final String[] Error = new String[1];
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });

        Button button7;
        button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6 or above");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Cars = tutorialsName;
                //nikhil
                //Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
                //nikhil
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("10 Min");
        arrayList1.add("20 Min");
        arrayList1.add("30 Min");
        arrayList1.add("40 Min");
        arrayList1.add("50 Min");
        arrayList1.add(" 1 Hour or above");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter1);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Time = tutorialsName;
                //nikhil
                //Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
                //nikhil
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(uploadTask != null && uploadTask.isInProgress()){
                        Toast.makeText(HomeActivity.this,"Uploading in Progress",Toast.LENGTH_LONG).show();
                    }
                    else{
                        FileUploader();}
                    addArtist();
                }

                catch (Exception e){
                    Error[0] = e.toString();
                }


                Intent homeIntent = new Intent(HomeActivity.this,MainActivity.class);

                startActivity(homeIntent);

            }
        });

        get_place  = findViewById(R.id.location);
        get_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(HomeActivity.this,MapsActivity.class);


                startActivity(homeIntent);

                finish();


            }
        });
        Intent homeIntent = getIntent();
        address = homeIntent.getStringExtra("address");
        LatLong = homeIntent.getStringExtra("Coordinates");
        add = address;
        Latlon = LatLong;
        textView.setText("Address: "+address);

    }


    private void addArtist(){

        String Name = name.getText().toString().trim();

        //if(TextUtils.isEmpty(Name)) {
            String id = databaseArtists.push().getKey();

            Artist artist = new Artist(id,Name,add,Latlon,Cars,Time);
            databaseArtists.child(id). setValue(artist);

             //nikhil
            //Toast.makeText(this,"Name ,Email, address and Coordinates added", Toast.LENGTH_LONG).show();
            //nikhil
        //}
        //else{
          //  Toast.makeText(this,"Enter Name, Email, address and Location",Toast.LENGTH_LONG).show();
        //}
    }
    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void FileUploader(){

        /***

         StorageReference Ref = mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));
         uploadTask=Ref.putFile(imguri)
         .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        // Get a URL to the uploaded content
        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
        Toast.makeText(HomeActivity.this,"image Uploaded Successfully",Toast.LENGTH_LONG).show();
        }
        })
         .addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception exception) {
        // Handle unsuccessful uploads
        // ...
        }
        });

         ***/
        try{

            if (filePath != null) {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

                //Toast.makeText(HomeActivity.this, "Here", Toast.LENGTH_SHORT).show();
                //StorageReference ref = storageReference.child(System.currentTimeMillis()+".jpg");

                ref.putFile(filePath)

                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        DatabaseReference imagestore = FirebaseDatabase.getInstance().getReference().child("Image");
                                        HashMap<String,String> hashMap = new HashMap<>();
                                        hashMap.put("imageurl",String.valueOf(uri));


                                        imagestore.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(HomeActivity.this,"Finally COmpleted",Toast.LENGTH_LONG);

                                            }
                                        });

                                    }
                                });

                                progressDialog.dismiss();
                                Toast.makeText(HomeActivity.this, "Request Sent", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(HomeActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded " + (int) progress + "%");



                            }
                        });




            } else {
                Toast.makeText(HomeActivity.this, "File Path Empty ", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(HomeActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

        }

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
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);



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




    /**
     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (requestCode == PLACE_PICKER_REQUEST) {
     if (resultCode == RESULT_OK) {
     Place place = PlacePicker.getPlace(data, this);
     StringBuilder stringBuilder = new StringBuilder();

     String latitude = String.valueOf(place.getLatLng().latitude);
     String longitude = String.valueOf(place.getLatLng().longitude);
     stringBuilder.append("LATITUDE :");
     stringBuilder.append(latitude);
     stringBuilder.append("\n");
     stringBuilder.append("LONGITUDE :");
     stringBuilder.append(longitude);
     Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();


     }
     }
     }
     ***/

}



