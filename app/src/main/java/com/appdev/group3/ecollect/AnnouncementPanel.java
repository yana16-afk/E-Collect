package com.appdev.group3.ecollect;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.FrameLayout;

import java.util.ArrayList;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

public class AnnouncementPanel extends AppCompatActivity {

    ArrayList<String> announcementTitles;

    ImageButton announceAddButton;
    ImageButton announceDeleteButton;
    ImageButton announceBackButton;
    LinearLayout announcementLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_announcement);

        // Initialize the Buttons
        announceBackButton = findViewById(R.id.announceBackBtn);
        announceAddButton = findViewById(R.id.announceAddBtn);
        announceDeleteButton = findViewById(R.id.announceDeleteBtn);

        // Assign Click Listener
        announceBackButton.setOnClickListener(v -> closeAnnouncementPanel());
        announceAddButton.setOnClickListener(v -> navigateToAddAnnouncement());
        announceDeleteButton.setOnClickListener(v -> navigateToDeleteAnnouncement());

        // Reference to the LinearLayout inside ScrollView
        announcementLayout = findViewById(R.id.announceLayoutList);

        createAnouncementTitleBtn();
    }

    private void createAnnouncementButton(String title) {
        // Create a FrameLayout to hold the ImageButton and TextView
        FrameLayout frameLayout = new FrameLayout(this);

        // Create the ImageButton
        ImageButton imageButton = new ImageButton(this);
        imageButton.setImageResource(R.drawable.announce_title_button);  // Set the image for the button
        imageButton.setBackground(null);

        // Set layout parameters for the ImageButton
        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,   // Set width to 40dp in pixels
                (int) (40 * getResources().getDisplayMetrics().density)    // Set height to 40dp in pixels
        );

        imageParams.gravity = Gravity.CENTER;  // Center the button in the FrameLayout
        imageButton.setLayoutParams(imageParams);

        // Create the TextView to display the title
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextColor(Color.BLACK);  // Set text color if needed
        textView.setGravity(Gravity.CENTER);  // Center the text
        textView.setPadding(10, 0, 10, 0);  // Optional padding for spacing

        Typeface typeface = ResourcesCompat.getFont(this, R.font.poppins_medium);  // R.font.poppins_medium is the reference to the font in res/font
        textView.setTypeface(typeface);

        // Set layout parameters for the TextView
        FrameLayout.LayoutParams textParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,  // Set width to match parent
                (int) (40 * getResources().getDisplayMetrics().density)   // Set height to match parent
        );
        textParams.gravity = Gravity.CENTER;  // Center the text in the FrameLayout
        textView.setLayoutParams(textParams);

        // Apply margin top starting from the second ImageButton onwards
        if (announcementLayout.getChildCount() > 0) {
            imageParams.topMargin = (int) (10 * getResources().getDisplayMetrics().density);
            textParams.topMargin = (int) (10 * getResources().getDisplayMetrics().density);
        }

        // Add the ImageButton and TextView to the FrameLayout
        frameLayout.addView(imageButton);
        frameLayout.addView(textView);

        // Optional: Set an onClickListener for each layout
        frameLayout.setOnClickListener(v -> {
            Intent intent = new Intent(AnnouncementPanel.this, Dashboard.class);
            startActivity(intent);
        });

        // Add the FrameLayout to the parent layout
        announcementLayout.addView(frameLayout);
    }

    private void createAnouncementTitleBtn() {
        // ArrayList of announcement titles
        announcementTitles = new ArrayList<>();

        announcementTitles.add("Title: New Elected President");
        announcementTitles.add("Title: Senator Elections");
        announcementTitles.add("Title: Garbage Collector");
        announcementTitles.add("Title: Crypto Scam");
        announcementTitles.add("Title: Global Warming");
        announcementTitles.add("Title 6");
        announcementTitles.add("Title 7");
        announcementTitles.add("Title 8");
        announcementTitles.add("Title 9");
        announcementTitles.add("Title 10");

        // Dynamically create a button for each announcement title
        for (int i = 0; i < announcementTitles.size(); i++) {
            String title = announcementTitles.get(i);
            createAnnouncementButton(title);
        }
    }

//    private void createAnouncementTitleBtn() {
//        // Instantiate database connection
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference quotesRef = database.getReference("quotes");
//
//        // Initialize the ArrayList
//        announcementTitles = new ArrayList<>();
//
//        // Fetch data from Firebase
//        quotesRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                announcementTitles.clear(); // Clear existing data before adding new ones
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String title = snapshot.getValue(String.class); // Assuming each quote is a string
//                    if (title != null) {
//                        announcementTitles.add(title);
//                    }
//                }
//
//                // Update UI after fetching data
//                announcementLayout.removeAllViews(); // Clear existing views
//                for (String title : announcementTitles) {
//                    createAnnouncementButton(title);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e("FirebaseError", "Failed to read data", databaseError.toException());
//            }
//        });
//    }

    private void closeAnnouncementPanel() {
        Intent intent = new Intent(AnnouncementPanel.this, Dashboard.class);
        startActivity(intent);
    }

    private void navigateToAddAnnouncement() {
        Intent intent = new Intent(AnnouncementPanel.this, Dashboard.class);
        startActivity(intent);
    }

    private void navigateToDeleteAnnouncement() {
        Intent intent = new Intent(AnnouncementPanel.this, Dashboard.class);
        startActivity(intent);
    }
}
