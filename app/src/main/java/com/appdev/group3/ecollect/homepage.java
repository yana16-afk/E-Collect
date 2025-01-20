package com.appdev.group3.ecollect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Button for District 1
        ImageButton buttonDistrict1 = findViewById(R.id.imageButtonDistrict1);
        buttonDistrict1.setOnClickListener(v -> navigateToDashboard());

        // Button for District 2
        ImageButton buttonDistrict2 = findViewById(R.id.imageButtonDistrict2);
        buttonDistrict2.setOnClickListener(v -> navigateToDashboard());
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(homepage.this, Dashboard.class);
        startActivity(intent);
    }
}
