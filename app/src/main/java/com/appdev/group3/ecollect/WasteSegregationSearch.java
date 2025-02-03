package com.appdev.group3.ecollect;/* package com.appdev.group3.ecollect;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WasteSegregationSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_waste_segregation_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
} */



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.appdev.group3.ecollect.data.WasteData;

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
        findViewById(R.id.nabubulokButton).setOnClickListener(view -> showCategoryInfo("Biodegradable"));
        findViewById(R.id.nareresikloButton).setOnClickListener(view -> showCategoryInfo("Recyclable"));
        findViewById(R.id.hazardousButton).setOnClickListener(view -> showCategoryInfo("Hazardous"));
        findViewById(R.id.bulkyButton).setOnClickListener(view -> showCategoryInfo("Bulky Waste"));
        findViewById(R.id.residualButton).setOnClickListener(view -> showCategoryInfo("Residual Waste"));
    }


    @SuppressLint("SetTextI18n")
    private void searchWaste(String item) {
        String category = WasteData.getCategory(item);
        categoryResult.setText("Category: " + category);
        Toast.makeText(this, "Category: " + category, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void showCategoryInfo(String category) {
        categoryResult.setText("Waste Type: " + category);
        Toast.makeText(this, "Waste Type: " + category, Toast.LENGTH_SHORT).show();
    }
}

