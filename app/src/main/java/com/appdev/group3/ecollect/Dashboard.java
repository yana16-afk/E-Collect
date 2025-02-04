package com.appdev.group3.ecollect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    private ImageButton schedButton, wasteButton, recyButton, homeButton, newsButton, searchButton, profButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize buttons
        schedButton = findViewById(R.id.sched_button);
        wasteButton = findViewById(R.id.waste_button);
        recyButton = findViewById(R.id.recy_button);
        homeButton = findViewById(R.id.homeButton);
        newsButton = findViewById(R.id.newsButton);
        searchButton = findViewById(R.id.searchButton);
        profButton = findViewById(R.id.profButton);

        // Set click listeners for buttons
        schedButton.setOnClickListener(v -> openSchedulePanel());
        wasteButton.setOnClickListener(v -> openWasteSegregationGuide());
        recyButton.setOnClickListener(v -> openRecyclableInformation());
        homeButton.setOnClickListener(v -> openHome());
        newsButton.setOnClickListener(v -> openNews());
        // searchButton.setOnClickListener(v -> openSearch());
        profButton.setOnClickListener(v -> openProfile());
    }

    // Open the Schedule Panel
    private void openSchedulePanel() {
        Intent intent = new Intent(Dashboard.this, CalendarMonthly.class);
        startActivity(intent);
    }

    // Open the Waste Segregation Guide
    private void openWasteSegregationGuide() {
        Intent intent = new Intent(Dashboard.this, WasteSegregationSearch.class);
        startActivity(intent);
    }

    // Open Recyclable Information
    private void openRecyclableInformation() {
        Intent intent = new Intent(Dashboard.this, RecyclableCategories.class);
        startActivity(intent);
    }

    // Navigate to the Home page (you can define the Home activity accordingly)
    private void openHome() {
        Intent intent = new Intent(Dashboard.this, HomePage.class);
        startActivity(intent);
    }

    // Navigate to the News page (you can define the News activity accordingly)
    private void openNews() {
        Intent intent = new Intent(Dashboard.this, Bulletin.class);
        startActivity(intent);
    }

    // Navigate to the Profile page (you can define the Profile activity accordingly)
    private void openProfile() {
        Intent intent = new Intent(Dashboard.this, UserProfilePanel.class);
        startActivity(intent);
    }
}