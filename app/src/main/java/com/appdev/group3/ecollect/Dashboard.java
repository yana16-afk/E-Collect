package com.appdev.group3.ecollect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    private ImageButton schedButton, wasteButton, recyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize buttons
        schedButton = findViewById(R.id.sched_button);
        wasteButton = findViewById(R.id.waste_button);
        recyButton = findViewById(R.id.recy_button);

        // Set click listeners
        schedButton.setOnClickListener(v -> openSchedulePanel());

        wasteButton.setOnClickListener(v -> openWasteSegregationGuide());

        recyButton.setOnClickListener(v -> openRecyclableInformation());
    }

    private void openSchedulePanel() {
        Intent intent = new Intent(Dashboard.this, CalendarMonthly.class);
        startActivity(intent);
    }

    private void openWasteSegregationGuide() {
        Intent intent = new Intent(Dashboard.this, WasteSegregationSearch.class);
        startActivity(intent);
    }

    private void openRecyclableInformation() {
        Intent intent = new Intent(Dashboard.this, RecyclableCategories.class);
        startActivity(intent);
    }
}