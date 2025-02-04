package com.appdev.group3.ecollect;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.view.inputmethod.EditorInfo;
import android.text.InputType;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UserProfilePanel extends AppCompatActivity {

    TextView nameTextView;
    TextView addressTextView;
    TextView contactTextView;
    TextView emailTextView;

    EditText contactInput;
    EditText emailInput;

    ImageButton nameEditButton;
    ImageButton addressEditButton;
    ImageButton contactEditButton;
    ImageButton emailEditButton;
    CardView nameFormCardView;
    CardView addressFormCardView;

    ArrayList<String> district1;
    ArrayList<String> district2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize the TextView
        nameTextView = findViewById(R.id.nameTextView);
        addressTextView = findViewById(R.id.addressTextView);
        contactTextView = findViewById(R.id.contactTextView);
        emailTextView = findViewById(R.id.emailTextView);


        // Initialize the CardView
        nameFormCardView = findViewById(R.id.nameFormCardView);
        addressFormCardView = findViewById(R.id.addressFormCardView);

        // Initialize the Buttons
        nameEditButton = findViewById(R.id.nameEditButton);
        addressEditButton = findViewById(R.id.addressEditButton);
        contactEditButton = findViewById(R.id.contactEditButton);
        emailEditButton = findViewById(R.id.emailEditButton);
        ImageButton directoryButton = findViewById(R.id.directoryButton);
        ImageButton faqButton = findViewById(R.id.faqButton);

        // Initialize the Edit Text
        contactInput = findViewById(R.id.contactInput);
        emailInput = findViewById(R.id.emailInput);

        // Assign Click Listener
        nameEditButton.setOnClickListener(v -> nameEditDialog());
        addressEditButton.setOnClickListener(v -> addressDialog());
        contactEditButton.setOnClickListener(v -> contactDialog());
        emailEditButton.setOnClickListener(v -> emailDialog());
        directoryButton.setOnClickListener(v -> Directory());
        faqButton.setOnClickListener(v -> Faqs());
//         Directory Button


//
//        // FAQ Button
//        ImageButton faqButton = findViewById(R.id.faqButton);
//        faqButton.setOnClickListener(v -> navigateToFAQ());
//
//        // Log Out Button
//        ImageButton logOutButton = findViewById(R.id.logOutButton);
//        logOutButton.setOnClickListener(v -> logOutUserProfile());
    }

