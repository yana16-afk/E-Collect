package com.appdev.group3.ecollect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class ItemBulkyPanel extends AppCompatActivity {

    public ArrayList<String> items;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bulky);

        // Initialize ArrayList
        items = new ArrayList<>();

        // Initialize TextView
        textView = findViewById(R.id.text_view_bulky);

        displayData();

        // Back Button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> navigateToItemSearch());
    }

    private void navigateToItemSearch() {
        Intent intent = new Intent(ItemBulkyPanel.this, Dashboard.class);
        startActivity(intent);
    }

    private void displayData() {
        //Temporary data
        items.clear();
        items.add("Mga sirang appliances");
        items.add("Mga sirang furniture");
        items.add("Lumang kutson o mattresses");
        items.add("Malalaking gamit");

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
