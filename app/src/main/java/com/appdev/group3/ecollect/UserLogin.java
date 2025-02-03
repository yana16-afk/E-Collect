/* package com.appdev.group3.ecollect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class UserLogin extends AppCompatActivity {
    private EditText emailInput, passwordInput;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login); // Ensure this matches your XML filename

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Bind UI Elements
        emailInput = findViewById(R.id.loginuser_email);
        passwordInput = findViewById(R.id.password_input);
        Button loginButton = findViewById(R.id.button_login);
        Button registerButton = findViewById(R.id.button_sregister2);

        // Handle Login Button Click
        loginButton.setOnClickListener(v -> loginUser());

        // Redirect to Registration Page
        registerButton.setOnClickListener(v -> {
            startActivity(new Intent(UserLogin.this, RegistrationPage.class));
        });
    }

    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Validate input fields
        if (!validateInputs(email, password)) return;

        // Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(UserLogin.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UserLogin.this, Dashboard.class)); // Navigate to dashboard
                        finish(); // Close LoginActivity
                    } else {
                        Toast.makeText(UserLogin.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private boolean validateInputs(String email, String password) {
        if (email.isEmpty()) {
            emailInput.setError("Email is required");
            return false;
        }
        if (password.isEmpty()) {
            passwordInput.setError("Password is required");
            return false;
        }
        return true;
    }
} */

package com.appdev.group3.ecollect;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserLogin extends AppCompatActivity {

    private EditText emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login); // Ensure this matches your XML file name

        // Initialize UI components
        emailInput = findViewById(R.id.loginuser_email);
        passwordInput = findViewById(R.id.password_input);
        Button loginButton = findViewById(R.id.button_login);
        Button registerButton = findViewById(R.id.button_sregister2);


        // Login button logic
        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(UserLogin.this, "Please enter email and password!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Dummy check, replace with real authentication
            if (email.equals("test@example.com") && password.equals("password123")) {
                Toast.makeText(UserLogin.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserLogin.this, HomePage.class); // Ensure Homepage.java exists
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(UserLogin.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
            }
        });

        // Register button logic
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserLogin.this, RegistrationPage.class);
            startActivity(intent);
        });
    }
}

