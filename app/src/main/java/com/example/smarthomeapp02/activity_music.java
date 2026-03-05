package com.example.smarthomeapp02;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_music extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView txtNowPlaying = findViewById(R.id.txtNowPlaying);
        Button btnPlayPause = findViewById(R.id.btnPlayPause);
        ListView listMusic = findViewById(R.id.listMusic);

        String[] musicNames = {
                "Shape of you",
                "Welcome Home Japanese",
        };

        int[] musicFiles = {
                R.raw.music1,
                R.raw.music2,
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        musicNames);

        listMusic.setAdapter(adapter);

        listMusic.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(this, MusicServices.class);
            intent.putExtra("music", musicFiles[position]);
            intent.putExtra("name", musicNames[position]);
            startService(intent);

            txtNowPlaying.setText(
                    getString(R.string.now_playing, musicNames[position])
            );
            btnPlayPause.setText(R.string.pause);
        });
        btnPlayPause.setOnClickListener(v -> {

            Intent intent = new Intent(this, MusicServices.class);

            if (MusicServices.isPlaying) {
                intent.putExtra("pause", true);
                btnPlayPause.setText(R.string.play);
            } else {
                intent.putExtra("resume", true);
                btnPlayPause.setText(R.string.pause);
            }

            startService(intent);
        });
        //---------------------Navigation menu-----------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnItemSelectedListener(item ->{
            int id = item.getItemId();
            if(id == R.id.music) {
                return true;
            }
            else if(id == R.id.chat) {
                Intent intent = new Intent(this, activity_chat.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
                return true;
            } else if (id == R.id.light) {
                Intent intent = new Intent(this, activity_control_light.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
                return true;
            } else if (id == R.id.dashboard) {
                Intent intent = (new Intent(this, activity_alldevices.class));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });

    }
}