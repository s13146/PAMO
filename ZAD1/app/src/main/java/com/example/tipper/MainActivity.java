package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    private EditText weightEdit;
    private EditText growthEdit;
    private TextView bmiTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        // get references to programmatically manipulated TextViews
        weightEdit = (EditText) findViewById(R.id.weightEditText);
        growthEdit = (EditText) findViewById(R.id.growthEditText);
        bmiTextView = (TextView) findViewById(R.id.bmiTextView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }

        });
    }
    private void calculate() {

        float weight =  Float.parseFloat(weightEdit.getText().toString());
        float growth = Float.parseFloat(growthEdit.getText().toString());
        float bmi = (100*100*weight)/(growth*growth);
        String calculatedBmi = String.format("%.2f", bmi);
        bmiTextView.setText("Twoje BMI: " + calculatedBmi);
    }

}

