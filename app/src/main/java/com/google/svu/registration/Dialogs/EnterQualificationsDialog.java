package com.google.svu.registration.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.svu.registration.MainActivity;
import com.google.svu.registration.R;
import com.google.svu.registration.StaticsVars;

public class EnterQualificationsDialog extends Dialog {

    public String _return;
    private RadioGroup checkRG;
    private MainActivity activity;

    public EnterQualificationsDialog(@NonNull Context context) {
        super(context);
        this.activity = (MainActivity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_enter_qualifications);
        setCanceledOnTouchOutside(false);

        Button button = findViewById(R.id.startBTN);
        checkRG = findViewById(R.id.checkRG);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = checkRG.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.secondaryL70:
                        _return = "less70";
                        break;

                    case R.id.secondaryM70:
                        _return = "more70";
                        break;

                    case R.id.diplomaD:
                        _return = "diploma";
                        break;

                    case R.id.collageD:
                        _return = "collage";
                        break;
                }

                SharedPreferences.Editor editor = activity.getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE).edit();
                editor.putString("type", _return);
                editor.apply();

                dismiss();
            }
        });
    }
}
