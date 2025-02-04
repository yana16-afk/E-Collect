package com.appdev.group3.ecollect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class ItemRecyclablePanel extends AppCompatActivity {
    public ArrayList<String> items;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_recyclable);

        // Initialize ArrayList
        items = new ArrayList<>();

        // Initialize TextView
        textView = findViewById(R.id.text_view_recyclable);

        displayData();

        // Back Button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> navigateToWasteSegregationSearch());
    }

    private void navigateToWasteSegregationSearch() {
        Intent intent = new Intent(ItemRecyclablePanel.this, Dashboard.class);
        startActivity(intent);
    }

    private void displayData() {
        //Temporary data
        items.clear();
        items.add("Tuyong Papel");
        items.add("Tuyong Karton");
        items.add("Aluminum Can");
        items.add("Plastic Containers");
        items.add("Balat ng kendi/sitsirya");
        items.add("Lahat ng klase ng bote");

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
