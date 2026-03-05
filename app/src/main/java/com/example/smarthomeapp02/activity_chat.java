package com.example.smarthomeapp02;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.chat);
        bottomNavigationView.setOnItemSelectedListener(item ->{
            int id = item.getItemId();
            if(id == R.id.chat) {
                return true;
            } else if (id == R.id.dashboard) {
                startActivity(new Intent(this, activity_alldevices.class));
                getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                return true;
            } else if (id == R.id.light) {
                startActivity(new Intent(this, activity_control_light.class));
                getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                return true;
            } else if (id == R.id.music) {
                startActivity(new Intent(this, activity_music.class));
                getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                return true;
            }
            return false;
            });
    }
}