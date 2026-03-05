package com.example.smarthomeapp02;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicServices extends Service {

    private static MediaPlayer mediaPlayer;
    public static boolean isPlaying = false;
    public static String currentSong = "";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null && intent.hasExtra("music")) {

            int musicRes = intent.getIntExtra("music", 0);
            String songName = intent.getStringExtra("name");

            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            mediaPlayer = MediaPlayer.create(this, musicRes);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

            isPlaying = true;
            currentSong = songName;
        }

        if (intent != null && intent.getBooleanExtra("pause", false)) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                isPlaying = false;
            }
        }

        if (intent != null && intent.getBooleanExtra("resume", false)) {
            if (mediaPlayer != null) {
                mediaPlayer.start();
                isPlaying = true;
            }
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}