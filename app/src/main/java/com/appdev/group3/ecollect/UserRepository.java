package com.appdev.group3.ecollect;

import com.appdev.group3.ecollect.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {
    private final FirebaseAuth mAuth;
    private final DatabaseReference databaseReference;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void registerUser(String name, String email, String mobile, String password, OnUserRegistrationListener listener) {
        // Register user with Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        User newUser = new User(userId, name, email, mobile);

                        // Save user data to Firebase Database
                        databaseReference.child(userId).setValue(newUser)
                                .addOnCompleteListener(dbTask -> {
                                    if (dbTask.isSuccessful()) {
                                        listener.onSuccess(); // Notify success
                                    } else {
                                        // Notify failure with database error message
                                        listener.onFailure("Database error: " + dbTask.getException().getMessage());
                                    }
                                });
                    } else {
                        // Notify failure with Firebase Authentication error message
                        listener.onFailure("Authentication error: " + task.getException().getMessage());
                    }
                })
                .addOnFailureListener(e -> {
                    // This will handle cases where the registration fails due to network issues or other reasons
                    listener.onFailure("Registration failed: " + e.getMessage());
                });
    }

    // Interface for listener callbacks
    public interface OnUserRegistrationListener {
        void onSuccess(); // Success callback
        void onFailure(String errorMessage); // Failure callback with error message
    }
}
