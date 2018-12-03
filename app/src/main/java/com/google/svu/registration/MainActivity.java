package com.google.svu.registration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.svu.registration.Dialogs.EnterQualificationsDialog;

public class MainActivity extends AppCompatActivity {

    private CardView university, master, diploma, computer;
    private String _return;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.universityCV:
                    if (!check_return("more70")) {
                        return;
                    }
                    break;

                case R.id.computerCV:
                    if (!check_return("less70")) {
                        return;
                    }
                    break;

                case R.id.masterCV:
                    if (!check_return("diploma")) {
                        return;
                    }
                    break;

                case R.id.diplomaCV:
                    if (!check_return("collage")) {
                        return;
                    }
                    break;
            }

            StaticMethods.checkClickSound(MainActivity.this);
            finish();
            startActivity(new Intent(MainActivity.this, EnterDataActivity.class));
        }
    };

    private boolean check_return(String s) {
        if (s.equals(_return)) {
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(this, "You Can\'t Register in this project", Toast.LENGTH_SHORT).show();
        return false;
    }

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

        SharedPreferences pref = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
        if (pref.getString("type", "").length() == 0) {
            final EnterQualificationsDialog enterQualificationsDialog = new EnterQualificationsDialog(this);
            enterQualificationsDialog.show();

            enterQualificationsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    _return = enterQualificationsDialog._return;
                }
            });
        } else {
//            finish();
//            startActivity(new Intent(MainActivity.this, EnterDataActivity.class));
        }

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

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor preferences = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE).edit();
        preferences.putBoolean(StaticsVars.login, false);
        preferences.apply();
        super.onBackPressed();
    }
}
