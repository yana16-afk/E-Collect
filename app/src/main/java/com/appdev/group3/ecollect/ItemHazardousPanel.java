package com.appdev.group3.ecollect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class ItemHazardousPanel extends AppCompatActivity {

    public ArrayList<String> items;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_hazardous);

        // Initialize ArrayList
        items = new ArrayList<>();

        // Initialize TextView
        textView = findViewById(R.id.text_view_hazardous);

        displayData();

        // Back Button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> navigateToItemSearch());
    }

    private void navigateToItemSearch() {
        Intent intent = new Intent(ItemHazardousPanel.this, Dashboard.class);
        startActivity(intent);
    }

    private void displayData() {
        //Temporary data
        items.clear();
        items.add("Pintura");
        items.add("Baterya");
        items.add("Thinner");
        items.add("Spray Canister");
        items.add("Nail Polish");
        items.add("Mga metal");
        items.add("Mga produktong kemikal");

        textView.setText("");

        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append(getString(R.string.biodegradable_items_header)).append("\n\n");
        for (String item : items) {
            textBuilder.append("•   ").append(item).append("\n");
        }

        // Set the text in the TextView
        textView.setText(textBuilder.toString().trim());
    }
}
