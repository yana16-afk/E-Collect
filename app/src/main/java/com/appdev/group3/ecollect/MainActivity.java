package com.appdev.group3.ecollect;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        Button getStartedButton = findViewById(R.id.button_getstarted);
        Button loginButton = findViewById(R.id.button_loginsmall);

        // Set up click listeners
        getStartedButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrationPage.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserLogin.class);
            startActivity(intent);
        });
    }
}