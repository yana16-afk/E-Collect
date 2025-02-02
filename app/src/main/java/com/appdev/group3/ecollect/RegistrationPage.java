package com.appdev.group3.ecollect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegistrationPage extends AppCompatActivity {
    private EditText nameInput, emailInput, mobileInput, passwordInput;
    private Button registerButton, loginButton;
    private FirebaseAuth mAuth;
    private UserRepository userRepository; // Separate user operations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        // Bind UI components
        nameInput = findViewById(R.id.name_input);
        emailInput = findViewById(R.id.emailAdd_input);
        mobileInput = findViewById(R.id.mobilenumber_input);
        passwordInput = findViewById(R.id.password_input);
        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.loginButton);

        // Initialize Firebase & Repository
        mAuth = FirebaseAuth.getInstance();
        userRepository = new UserRepository();

        // Handle Register Button Click
        registerButton.setOnClickListener(v -> registerUser());

        // Redirect to Login Page
        loginButton.setOnClickListener(v -> {
            startActivity(new Intent(RegistrationPage.this, UserLogin.class));
        });
    }

    private void registerUser() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String mobile = mobileInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Validate inputs
        if (!validateInputs(name, email, mobile, password)) return;

        // Call UserRepository to register user
        userRepository.registerUser(name, email, mobile, password, new UserRepository.OnUserRegistrationListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(RegistrationPage.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationPage.this, UserLogin.class)); // Fixed reference
                finish(); // Prevent user from going back to the registration page
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(RegistrationPage.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateInputs(String name, String email, String mobile, String password) {
        if (name.isEmpty()) {
            nameInput.setError("Name is required");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Invalid Email");
            return false;
        }
        if (mobile.length() != 10) {
            mobileInput.setError("Enter a valid 10-digit mobile number");
            return false;
        }
        if (password.length() < 6) {
            passwordInput.setError("Password must be at least 6 characters");
            return false;
        }
        return true;
    }
}