//    private void navigateToDirectory() {
//        Intent intent = new Intent(UserProfilePanel.this, Directory.class);
//        startActivity(intent);
//    }
//
//    private void navigateToFAQ() {
//        Intent intent = new Intent(UserProfilePanel.this, Faqs.class);
//        startActivity(intent);
//    }
//
//    private void logOutUserProfile() {
//        Intent intent = new Intent(UserProfilePanel.this, UserLogin.class);
//        startActivity(intent);
//    }

    private void nameEditDialog() {
        // Display nameFormCardView
        nameFormCardView.setVisibility(View.VISIBLE);

        // Get the views inside the CardView
        EditText firstNameInput = nameFormCardView.findViewById(R.id.firstNameInput);
        EditText lastNameInput = nameFormCardView.findViewById(R.id.lastNameInput);

        Button saveNameButton = nameFormCardView.findViewById(R.id.saveNameButton);
        Button cancelNameButton = nameFormCardView.findViewById(R.id.cancelNameButton);

        // Handle Save button click
        saveNameButton.setOnClickListener(v -> {
            String firstName = firstNameInput.getText().toString().trim();
            String lastName = lastNameInput.getText().toString().trim();

            // Check if both fields are non-empty
            if (firstName.isEmpty() || lastName.isEmpty()) {
                // Show error message if either field is empty
                if (firstName.isEmpty()) {
                    firstNameInput.setError("First Name is required");
                }
                if (lastName.isEmpty()) {
                    lastNameInput.setError("Last Name is required");
                }
            } else {
                // If both fields have values, set the full name
                String fullName = firstName + " " + lastName;
                nameTextView.setText(fullName); // Update the TextView with the new name

                // Hide the CardView after saving
                nameFormCardView.setVisibility(View.GONE);
            }
        });

        // Handle Cancel button click
        cancelNameButton.setOnClickListener(v -> {
            // Hide the CardView without saving
            nameFormCardView.setVisibility(View.GONE);
        });
    }

    private void Directory() {
        Intent intent = new Intent(UserProfilePanel.this, Directory.class);
        startActivity(intent);
    }
    private void Faqs() {
        Intent intent = new Intent(UserProfilePanel.this, Faqs.class);
        startActivity(intent);
    }

    private void addressDialog() {
        // Display nameFormCardView
        addressFormCardView.setVisibility(View.VISIBLE);

        // Initialize the ArrayLists
        district1 = new ArrayList<>();
        district2 = new ArrayList<>();

        // Barangays in District 1
        district1.add("Balong-Bato");
        district1.add("Batis");
        district1.add("Corazon de Jesus");
        district1.add("Ermitaño");
        district1.add("Pasadena");
        district1.add("Pedro Cruz");
        district1.add("Progreso");
        district1.add("Rivera");
        district1.add("Salapan");
        district1.add("San Perfecto");

        // Barangays in District 2
        district2.add("Addition Hills");
        district2.add("Greenhills");
        district2.add("Isabelita");
        district2.add("Kabayan");
        district2.add("Little Baguio");
        district2.add("Maytunas");
        district2.add("Onse");
        district2.add("St. Joseph (Halo-Halo)");
        district2.add("Sta. Lucia");
        district2.add("Tibagan");
        district2.add("West Crame");

        // Get the views inside the CardView
        EditText streetNameInput = addressFormCardView.findViewById(R.id.streetNameInput);
        EditText barangayNameInput = addressFormCardView.findViewById(R.id.baranggayNameInput);
        EditText cityNameInput = addressFormCardView.findViewById(R.id.cityNameInput);

        Button saveNameButton = addressFormCardView.findViewById(R.id.saveAddressButton);
        Button cancelNameButton = addressFormCardView.findViewById(R.id.cancelAddressButton);

        // Handle Save button click
        saveNameButton.setOnClickListener(v -> {
            String streetName = streetNameInput.getText().toString().trim();
            String barangayName = barangayNameInput.getText().toString().trim();
            String cityName = cityNameInput.getText().toString().trim();

            // Check if all fields are non-empty
            if (streetName.isEmpty() || barangayName.isEmpty() || cityName.isEmpty()) {
                // Show error message if either field is empty
                if (streetName.isEmpty()) {
                    streetNameInput.setError("Street name is required");
                }
                if (barangayName.isEmpty()) {
                    barangayNameInput.setError("Barangay name is required");
                }
            } else {
                // Validate if the barangay exists in district1 or district2
                String district = "";

                if (district1.contains(barangayName)) {
                    district = "District 1";
                } else if (district2.contains(barangayName)) {
                    district = "District 2";
                }

                // If the district was found, append it to the address
                if (!district.isEmpty()) {
                    String address = streetName + ", " + barangayName + ", " + district + ", " + cityName;
                    addressTextView.setText(address); // Update the TextView with the new name
                } else {
                    // If the barangay is not found in either district
                    Toast.makeText(this, "Invalid Barangay Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Hide the CardView after saving
                addressFormCardView.setVisibility(View.GONE);
            }
        });

        // Handle Cancel button click
        cancelNameButton.setOnClickListener(v -> {
            // Hide the CardView without saving
            addressFormCardView.setVisibility(View.GONE);
        });
    }

    private void contactDialog() {
        // Disable other edit buttons
        nameEditButton.setEnabled(false);
        addressEditButton.setEnabled(false);
        emailEditButton.setEnabled(false);

        contactTextView.setVisibility(View.GONE);
        contactInput.setVisibility(View.VISIBLE);

        // Ensure "Enter" key works as a submission action
        contactInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
        contactInput.setInputType(InputType.TYPE_CLASS_PHONE);

        // Handle "Enter" key press
        contactInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validatePhoneNumber();
                return true;
            }
            return false;
        });

        // Validate when the user clicks away
        contactInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validatePhoneNumber();
            }
        });
    }

    private void emailDialog() {
        // Disable other edit buttons
        nameEditButton.setEnabled(false);
        addressEditButton.setEnabled(false);
        contactEditButton.setEnabled(false);

        emailInput.setSingleLine(true);

        emailTextView.setVisibility(View.GONE);
        emailInput.setVisibility(View.VISIBLE);

        // Ensure "Enter" key works as a submission action
        emailInput.setImeOptions(EditorInfo.IME_ACTION_DONE);

        // Handle "Enter" key press
        emailInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateEmail();
                return true;
            }
            return false;
        });

        // Validate when the user clicks away
        emailInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validateEmail();
            }
        });
    }

    private void validatePhoneNumber() {
        String phone = contactInput.getText().toString().trim();

        if (!phone.startsWith("09")) {
            Toast.makeText(this, "Invalid number format. Must start with '09'", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone.length() != 11) {
            Toast.makeText(this, "Phone number must be 11 digits", Toast.LENGTH_SHORT).show();
            return;
        }

        // If both conditions pass, update UI
        contactTextView.setText(phone);
        contactInput.setVisibility(View.GONE);
        contactTextView.setVisibility(View.VISIBLE);

        // Re-enable other edit buttons after editing is done
        nameEditButton.setEnabled(true);
        addressEditButton.setEnabled(true);
        emailEditButton.setEnabled(true);
    }

    private void validateEmail() {
        String email = emailInput.getText().toString().trim();

        if (!email.contains("@")) {
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            return;
        }

        // List of valid email extensions
        String[] validExtensions = {".ph", ".com", ".org", ".edu", ".net", ".gov",
                ".com.ph", ".org.ph", ".edu.ph", ".net.ph", ".gov.ph"};

        // Check if the email ends with any of the valid extensions
        boolean isValidExtension = false;
        for (String extension : validExtensions) {
            if (email.endsWith(extension)) {
                isValidExtension = true;
                break;
            }
        }

        if (!isValidExtension) {
            Toast.makeText(this, "Invalid Email Extension", Toast.LENGTH_SHORT).show();
            return;
        }

        // If both conditions pass, update UI
        emailTextView.setText(email);
        emailInput.setVisibility(View.GONE);
        emailTextView.setVisibility(View.VISIBLE);

        // Re-enable other edit buttons after editing is done
        nameEditButton.setEnabled(true);
        addressEditButton.setEnabled(true);
        contactEditButton.setEnabled(true);
    }
}