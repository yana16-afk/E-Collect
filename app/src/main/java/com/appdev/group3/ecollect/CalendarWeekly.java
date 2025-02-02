package com.appdev.group3.ecollect;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarWeekly extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_weekly);

        // Initialize spinner
        Spinner spinner = findViewById(R.id.xml_spinner);

        // Create ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.string_levels,  // The string array resource
                android.R.layout.simple_spinner_item    // Default layout for the Spinner levels
        );

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply adapter to spinner
        spinner.setAdapter(adapter);

        // Set the default selection to Weekly View (position 1)
        spinner.setSelection(1);

        // Set listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Call the existing onItemSelected method
                CalendarWeekly.this.onItemSelected(parent, view, position, id);

                // Navigate to the appropriate activity based on the selected item
                if (position == 0) { // Assuming position 0 corresponds to "Monthly View"
                    navigateToCalendarMonthly();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Call the existing onNothingSelected method
                CalendarWeekly.this.onNothingSelected(parent);
            }
        });

        // Back Button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> navigateToCalendarMonthly());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show();
    }

    private void navigateToCalendarMonthly() {
        Intent intent = new Intent(CalendarWeekly.this, CalendarMonthly.class);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
