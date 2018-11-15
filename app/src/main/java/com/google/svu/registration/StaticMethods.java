package com.google.svu.registration;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.util.Random;

public class StaticMethods {

    public static String getRandomString(int i) {

        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        while (stringBuilder.length() < i) {
            int index = (int) (random.nextFloat() * alpha.length());
            stringBuilder.append(alpha.charAt(index));
        }
        return stringBuilder.toString();
    }

    public static void checkClickSound(Activity activity) {
        AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        SharedPreferences preferences = activity.getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
        if (preferences.getBoolean(StaticsVars.sound, true)) {
            final MediaPlayer mp = MediaPlayer.create(activity, R.raw.click_sound);
            mp.start();
        }
    }

}
