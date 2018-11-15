package com.google.svu.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    private RadioButton theme1, theme2, theme3, theme4, theme5;
    private Button applyBTN;
    private CheckBox soundCB;
    private RadioGroup themeRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pre = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
        setTheme(pre.getInt(StaticsVars.theme, R.style.AppTheme));
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        themeRG = findViewById(R.id.themeRG);
        theme1 = findViewById(R.id.theme1RB);
        theme2 = findViewById(R.id.theme2RB);
        theme3 = findViewById(R.id.theme3RB);
        theme4 = findViewById(R.id.theme4RB);
        theme5 = findViewById(R.id.theme5RB);
        applyBTN = findViewById(R.id.applyBTN);
        soundCB = findViewById(R.id.soundCB);

        SharedPreferences preferences = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
        int themeSelected = preferences.getInt(StaticsVars.theme, R.style.AppTheme);
        switch (themeSelected) {
            case R.style.AppTheme:
                theme1.setChecked(true);
                applyBTN.setBackgroundResource(R.drawable.btn_primary_background);
                break;
            case R.style.AppTheme2:
                theme2.setChecked(true);
                applyBTN.setBackgroundResource(R.drawable.btn_primary2_background);
                break;
            case R.style.AppTheme3:
                theme3.setChecked(true);
                applyBTN.setBackgroundResource(R.drawable.btn_primary3_background);
                break;
            case R.style.AppTheme4:
                theme4.setChecked(true);
                applyBTN.setBackgroundResource(R.drawable.btn_primary4_background);
                break;
            case R.style.AppTheme5:
                theme5.setChecked(true);
                applyBTN.setBackgroundResource(R.drawable.btn_primary5_background);
                break;
        }
        soundCB.setChecked(preferences.getBoolean(StaticsVars.sound, true));

        applyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticMethods.checkClickSound(SettingsActivity.this);
                int theme = R.style.AppTheme;
                int id = themeRG.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.theme2RB:
                        theme = R.style.AppTheme2;
                        break;

                    case R.id.theme3RB:
                        theme = R.style.AppTheme3;
                        break;

                    case R.id.theme4RB:
                        theme = R.style.AppTheme4;
                        break;

                    case R.id.theme5RB:
                        theme = R.style.AppTheme5;
                        break;
                }
                SharedPreferences.Editor editor = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE).edit();
                editor.putInt(StaticsVars.theme, theme);
                editor.putBoolean(StaticsVars.sound, soundCB.isChecked());
                editor.apply();
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        StaticMethods.checkClickSound(this);
        finish();
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
