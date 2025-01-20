package com.appdev.group3.ecollect;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, homepage.class);
            startActivity(intent);
            finish();
        }, 3000); // 3000 ms delay
    }
}
