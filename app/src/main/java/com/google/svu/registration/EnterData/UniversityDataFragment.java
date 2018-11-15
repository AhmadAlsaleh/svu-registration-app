package com.google.svu.registration.EnterData;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.svu.registration.EnterDataActivity;
import com.google.svu.registration.R;

@SuppressLint("ValidFragment")
public class UniversityDataFragment extends Fragment {

    private EditText certificateET, certificateDateET, certificateDegreeET, certificateEnglishET;
    private View view;
    private EnterDataActivity enterDataActivity;

    @SuppressLint("ValidFragment")
    public UniversityDataFragment(EnterDataActivity enterDataActivity) {
        this.enterDataActivity = enterDataActivity;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_university_data, container, false);

        certificateET = view.findViewById(R.id.certificateET);
        certificateDateET = view.findViewById(R.id.certificateDateET);
        certificateDegreeET = view.findViewById(R.id.certificateDegreeET);
        certificateEnglishET = view.findViewById(R.id.certificateEnglishET);

        certificateET.setText(enterDataActivity.getCertificateString());
        certificateEnglishET.setText(enterDataActivity.getCertificateStringEnglish());
        certificateDateET.setText(enterDataActivity.getCertificateStringDate());
        certificateDegreeET.setText(enterDataActivity.getCertificateStringDegree());

        return view;
    }

    public boolean checkInput() {
        if (certificateET.getText().toString().trim().length() == 0
                || certificateDateET.getText().toString().trim().length() == 0
                || certificateDegreeET.getText().toString().trim().length() == 0
                || certificateEnglishET.getText().toString().trim().length() == 0) {
            Toast.makeText(getContext(), "Check your inputs please", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String getCertificateET() {
        return certificateET.getText().toString().trim();
    }

    public void setCertificateET(String certificateET) {
        this.certificateET.setText(certificateET);
    }

    public String getCertificateDateET() {
        return certificateDateET.getText().toString().trim();
    }

    public void setCertificateDateET(String certificateDateET) {
        this.certificateDateET.setText(certificateDateET);
    }

    public String getCertificateDegreeET() {
        return certificateDegreeET.getText().toString().trim();
    }

    public void setCertificateDegreeET(String certificateDegreeET) {
        this.certificateDegreeET.setText(certificateDegreeET);
    }

    public String getCertificateEnglishET() {
        return certificateEnglishET.getText().toString().trim();
    }

    public void setCertificateEnglishET(String certificateEnglishET) {
        this.certificateEnglishET.setText(certificateEnglishET);
    }
}
