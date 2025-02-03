/* package com.appdev.group3.ecollect;

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
} */

package com.appdev.group3.ecollect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationPage extends AppCompatActivity {

    private EditText emailInput;
    private EditText usernameInput;
    private EditText mobileNumberInput;
    private final EditText passwordInput = findViewById(R.id.password_input);
    private EditText confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page); // Ensure this matches your XML filename

        // Initialize UI elements
        emailInput = findViewById(R.id.emailAdd_input);
        usernameInput = findViewById(R.id.name_input);
        mobileNumberInput = findViewById(R.id.mobilenumber_input);
        confirmPasswordInput = findViewById(R.id.password_input); // Change to confirmPassword_input if exists
        Button registerButton = findViewById(R.id.register_button);
        Button loginButton = findViewById(R.id.loginButton);

        // Register button click event
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Login button click event
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationPage.this, UserLogin.class);
                startActivity(intent);
                finish(); // Closes RegistrationPage after switching to LoginActivity
            }
        });
    }

    // Registration logic
    private void registerUser() {
        String email = emailInput.getText().toString().trim();
        String username = usernameInput.getText().toString().trim();
        String mobileNumber = mobileNumberInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        // Validation checks
        if (email.isEmpty() || username.isEmpty() || mobileNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email format!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Here, you would normally store user data (e.g., in Firebase or SQLite)
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();

        // Redirect to login
        Intent intent = new Intent(RegistrationPage.this, UserLogin.class);
        startActivity(intent);
        finish();
    }
}