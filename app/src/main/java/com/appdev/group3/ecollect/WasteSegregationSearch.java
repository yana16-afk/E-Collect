package com.appdev.group3.ecollect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WasteSegregationSearch extends AppCompatActivity {

    private TextView categoryResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste_segregation_search);

        SearchView searchView = findViewById(R.id.searchfunction);
        categoryResult = findViewById(R.id.category_result);

        // Handle search input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchWaste(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Handle category button clicks
        findViewById(R.id.nabubulokButton).setOnClickListener(view -> navigateToCategory("Biodegradable"));
        findViewById(R.id.nareresikloButton).setOnClickListener(view -> navigateToCategory("Recyclable"));
        findViewById(R.id.hazardousButton).setOnClickListener(view -> navigateToCategory("Hazardous"));
        findViewById(R.id.bulkyButton).setOnClickListener(view -> navigateToCategory("Bulky Waste"));
        findViewById(R.id.residualButton).setOnClickListener(view -> navigateToCategory("Residual Waste"));
    }

    @SuppressLint("SetTextI18n")
    private void searchWaste(String item) {
        String category = WasteData.getCategory(item);
        categoryResult.setText("Category: " + category);
        Toast.makeText(this, "Category: " + category, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void navigateToCategory(String category) {
        Intent intent;
        switch (category) {
            case "Biodegradable":
                intent = new Intent(this, ItemBiodegradablePanel.class);
                break;
            case "Recyclable":
                intent = new Intent(this, ItemRecyclablePanel.class);
                break;
            case "Hazardous":
                intent = new Intent(this, ItemHazardousPanel.class);
                break;
            case "Bulky Waste":
                intent = new Intent(this, ItemBulkyPanel.class);
                break;
            case "Residual Waste":
                intent = new Intent(this, ItemResidualPanel.class);
                break;
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
        startActivity(intent);
        Toast.makeText(this, "Navigating to: " + category, Toast.LENGTH_SHORT).show();
    }
}