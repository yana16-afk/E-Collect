package com.appdev.group3.ecollect;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Example Button for Schedule Calendar
        ImageButton scheduleCalendarButton = findViewById(R.id.sched_button);
        scheduleCalendarButton.setOnClickListener(v -> {
            // Handle navigation or functionality here
        });

        // Example Button for Recyclable Materials Info
        ImageButton recyclableMaterialButton = findViewById(R.id.recy_button);
        recyclableMaterialButton.setOnClickListener(v -> {
            // Handle navigation or functionality here
        });

        // Example Button for Waste Segregation Guide
        ImageButton wasteSegregationButton = findViewById(R.id.waste_button);
        wasteSegregationButton.setOnClickListener(v -> {
            // Handle navigation or functionality here
        });
    }
}