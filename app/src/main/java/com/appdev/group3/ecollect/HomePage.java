/* package com.appdev.group3.ecollect;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    private ImageButton district2Button;
    private TextView kindView, pickView, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize views
        ImageButton district1Button = findViewById(R.id.district1Button);
        district2Button = findViewById(R.id.district2Button);
        kindView = findViewById(R.id.kindView);
        pickView = findViewById(R.id.pickView);
        textView = findViewById(R.id.textView);

        // Set button listeners
        district1Button.setOnClickListener(new MyOnClickListener());

        district2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDistrict2Selected();
            }
        });
    }

    private void onDistrict1Selected() {
        // Update text or perform actions when District 1 is selected
        textView.setText(R.string.you_selected_district_1);
        kindView.setText(R.string.please_choose_a_schedule_for_district_1);
        pickView.setText(R.string.district_1_waste_schedule);
    }

    private void onDistrict2Selected() {
        // Update text or perform actions when District 2 is selected
        textView.setText(R.string.you_selected_district_2);
        kindView.setText(R.string.please_choose_a_schedule_for_district_2);
        pickView.setText(R.string.district_2_waste_schedule);
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            onDistrict1Selected();
        }
    }
} */

package com.appdev.group3.ecollect;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    private ImageButton district2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ImageButton district1Button = findViewById(R.id.district1Button);
        district2Button = findViewById(R.id.district2Button);

        district1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Dashboard.class);
                startActivity(intent);
            }
        });

        district2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}
