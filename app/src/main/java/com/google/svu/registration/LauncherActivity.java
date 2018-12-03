package com.google.svu.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
        int theme = preferences.getInt(StaticsVars.theme, R.style.AppTheme);
        setTheme(theme);
        setContentView(R.layout.activity_launcher);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
                boolean isLogin = preferences.getBoolean(StaticsVars.login, false);
                if (isLogin) {
                    startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 2000);

    }
}
