package com.appdev.group3.ecollect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Example Button for Schedule Calendar
        Button scheduleCalendarButton = findViewById(R.id.schedule_calendar);
        scheduleCalendarButton.setOnClickListener(v -> {
            // Handle navigation or functionality here
        });

        // Example Button for Recyclable Materials Info
        Button recyclableMaterialButton = findViewById(R.id.recyclable_material_info);
        recyclableMaterialButton.setOnClickListener(v -> {
            // Handle navigation or functionality here
        });

        // Example Button for Waste Segregation Guide
        Button wasteSegregationButton = findViewById(R.id.waste_segregation);
        wasteSegregationButton.setOnClickListener(v -> {
            // Handle navigation or functionality here
        });
    }
}
