package com.example.slip_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class First extends AppCompatActivity {
    private RadioGroup radioGroup;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        submit = (Button)findViewById(R.id.button3);

        radioGroup = (RadioGroup)findViewById(R.id.radio);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup
                        .OnCheckedChangeListener() {
                    @Override

                    // The flow will come here when
                    // any of the radio buttons in the radioGroup
                    // has been clicked

                    // Check which radio button has been clicked
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId)
                    {

                        // Get the selected Radio Button
                        RadioButton
                                radioButton
                                = (RadioButton)group
                                .findViewById(checkedId);
                    }
                });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                // When submit button is clicked,
                // Ge the Radio Button which is set
                // If no Radio Button is set, -1 will be returned
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(First.this,
                            "No answer has been selected",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                else {

                    RadioButton radioButton
                            = (RadioButton)radioGroup
                            .findViewById(selectedId);

                    // Now display the value of selected item
                    // by the Toast message
                    if(radioButton.getText().equals("On")){
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                    else{
                    Toast.makeText(First.this,
                           " Please select 'On'",
                            Toast.LENGTH_SHORT)
                            .show();}
                }
            }
        });
    }
}
