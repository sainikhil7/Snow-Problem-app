package com.example.slip_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Login extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    Button button4;
    Button button5;
    String add;
    String Latlon;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        button4 = (Button)findViewById(R.id.button2);




        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),First.class);
                startActivity(i);
            }
        });


        button5 = (Button)findViewById(R.id.button6);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Rating.class);
                startActivity(i);
            }
        });


    }
}
