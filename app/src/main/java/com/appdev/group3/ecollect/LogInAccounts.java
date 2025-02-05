package com.application.ecollectapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogInAccounts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in_accounts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_user).setOnClickListener(v -> {
            Intent intent = new Intent(LogInAccounts.this, LogInUser.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_admin).setOnClickListener(v -> {
            Intent intent = new Intent(LogInAccounts.this, LogInAdmin.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_register).setOnClickListener(v -> {
            Intent intent = new Intent(LogInAccounts.this, UserRegister.class);
            startActivity(intent);
        });
    }
}