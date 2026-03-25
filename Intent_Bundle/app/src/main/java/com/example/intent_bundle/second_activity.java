package com.example.intent_bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class second_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        Intent fromAct = getIntent();
        String studName = fromAct.getStringExtra("Name");
        int rollNo = fromAct.getIntExtra("RollNo", 0);

        TextView studNameTxt = findViewById(R.id.studName);
        TextView rollNoTxt = findViewById(R.id.rollNo);

        studNameTxt.setText("Student Name: "+studName);
        rollNoTxt.setText("RollNo.: "+rollNo);

    }
}