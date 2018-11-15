package com.google.svu.registration.Dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.svu.registration.R;
import com.google.svu.registration.StaticMethods;
import com.google.svu.registration.StaticsVars;

public class EnterIDDialog extends Dialog {

    private LinearLayout enterIDLL, showInfoLL;
    private Button enterIDBTN, showInfoBTN;
    private EditText enterIDET;
    private TextView usernameTV, passwordTV;
    private Activity activity;

    public EnterIDDialog(Activity activity, @NonNull Context context) {
        super(context);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_enter_id);
        setCanceledOnTouchOutside(false);

        enterIDLL = findViewById(R.id.enterIDLL);
        showInfoLL = findViewById(R.id.showInfoLL);
        enterIDBTN = findViewById(R.id.enterIDBTN);
        showInfoBTN = findViewById(R.id.showInfoBTN);
        enterIDET = findViewById(R.id.enterIDET);
        usernameTV = findViewById(R.id.showInfoUsernameTV);
        passwordTV = findViewById(R.id.showInfoPasswordTV);


        enterIDBTN.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String id = enterIDET.getText().toString().trim();
                if (id.length() == 0) {
                    Toast.makeText(getContext(), "Enter your ID please", Toast.LENGTH_SHORT).show();
                    return;
                }
                String username = StaticMethods.getRandomString(10);
                String password = StaticMethods.getRandomString(6);

                SharedPreferences.Editor editor = activity.getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE).edit();
                editor.putString("id", id);
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();

                enterIDLL.setVisibility(View.GONE);
                showInfoLL.setVisibility(View.VISIBLE);

                usernameTV.setText("username: " + username);
                passwordTV.setText("password: " + password);

            }
        });

        showInfoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Enter username and password please", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

    }
}
