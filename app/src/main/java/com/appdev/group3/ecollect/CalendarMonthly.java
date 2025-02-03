package com.appdev.group3.ecollect;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarMonthly extends AppCompatActivity implements OnItemSelectedListener, CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_monthly);

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

        // Set the default selection to Monthly View (position 0)
        spinner.setSelection(0);

        // Set listener
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Call the existing onItemSelected method
                CalendarMonthly.this.onItemSelected(parent, view, position, id);

                // Navigate to the appropriate activity based on the selected item
                if (position == 1) { // Assuming position 1 corresponds to "Weekly View"
                    navigateToItemSearch();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Call the existing onNothingSelected method
                CalendarMonthly.this.onNothingSelected(parent);
            }
        });

        // Back Button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> navigateToDashboard());

        // Initialize calendar
        initCalendarWidgets();
        selectedDate = Calendar.getInstance();
        setMonthView();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show();
    }

    private void navigateToItemSearch() {
        Intent intent = new Intent(CalendarMonthly.this, CalendarWeekly.class);
        startActivity(intent);
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(CalendarMonthly.this, Dashboard.class);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private void initCalendarWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private ArrayList<String> daysInMonthArray(Calendar date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        Calendar calendar = (Calendar) date.clone(); // Clone to avoid modifying the original date

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Replaced YearMonth.lengthOfMonth()

        calendar.set(Calendar.DAY_OF_MONTH, 1); // Replaced LocalDate.withDayOfMonth(1)
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Replaced LocalDate.getDayOfWeek().getValue()

        // Adjust dayOfWeek to match your logic (Monday = 1, Sunday = 7)
        if (dayOfWeek == Calendar.SUNDAY) {
            dayOfWeek = 7;
        } else {
            dayOfWeek -= 1;
        }

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(Calendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.getDefault()); // Replaced DateTimeFormatter
        return formatter.format(date.getTime());
    }

    public void previousMonthAction(View view) {
        selectedDate.add(Calendar.MONTH, -1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate.add(Calendar.MONTH, 1);
        setMonthView();
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this, selectedDate);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.isEmpty()) {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}