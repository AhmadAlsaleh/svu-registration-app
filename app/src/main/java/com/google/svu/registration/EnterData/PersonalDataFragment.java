package com.google.svu.registration.EnterData;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.svu.registration.EnterDataActivity;
import com.google.svu.registration.R;
import com.google.svu.registration.StaticMethods;
import com.google.svu.registration.StaticsVars;

@SuppressLint("ValidFragment")
public class PersonalDataFragment extends Fragment implements TextWatcher {

    EditText id, fName, lName, birthDate, phone;
    private View view;
    private EnterDataActivity enterDataActivity;

    @SuppressLint("ValidFragment")
    public PersonalDataFragment(EnterDataActivity enterDataActivity) {
        this.enterDataActivity = enterDataActivity;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_personal_data, container, false);

        id = view.findViewById(R.id.personalIDET);
        fName = view.findViewById(R.id.personalFNET);
        lName = view.findViewById(R.id.personalLNET);
        birthDate = view.findViewById(R.id.personalBDET);
        phone = view.findViewById(R.id.personalPhoneET);

        SharedPreferences preferences = enterDataActivity.getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
        id.setText(preferences.getString("id", ""));
        fName.setText(enterDataActivity.getfName());
        lName.setText(enterDataActivity.getlName());
        birthDate.setText(enterDataActivity.getBirthDate());
        phone.setText(enterDataActivity.getPhone());

        id.addTextChangedListener(this);
        fName.addTextChangedListener(this);
        lName.addTextChangedListener(this);
        birthDate.addTextChangedListener(this);
        phone.addTextChangedListener(this);

        return view;
    }

    public boolean checkInput() {
        if (id.getText().toString().trim().length() == 0) {
            Toast.makeText(getContext(), "Check your ID", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (fName.getText().toString().trim().length() == 0) {
            Toast.makeText(getContext(), "Check your First Name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (lName.getText().toString().trim().length() == 0) {
            Toast.makeText(getContext(), "Check your Last Name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (birthDate.getText().toString().trim().length() == 0) {
            Toast.makeText(getContext(), "Check your Birth Date", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (phone.getText().toString().trim().length() == 0) {
            Toast.makeText(getContext(), "Check your Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String getIdString() {
        return id.getText().toString().trim();
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public String getfName() {
        return fName.getText().toString().trim();
    }

    public void setfName(String fName) {
        this.fName.setText(fName);
    }

    public String getlName() {
        return lName.getText().toString().trim();
    }

    public void setlName(String lName) {
        this.lName.setText(lName);
    }

    public String getBirthDate() {
        return birthDate.getText().toString().trim();
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.setText(birthDate);
    }

    public String getPhone() {
        return phone.getText().toString().trim();
    }

    public void setPhone(String phone) {
        this.phone.setText(phone);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        StaticMethods.checkClickSound(getActivity());
    }
}
