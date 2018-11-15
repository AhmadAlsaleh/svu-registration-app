package com.google.svu.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CardView university, master, diploma, computer;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StaticMethods.checkClickSound(MainActivity.this);
            finish();
            startActivity(new Intent(MainActivity.this, EnterDataActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
        int theme = preferences.getInt(StaticsVars.theme, R.style.AppTheme);
        setTheme(theme);
        setContentView(R.layout.activity_main);

        university = findViewById(R.id.universityCV);
        master = findViewById(R.id.masterCV);
        diploma = findViewById(R.id.diplomaCV);
        computer = findViewById(R.id.computerCV);

        university.setOnClickListener(onClickListener);
        master.setOnClickListener(onClickListener);
        diploma.setOnClickListener(onClickListener);
        computer.setOnClickListener(onClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSettings:
                StaticMethods.checkClickSound(MainActivity.this);
                finish();
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
