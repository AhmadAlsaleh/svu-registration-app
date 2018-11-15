package com.google.svu.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.svu.registration.Dialogs.EnterIDDialog;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;
    private Button loginBTN;
    private TextView newUserTV;
    private ImageView loginEyeIV;
    private boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");

        usernameET = findViewById(R.id.loginUsernameET);
        passwordET = findViewById(R.id.loginPasswordET);
        loginBTN = findViewById(R.id.loginBTN);
        newUserTV = findViewById(R.id.newUserTV);
        loginEyeIV = findViewById(R.id.loginEyeIV);

        loginEyeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    loginEyeIV.setImageResource(R.drawable.ic_eye_black);
                    passwordET.setTransformationMethod(new PasswordTransformationMethod());
                    isShow = false;
                } else {
                    loginEyeIV.setImageResource(R.drawable.ic_eye);
                    passwordET.setTransformationMethod(null);
                    isShow = true;
                }
            }
        });

        newUserTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EnterIDDialog(LoginActivity.this, LoginActivity.this).show();
            }
        });

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();

                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Enter your Information please", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences preferences = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
                if (username.equals(preferences.getString("username", ""))
                        && password.equals(preferences.getString("password", ""))) {
                    Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(StaticsVars.login, true);
                    editor.apply();

                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Check your inputs please", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
