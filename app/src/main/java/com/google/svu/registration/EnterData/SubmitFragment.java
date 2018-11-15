package com.google.svu.registration.EnterData;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.svu.registration.EnterDataActivity;
import com.google.svu.registration.R;

@SuppressLint("ValidFragment")
public class SubmitFragment extends Fragment {

    private EnterDataActivity enterDataActivity;
    private View view;
    private TextView personal, university;
    private Button submit;

    @SuppressLint("ValidFragment")
    public SubmitFragment(EnterDataActivity enterDataActivity) {
        this.enterDataActivity = enterDataActivity;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_submit, container, false);

        personal = view.findViewById(R.id.submitPersonalTV);
        university = view.findViewById(R.id.submitUniversityTV);
        submit = view.findViewById(R.id.submitBTN);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(enterDataActivity, "Done", Toast.LENGTH_SHORT).show();
                enterDataActivity.onBackPressed();
            }
        });

        return view;
    }

    public void setPersonal(String personal) {
        this.personal.setText(personal);
    }

    public void setUniversity(String university) {
        this.university.setText(university);
    }
}
