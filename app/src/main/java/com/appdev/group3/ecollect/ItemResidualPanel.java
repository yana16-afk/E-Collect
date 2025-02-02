package com.appdev.group3.ecollect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class ItemResidualPanel extends AppCompatActivity {

    public ArrayList<String> items;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_residual);

        // Initialize ArrayList
        items = new ArrayList<>();

        // Initialize TextView
        textView = findViewById(R.id.text_view_residual);

        displayData();

        // Back Button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> navigateToItemSearch());
    }

    private void navigateToItemSearch() {
        Intent intent = new Intent(ItemResidualPanel.this, Dashboard.class);
        startActivity(intent);
    }

    private void displayData() {
        //Temporary data
        items.clear();
        items.add("Diapers");
        items.add("Seramiks");
        items.add("Styropor");
        items.add("Sanitary Napkins");
        items.add("Basang Papel");
        items.add("Box ng pagkain");
        items.add("Sanitary Products");
        items.add("CD, credit cards, films");

        textView.setText("");

        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append(getString(R.string.residual_items_header)).append("\n\n");
        for (String item : items) {
            textBuilder.append("•   ").append(item).append("\n");
        }

        // Set the text in the TextView
        textView.setText(textBuilder.toString().trim());
    }
}
