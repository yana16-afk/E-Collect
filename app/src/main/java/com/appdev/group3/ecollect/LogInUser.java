package com.application.ecollectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInUser extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText loginEmail, loginPassword;
    private Button userLogIn;
    private SessionManager userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in_user);

        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.user_email);
        loginPassword = findViewById(R.id.user_password);
        userLogIn = findViewById(R.id.btn_login_user);

        userLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString().trim();
                String pass = loginPassword.getText().toString().trim();

                boolean valid = true;

                if (email.isEmpty()) {
                    loginEmail.setError("Email cannot be empty");
                    loginEmail.requestFocus();
                    valid = false;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    loginEmail.setError("Please enter a valid email address");
                    loginEmail.requestFocus();
                    valid = false;
                }

                if (pass.isEmpty()) {
                    loginPassword.setError("Password cannot be empty");
                    loginPassword.requestFocus();
                    valid = false;
                }

                if (valid) {
                    loginUser(email, pass);
                }
            }
        });

        findViewById(R.id.btn_register).setOnClickListener(v -> {
            Intent intent = new Intent(LogInUser.this, UserRegister.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(LogInUser.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                        // Navigate to next activity or update UI
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LogInUser.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
