package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtWeight, edtHeightFt, edtHeightIn;
    Button btnCalculate;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWeight = findViewById(R.id.edtWeight);
        edtHeightFt = findViewById(R.id.edtHeightFt);
        edtHeightIn = findViewById(R.id.edtHeightIn);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weightStr = edtWeight.getText().toString();
                String ftStr = edtHeightFt.getText().toString();
                String inStr = edtHeightIn.getText().toString();

                if (weightStr.isEmpty() || ftStr.isEmpty() || inStr.isEmpty()) {
                    txtResult.setText("Please fill all fields");
                    return;
                }

                int wt = Integer.parseInt(weightStr);
                int ft = Integer.parseInt(ftStr);
                int in = Integer.parseInt(inStr);

                int totalIn = ft * 12 + in;
                double cm = 2.54 * totalIn;
                double meters = cm / 100;

                double bmi = wt / (meters * meters);

                if (bmi > 25) {
                    txtResult.setText("You're Overweight\nBMI: " + bmi);
                } else if (bmi < 18) {
                    txtResult.setText("You're Underweight\nBMI: " + bmi);
                } else {
                    txtResult.setText("You're Healthy\nBMI: " + bmi);
                }
            }
        });
    }
}