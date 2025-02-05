package com.application.ecollectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class UserRegister extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText userEmail, userPassword, userFName, userLName, userPhone;
    private Button userRegister;

    private static final String TAG = "UserRegister";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_register);

        auth = FirebaseAuth.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        userFName = findViewById(R.id.user_fname);
        userLName = findViewById(R.id.user_lname);
        userPhone = findViewById(R.id.user_phone);
        userRegister = findViewById(R.id.btn_register_user);

        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString().trim();
                String pass = userPassword.getText().toString().trim();
                String firstname = userFName.getText().toString().trim();
                String lastname = userLName.getText().toString().trim();
                String phone = userPhone.getText().toString().trim();

                boolean valid = true;

                if (email.isEmpty()) {
                    userEmail.setError("Email cannot be empty");
                    userEmail.requestFocus();
                    valid = false;
                }

                if (pass.isEmpty()) {
                    userPassword.setError("Password cannot be empty");
                    userPassword.requestFocus();
                    valid = false;
                }

                if (firstname.isEmpty()) {
                    userFName.setError("First name cannot be empty");
                    userFName.requestFocus();
                    valid = false;
                }

                if (lastname.isEmpty()) {
                    userLName.setError("Last name cannot be empty");
                    userLName.requestFocus();
                    valid = false;
                }

                if (phone.isEmpty()) {
                    userPhone.setError("Phone number cannot be empty");
                    userPhone.requestFocus();
                    valid = false;
                } else if (!phone.matches("\\d{11}")) {
                    userPhone.setError("Phone number must only contain digits (11)");
                    userPhone.requestFocus();
                    valid = false;
                }

                if (valid) {
                    auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            if (task.isSuccessful()) {
                                List<String> signInMethods = task.getResult().getSignInMethods();
                                if (signInMethods != null && !signInMethods.isEmpty()) {
                                    // Email already exists
                                    userEmail.setError("Email is already in use");
                                    Toast.makeText(UserRegister.this, "Email is already in use", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Email does not exist, proceed with signup
                                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                                if (firebaseUser != null) {
                                                    String UID = firebaseUser.getUid();
                                                    // Add to DB
                                                    AddUser_DB(email, firstname, lastname, phone, UID);

                                                    Toast.makeText(UserRegister.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(UserRegister.this, LogInUser.class));
                                                } else {
                                                    Toast.makeText(UserRegister.this, "Failed to get user UID", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Log.e(TAG, "SignUp Failed: " + task.getException().getMessage());
                                                Toast.makeText(UserRegister.this, "SignUp Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            } else {
                                Log.e(TAG, "Failed to check email: " + task.getException().getMessage());
                                Toast.makeText(UserRegister.this, "Failed to check email: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(UserRegister.this, "Please check all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AddUser_DB(String UserEmail, String FirstN, String LastN, String PhoneNum, String UID) {
        // Hashing
        HashMap<String, Object> UserData_Hash = new HashMap<>();

        UserData_Hash.put("User ID", UID);
        UserData_Hash.put("User Email", UserEmail);
        UserData_Hash.put("First Name", FirstN);
        UserData_Hash.put("Last Name", LastN);
        UserData_Hash.put("Phone Number", PhoneNum);

        // Instantiate
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference UserDataRef = database.getReference("UserData");

        // Add the user data to the database using UID
        UserDataRef.child(UID).setValue(UserData_Hash).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(UserRegister.this, "Added", Toast.LENGTH_SHORT).show();
                    userEmail.getText().clear();
                    userPassword.getText().clear();
                    userFName.getText().clear();
                    userLName.getText().clear();
                    userPhone.getText().clear();
                } else {
                    Log.e(TAG, "Failed to add user data: " + task.getException().getMessage());
                    Toast.makeText(UserRegister.this, "Failed to add user data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
