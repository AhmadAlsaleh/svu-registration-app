package com.google.svu.registration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.svu.registration.EnterData.EnterViewPagerAdapter;
import com.google.svu.registration.EnterData.PersonalDataFragment;
import com.google.svu.registration.EnterData.SubmitFragment;
import com.google.svu.registration.EnterData.UniversityDataFragment;

public class EnterDataActivity extends AppCompatActivity {

    private NonViewPage viewPage;
    private FloatingActionButton nextFAB, previousFAB;

    String id = "", fName = "", lName = "", phone = "", birthDate = "";
    String certificateString = "", certificateStringDegree = "", certificateStringDate = "", certificateStringEnglish = "";

    // region


    public String getCertificateString() {
        return certificateString;
    }

    public void setCertificateString(String certificateString) {
        this.certificateString = certificateString;
    }

    public String getCertificateStringDegree() {
        return certificateStringDegree;
    }

    public void setCertificateStringDegree(String certificateStringDegree) {
        this.certificateStringDegree = certificateStringDegree;
    }

    public String getCertificateStringDate() {
        return certificateStringDate;
    }

    public void setCertificateStringDate(String certificateStringDate) {
        this.certificateStringDate = certificateStringDate;
    }

    public String getCertificateStringEnglish() {
        return certificateStringEnglish;
    }

    public void setCertificateStringEnglish(String certificateStringEnglish) {
        this.certificateStringEnglish = certificateStringEnglish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    // endregion

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
        int theme = preferences.getInt(StaticsVars.theme, R.style.AppTheme);
        setTheme(theme);
        setContentView(R.layout.activity_enter_data);

        getSupportActionBar().setTitle("Enter Date");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPage = findViewById(R.id.enterVP);
        nextFAB = findViewById(R.id.nextFAB);
        previousFAB = findViewById(R.id.previousFAB);
        previousFAB.setVisibility(View.GONE);

        final PersonalDataFragment personalDataFragment = new PersonalDataFragment(this);
        final UniversityDataFragment universityDataFragment = new UniversityDataFragment(this);
        final SubmitFragment submitFragment = new SubmitFragment(this);

        EnterViewPagerAdapter adapter = new EnterViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(personalDataFragment);
        adapter.addFragment(universityDataFragment);
        adapter.addFragment(submitFragment);
        viewPage.setAdapter(adapter);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        nextFAB.setVisibility(View.VISIBLE);
                        previousFAB.setVisibility(View.GONE);
                        break;

                    case 1:
                        nextFAB.setVisibility(View.VISIBLE);
                        previousFAB.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        nextFAB.setVisibility(View.GONE);
                        previousFAB.setVisibility(View.VISIBLE);
                        break;
                }
            }

        });

        SharedPreferences sharedPreferencesIn = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE);
        id = sharedPreferencesIn.getString("_id", "");
        fName = sharedPreferencesIn.getString("_fName", "");
        lName = sharedPreferencesIn.getString("_lName", "");
        phone = sharedPreferencesIn.getString("_phone", "");
        birthDate = sharedPreferencesIn.getString("_birth", "");

        certificateString = sharedPreferencesIn.getString("_cs", "");
        certificateStringDate = sharedPreferencesIn.getString("_cd", "");
        certificateStringDegree = sharedPreferencesIn.getString("_cde", "");
        certificateStringEnglish = sharedPreferencesIn.getString("_ce", "");

        nextFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticMethods.checkClickSound(EnterDataActivity.this);

                if (viewPage.getCurrentItem() == 0) {
                    if (personalDataFragment.checkInput()) {
                        id = personalDataFragment.getIdString();
                        fName = personalDataFragment.getfName();
                        lName = personalDataFragment.getlName();
                        phone = personalDataFragment.getPhone();
                        birthDate = personalDataFragment.getBirthDate();

                        SharedPreferences.Editor sharedPreferences = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE).edit();
                        sharedPreferences.putString("_id", id);
                        sharedPreferences.putString("_fName", fName);
                        sharedPreferences.putString("_lName", lName);
                        sharedPreferences.putString("_phone", phone);
                        sharedPreferences.putString("_birth", birthDate);
                        sharedPreferences.apply();

                        viewPage.setCurrentItem(viewPage.getCurrentItem() + 1);

                    }
                } else {
                    if (universityDataFragment.checkInput()) {
                        certificateString = universityDataFragment.getCertificateET();
                        certificateStringDate = universityDataFragment.getCertificateDateET();
                        certificateStringDegree = universityDataFragment.getCertificateDegreeET();
                        certificateStringEnglish = universityDataFragment.getCertificateEnglishET();

                        SharedPreferences.Editor sharedPreferences = getSharedPreferences(StaticsVars.name, Context.MODE_PRIVATE).edit();
                        sharedPreferences.putString("_cs", certificateString);
                        sharedPreferences.putString("_cd", certificateStringDate);
                        sharedPreferences.putString("_cde", certificateStringDegree);
                        sharedPreferences.putString("_ce", certificateStringEnglish);
                        sharedPreferences.apply();

                        viewPage.setCurrentItem(viewPage.getCurrentItem() + 1);

                        submitFragment.setPersonal("ID: " + id + "\n"
                                + "First Name: " + fName + "\n"
                                + "Last Name: " + lName + "\n"
                                + "Birth Date: " + birthDate + "\n"
                                + "Phone: " + phone);

                        submitFragment.setPersonal(certificateString + "\n"
                                + "Date: " + certificateStringDate + "\n"
                                + "Degree: " + certificateStringDegree + "\n"
                                + "English: " + certificateStringEnglish);

                    }
                }
            }
        });

        previousFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticMethods.checkClickSound(EnterDataActivity.this);
                viewPage.setCurrentItem(viewPage.getCurrentItem() - 1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        StaticMethods.checkClickSound(EnterDataActivity.this);
        finish();
        startActivity(new Intent(EnterDataActivity.this, MainActivity.class));
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
