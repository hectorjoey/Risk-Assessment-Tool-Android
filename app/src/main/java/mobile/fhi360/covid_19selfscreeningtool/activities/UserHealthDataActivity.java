package mobile.fhi360.covid_19selfscreeningtool.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ikhiloyaimokhai.nigeriastatesandlgas.Nigeria;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import mobile.fhi360.covid_19selfscreeningtool.Api.RetrofitClient;
import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.model.UserHealthData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHealthDataActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Toolbar mToolbar;
    final Calendar myCalendar = Calendar.getInstance();
    EditText mFullname, mDate, mPhone;
    Spinner mAgeSpinner, mStateSpinner, mLgaSpinner, mGenderSpinner;
    private ProgressDialog loadingBar;
    private String mState, mLga, mAge;
    private List<String> states;
    private Spinner mLanguageSpinner;
    private static final int SPINNER_HEIGHT = 500;
//    private String userId;

    private String risk;

    //    Users users;
    //TextView mUserId;
    //declaring the radio group;
    RadioGroup mCough, mFever, mDifficultyInBreathing, mSneezing, mChestPain, mDiarrhoea, mFlu, mSoreThroatSymptoms,
            mContactWithFever, mContactWithCough, mContactWithDifficultBreathing, mContactWithSneeze, mContactWithChestpain,
            mContactWithDiarrhoea, mContactWithOtherFlu, mContactWithSoreThroat, mUnderlyingConditions, mSpecifyKidney, mSpecifyPregnancy,
            mSpecifyTB, mSpecifyDiabetes, mSpecifyLiver, mSpecifyChronicLungDisease, mSpecifyCancer, mSpecifyHeartDisease,
            mContactWithSomeoneWithSymptoms,
            mSpecifyHIV, mExposedToFacilityWithConfirmedCase, mTreatment, mEnoughDrugsForThreeMonths,
            mCovid19CareFromSomeoneInHousehold, mSomeoneHelpingYouManageHIV, mLossOfSmellSymptoms, mLossOfTasteSymptoms, mContactWithLossOfSmell, mContactWithLossOfTaste;
    //declaring radio buttons
    RadioButton rbFever, rbCough, rbDifficultyInBreathing, rbSneezing, rbChestPain, rbDiarrhoea, rbFlu, rbSoreThroatSymptoms, rbSpecifyCancer,
            rbContactWithFever, rbContactWithCough, rbContactWithDifficultBreathing, rbContactWithSneeze, rbContactWithChestpain, rbSpecifyKidney, rbSpecifyTB,
            rbContactWithDiarrhoea, rbContactWithOtherFlu, rbContactWithSoreThroat, rbUnderlyingConditions, rbSpecifyPregnancy, rbSpecifyDiabetes, rbSpecifyLiver,
            rbSpecifyHeartDisease, rbSpecifyChronicLungDisease, rbContactWithSomeoneWithSymptoms, rbSpecifyHIV, rbExposedToFacilityWithConfirmedCase,
            rbTreatment, rbEnoughDrugsForThreeMonths, rbCovid19CareFromSomeoneInHousehold, rbSomeoneHelpingYouManageHIV, rbLossOfSmellSymptoms, rbLossOfTasteSymptoms, rbContactWithLossOfSmell, rbContactWithLossOfTaste;
    Button mBtn_submit;
    Locale myLocale;
    String currentLanguage = "en", currentLang;
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_health_data);
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        setTitle("User Dashboard");
        loadingBar = new ProgressDialog(this);
        currentLanguage = getIntent().getStringExtra(currentLang);
        mLanguageSpinner = findViewById(R.id.language_spinner);
        List<String> list = new ArrayList<>();
        list.add("Select language");
        list.add("English");
        list.add("Hausa");
        list.add("Igbo");
        HashMap<String, String> status = status();
        String statuss = status.get("status");
        if (statuss != null) {

        } else {
            showCustomDialog();
        }
//        Intent intent = getIntent();
//
//        HashMap<String, String> id = getUserId();
//        userId = id.get("userId");
        mAgeSpinner = findViewById(R.id.age_spinner);
//
        mDate = findViewById(R.id.edit_date);
        mFullname = findViewById(R.id.edit_text_fullname);
        mFever = findViewById(R.id.radio_fever);
        mPhone = findViewById(R.id.phone);
        mCough = findViewById(R.id.radio_cough);
        mDifficultyInBreathing = findViewById(R.id.radio_difficultBreathing);
        mSneezing = findViewById(R.id.radio_sneezingSymptoms);
        mChestPain = findViewById(R.id.radio_chestpainSymptoms);
        mDiarrhoea = findViewById(R.id.radio_diarrhoeaSymptoms);
        mFlu = findViewById(R.id.radio_fluSymptoms);
        mSoreThroatSymptoms = findViewById(R.id.radio_soreSymptoms);
        mContactWithFever = findViewById(R.id.radio_contactWithFever);
        mContactWithCough = findViewById(R.id.radio_contactWithCough);
        mContactWithDifficultBreathing = findViewById(R.id.radio_contactWithDifficultBreathing);
        mContactWithSneeze = findViewById(R.id.radio_contactWithSneeze);
        mContactWithChestpain = findViewById(R.id.radio_contactWithChestpain);
        mContactWithDiarrhoea = findViewById(R.id.radio_contactWithDiarrhoea);
        mContactWithOtherFlu = findViewById(R.id.radio_contactWithOtherFLu);
        mContactWithSoreThroat = findViewById(R.id.radio_contactWithSoreThroat);
        mUnderlyingConditions = findViewById(R.id.radio_condition);
        mSpecifyTB = findViewById(R.id.specifyTB);
        mSpecifyDiabetes = findViewById(R.id.radio_specifyDiabetes);
        mSpecifyLiver = findViewById(R.id.radio_specifyLiverDisease);
        mSpecifyChronicLungDisease = findViewById(R.id.radio_chronicLungDisease);
        mSpecifyCancer = findViewById(R.id.radio_specifyCancer);
        mSpecifyHeartDisease = findViewById(R.id.radio_heartDisease);
        mContactWithSomeoneWithSymptoms = findViewById(R.id.radio_contactWithSomeoneWithSymptoms);
        mSpecifyKidney = findViewById(R.id.radio_specifyKidney);
        mSpecifyPregnancy = findViewById(R.id.radio_specifyPregnancy);
        mSpecifyHIV = findViewById(R.id.specifyHIV);
        mTreatment = findViewById(R.id.treatment);
        mEnoughDrugsForThreeMonths = findViewById(R.id.enoughDrugsForThreeMonths);
        mExposedToFacilityWithConfirmedCase = findViewById(R.id.exposedToFacilityWithConfirmedCase);
        mCovid19CareFromSomeoneInHousehold = findViewById(R.id.covid19CareFromSomeoneInHousehold);
        mSomeoneHelpingYouManageHIV = findViewById(R.id.someoneHelpingYouManageHIV);
        mLossOfSmellSymptoms = findViewById(R.id.lossOfSmellSymptoms);
        mLossOfTasteSymptoms = findViewById(R.id.lossTasteSymptoms);
        mContactWithLossOfSmell = findViewById(R.id.radio_contactWithLossOfSmell);
        mContactWithLossOfTaste = findViewById(R.id.radio_contactWithTaste);
        mAgeSpinner = findViewById(R.id.age_spinner);
        mGenderSpinner = findViewById(R.id.genderSpinner);


        mStateSpinner = findViewById(R.id.stateSpinner);
        mLgaSpinner = findViewById(R.id.lgaSpinner);
        resizeSpinner(mStateSpinner, SPINNER_HEIGHT);
        resizeSpinner(mLgaSpinner, SPINNER_HEIGHT);

        states = Nigeria.getStates();

        //call to method that'll set up state and lga spinner
        setupSpinners();

        mBtn_submit = findViewById(R.id.btn_submit);
//        mUserId = findViewById(R.id.userId);


        mBtn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = mFullname.getText().toString().trim();
                String date = mDate.getText().toString().trim();
                String age = String.valueOf(mAgeSpinner.getSelectedItem());
                String state = String.valueOf(mStateSpinner.getSelectedItem());
                String lga = String.valueOf(mLgaSpinner.getSelectedItem());
                String gender = String.valueOf(mGenderSpinner.getSelectedItem());
                String phone = mPhone.getText().toString().trim();
                String feverSymptom = ((RadioButton) findViewById(mFever.getCheckedRadioButtonId())).getText().toString();
                String coughSymptom = ((RadioButton) findViewById(mCough.getCheckedRadioButtonId())).getText().toString();
                String difficultyInBreathingSymptom = ((RadioButton) findViewById(mDifficultyInBreathing.getCheckedRadioButtonId())).getText().toString();
                String sneezingSymptoms = ((RadioButton) findViewById(mSneezing.getCheckedRadioButtonId())).getText().toString();
                String chestPainSymptoms = ((RadioButton) findViewById(mChestPain.getCheckedRadioButtonId())).getText().toString();
                String diarrhoeaSymptoms = ((RadioButton) findViewById(mDiarrhoea.getCheckedRadioButtonId())).getText().toString();
                String fluSymptoms = ((RadioButton) findViewById(mFlu.getCheckedRadioButtonId())).getText().toString();
                String soreThroatSymptoms = ((RadioButton) findViewById(mSoreThroatSymptoms.getCheckedRadioButtonId())).getText().toString();
                String contactWithFever = ((RadioButton) findViewById(mContactWithFever.getCheckedRadioButtonId())).getText().toString();
                String contactWithCough = ((RadioButton) findViewById(mContactWithCough.getCheckedRadioButtonId())).getText().toString();
                String contactWithDifficultBreathing = ((RadioButton) findViewById(mContactWithDifficultBreathing.getCheckedRadioButtonId())).getText().toString();
                String contactWithSneeze = ((RadioButton) findViewById(mContactWithSneeze.getCheckedRadioButtonId())).getText().toString();
                String contactWithChestpain = ((RadioButton) findViewById(mContactWithChestpain.getCheckedRadioButtonId())).getText().toString();
                String contactWithDiarrhoea = ((RadioButton) findViewById(mContactWithDiarrhoea.getCheckedRadioButtonId())).getText().toString();
                String contactWithOtherFLu = ((RadioButton) findViewById(mContactWithOtherFlu.getCheckedRadioButtonId())).getText().toString();
                String contactWithSoreThroat = ((RadioButton) findViewById(mContactWithSoreThroat.getCheckedRadioButtonId())).getText().toString();
                String underlyingConditions = ((RadioButton) findViewById(mUnderlyingConditions.getCheckedRadioButtonId())).getText().toString();
                String specifyKidney = ((RadioButton) findViewById(mSpecifyKidney.getCheckedRadioButtonId())).getText().toString();
                String specifyPregnancy = ((RadioButton) findViewById(mSpecifyPregnancy.getCheckedRadioButtonId())).getText().toString();
                String specifyTB = ((RadioButton) findViewById(mSpecifyTB.getCheckedRadioButtonId())).getText().toString();
                String specifyLiver = ((RadioButton) findViewById(mSpecifyLiver.getCheckedRadioButtonId())).getText().toString();
                String specifyChronicLungDisease = ((RadioButton) findViewById(mSpecifyChronicLungDisease.getCheckedRadioButtonId())).getText().toString();
                String specifyCancer = ((RadioButton) findViewById(mSpecifyCancer.getCheckedRadioButtonId())).getText().toString();
                String specifyHeartDisease = ((RadioButton) findViewById(mSpecifyHeartDisease.getCheckedRadioButtonId())).getText().toString();
                String contactWithSomeoneWithSymptoms = ((RadioButton) findViewById(mContactWithSomeoneWithSymptoms.getCheckedRadioButtonId())).getText().toString();
                String specifyHIV = ((RadioButton) findViewById(mSpecifyHIV.getCheckedRadioButtonId())).getText().toString();
                String treatment = ((RadioButton) findViewById(mTreatment.getCheckedRadioButtonId())).getText().toString();
                String specifyDiabetes = ((RadioButton) findViewById(mSpecifyDiabetes.getCheckedRadioButtonId())).getText().toString();
                String exposedToFacilityWithConfirmedCase = ((RadioButton) findViewById(mExposedToFacilityWithConfirmedCase.getCheckedRadioButtonId())).getText().toString();
                String covid19CareFromSomeoneInHousehold = ((RadioButton) findViewById(mCovid19CareFromSomeoneInHousehold.getCheckedRadioButtonId())).getText().toString();
                String someoneHelpingYouManageHIV = ((RadioButton) findViewById(mSomeoneHelpingYouManageHIV.getCheckedRadioButtonId())).getText().toString();
                String lossOfSmellSymptoms = ((RadioButton) findViewById(mLossOfSmellSymptoms.getCheckedRadioButtonId())).getText().toString();
                String lossOfTasteSymptoms = ((RadioButton) findViewById(mLossOfTasteSymptoms.getCheckedRadioButtonId())).getText().toString();
                String contactWithLossOfSmell = ((RadioButton) findViewById(mContactWithLossOfSmell.getCheckedRadioButtonId())).getText().toString();
                String contactWithLossOfTaste = ((RadioButton) findViewById(mContactWithLossOfTaste.getCheckedRadioButtonId())).getText().toString();
                String enoughDrugsForThreeMonths = ((RadioButton) findViewById(mEnoughDrugsForThreeMonths.getCheckedRadioButtonId())).getText().toString();

                //validations of fields
                if (TextUtils.isEmpty(fullname)) {
                    mFullname.setError("Please enter initials!");
                    mFullname.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    mPhone.setError("Phone number is required!");
                    mPhone.requestFocus();
                    return;
                }
                if (fullname.contains(".") || fullname.contains(",") || fullname.contains("&")
                        || fullname.contains("*") || fullname.contains("-")) {
                    mFullname.setError("special characters not allowed here!");
                    mFullname.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(date)) {
                    mDate.setError("Enter date!");
                    mDate.requestFocus();

                } else if ((exposedToFacilityWithConfirmedCase.equals("Yes")
                        || ((specifyPregnancy.equals("Yes")
                        || specifyKidney.equals("Yes")
                        || specifyTB.equals("Yes")
                        || specifyDiabetes.equals("Yes")
                        || specifyChronicLungDisease.equals("Yes")
                        || specifyHeartDisease.equals("Yes")
                        || specifyCancer.equals("Yes")
                        || specifyLiver.equals("Yes")
                        || difficultyInBreathingSymptom.equals("Yes"))))) {
                    risk = "High Risk";
                } else if ((contactWithSomeoneWithSymptoms.equals("Yes")
                        || contactWithChestpain.equals("Yes")
                        &&
                        (diarrhoeaSymptoms.equals("Yes")
                                || contactWithCough.equals("Yes")
                                || contactWithFever.equals("Yes")
                                || contactWithDifficultBreathing.equals("Yes")
                                || contactWithSneeze.equals("Yes")
                                || contactWithDiarrhoea.equals("Yes")
                                || contactWithSoreThroat.equals("Yes")
                                || coughSymptom.equals("Yes")
                                || feverSymptom.equals("Yes")
                                || contactWithLossOfSmell.equals("Yes")
                                || contactWithLossOfTaste.equals("Yes")
                                || lossOfTasteSymptoms.equals("Yes")
                                || lossOfSmellSymptoms.equals("Yes")
                                || sneezingSymptoms.equals("Yes")
                                || contactWithOtherFLu.equals("Yes")))) {
                    risk = "Medium Risk";
                } else {
                    risk = "Low Risk";
                }

                registerUserHealthData(fullname, date, age, gender, state, lga, phone, feverSymptom, coughSymptom,
                        difficultyInBreathingSymptom, sneezingSymptoms, chestPainSymptoms,
                        diarrhoeaSymptoms, soreThroatSymptoms, fluSymptoms, lossOfSmellSymptoms, lossOfTasteSymptoms,
                        contactWithSomeoneWithSymptoms, contactWithFever, contactWithCough,
                        contactWithDifficultBreathing, contactWithSneeze, contactWithChestpain,
                        contactWithDiarrhoea, contactWithOtherFLu, contactWithSoreThroat, contactWithLossOfSmell,
                        contactWithLossOfTaste, underlyingConditions,
                        specifyKidney, specifyPregnancy, specifyTB, specifyDiabetes, specifyLiver,
                        specifyChronicLungDisease, specifyCancer, specifyHeartDisease, specifyHIV,
                        treatment, enoughDrugsForThreeMonths, exposedToFacilityWithConfirmedCase,
                        someoneHelpingYouManageHIV, covid19CareFromSomeoneInHousehold,
//                        Long.parseLong(userId),
                        risk
                );
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,
                R.array.age_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mAgeSpinner.setAdapter(ageAdapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_dropdown_item);
        // Specify the layout to use when the list of choices appears
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mLanguageSpinner.setAdapter(languageAdapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderAdapter);

        mLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("ha");
                        break;
                    case 3:
                        setLocale("ig");
                        break;
                    case 4:
                        setLocale("yo");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //            // TODO Auto-generated method stub
                new DatePickerDialog(UserHealthDataActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
    }

    public void setupSpinners() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        //populates the quantity spinner ArrayList

        ArrayAdapter<String> statesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states);

        // Specify dropdown layout style - simple list view with 1 item per line
        statesAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // Apply the adapter to the spinner
        statesAdapter.notifyDataSetChanged();
        mStateSpinner.setAdapter(statesAdapter);

        // Set the integer mSelected to the constant values
        mStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mState = (String) parent.getItemAtPosition(position);
                setUpStatesSpinner(position);
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Unknown
            }
        });
    }
    /**
     * method to set up the state spinner
     *
     * @param position current position of the spinner
     */
    private void setUpStatesSpinner(int position) {
        List<String> list = new ArrayList<>(Nigeria.getLgasByState(states.get(position)));
        setUpLgaSpinner(list);
    }
    /**
     * Method to set up the local government areas corresponding to selected states
     *
     * @param lgas represents the local government areas of the selected state
     */
    private void setUpLgaSpinner(List<String> lgas) {
        ArrayAdapter lgaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lgas);
        lgaAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        lgaAdapter.notifyDataSetChanged();
        mLgaSpinner.setAdapter(lgaAdapter);

        mLgaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                mLga = (String) parent.getItemAtPosition(position);
//                    Toast.makeText(UserHealthDataActivity.this, "state: " + mState + " lga: " + mLga, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void resizeSpinner(Spinner spinner, int height) {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            //Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);

            //set popupWindow height to height
            popupWindow.setHeight(height);

        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";   //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mDate.setText(sdf.format(myCalendar.getTime()));

    }

    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(UserHealthDataActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        int radioButtonId2 = mFever.getCheckedRadioButtonId();
        rbFever = findViewById(radioButtonId2);

        int radioButtonId3 = mCough.getCheckedRadioButtonId();
        rbCough = findViewById(radioButtonId3);

        int radioButtonId4 = mDifficultyInBreathing.getCheckedRadioButtonId();
        rbDifficultyInBreathing = findViewById(radioButtonId4);

        int radioButtonId5 = mSneezing.getCheckedRadioButtonId();
        rbSneezing = findViewById(radioButtonId5);

        int radioButtonId6 = mChestPain.getCheckedRadioButtonId();
        rbChestPain = findViewById(radioButtonId6);

        int radioButtonId7 = mDiarrhoea.getCheckedRadioButtonId();
        rbDiarrhoea = findViewById(radioButtonId7);

        int radioButtonId8 = mFlu.getCheckedRadioButtonId();
        rbFlu = findViewById(radioButtonId8);

        int radioButtonId9 = mSoreThroatSymptoms.getCheckedRadioButtonId();
        rbSoreThroatSymptoms = findViewById(radioButtonId9);

        int radioButtonId10 = mContactWithFever.getCheckedRadioButtonId();
        rbContactWithFever = findViewById(radioButtonId10);

        int radioButtonId11 = mContactWithCough.getCheckedRadioButtonId();
        rbContactWithCough = findViewById(radioButtonId11);

        int radioButtonId12 = mContactWithDifficultBreathing.getCheckedRadioButtonId();
        rbContactWithDifficultBreathing = findViewById(radioButtonId12);

        int radioButtonId13 = mContactWithSneeze.getCheckedRadioButtonId();
        rbContactWithSneeze = findViewById(radioButtonId13);

        int radioButtonId14 = mContactWithChestpain.getCheckedRadioButtonId();
        rbContactWithChestpain = findViewById(radioButtonId14);

        int radioButtonId15 = mContactWithDiarrhoea.getCheckedRadioButtonId();
        rbContactWithDiarrhoea = findViewById(radioButtonId15);

        int radioButtonId16 = mContactWithOtherFlu.getCheckedRadioButtonId();
        rbContactWithOtherFlu = findViewById(radioButtonId16);

        int radioButtonId17 = mContactWithSoreThroat.getCheckedRadioButtonId();
        rbContactWithSoreThroat = findViewById(radioButtonId17);

        int radioButtonId18 = mUnderlyingConditions.getCheckedRadioButtonId();
        rbUnderlyingConditions = findViewById(radioButtonId18);

        int radioButtonId19 = mSpecifyKidney.getCheckedRadioButtonId();
        rbSpecifyKidney = findViewById(radioButtonId19);

        int radioButtonId20 = mSpecifyPregnancy.getCheckedRadioButtonId();
        rbSpecifyPregnancy = findViewById(radioButtonId20);

        int radioButtonId21 = mSpecifyTB.getCheckedRadioButtonId();
        rbSpecifyTB = findViewById(radioButtonId21);

        int radioButtonId22 = mSpecifyDiabetes.getCheckedRadioButtonId();
        rbSpecifyDiabetes = findViewById(radioButtonId22);

        int radioButtonId23 = mSpecifyLiver.getCheckedRadioButtonId();
        rbSpecifyLiver = findViewById(radioButtonId23);
        int radioButtonId24 = mSpecifyChronicLungDisease.getCheckedRadioButtonId();
        rbSpecifyChronicLungDisease = findViewById(radioButtonId24);

        int radioButtonId25 = mSpecifyCancer.getCheckedRadioButtonId();
        rbSpecifyCancer = findViewById(radioButtonId25);

        int radioButtonId26 = mSpecifyHeartDisease.getCheckedRadioButtonId();
        rbSpecifyHeartDisease = findViewById(radioButtonId26);

        int radioButtonId27 = mContactWithSomeoneWithSymptoms.getCheckedRadioButtonId();
        rbContactWithSomeoneWithSymptoms = findViewById(radioButtonId27);

        int radioButtonId30 = mSpecifyHIV.getCheckedRadioButtonId();
        rbSpecifyHIV = findViewById(radioButtonId30);

        int radioButtonId31 = mTreatment.getCheckedRadioButtonId();
        rbTreatment = findViewById(radioButtonId31);

        int radioButtonId32 = mEnoughDrugsForThreeMonths.getCheckedRadioButtonId();
        rbEnoughDrugsForThreeMonths = findViewById(radioButtonId32);

        int radioButtonId33 = mExposedToFacilityWithConfirmedCase.getCheckedRadioButtonId();
        rbExposedToFacilityWithConfirmedCase = findViewById(radioButtonId33);

        int radioButtonId34 = mCovid19CareFromSomeoneInHousehold.getCheckedRadioButtonId();
        rbCovid19CareFromSomeoneInHousehold = findViewById(radioButtonId34);

        int radioButtonId35 = mSomeoneHelpingYouManageHIV.getCheckedRadioButtonId();
        rbSomeoneHelpingYouManageHIV = findViewById(radioButtonId35);

        int radioButtonId36 = mLossOfSmellSymptoms.getCheckedRadioButtonId();
        rbLossOfSmellSymptoms = findViewById(radioButtonId36);

        int radioButtonId37 = mLossOfTasteSymptoms.getCheckedRadioButtonId();
        rbLossOfTasteSymptoms = findViewById(radioButtonId37);

        int radioButtonId38 = mContactWithLossOfTaste.getCheckedRadioButtonId();
        rbContactWithLossOfTaste = findViewById(radioButtonId38);

        int radioButtonId39 = mContactWithLossOfSmell.getCheckedRadioButtonId();
        rbContactWithLossOfSmell = findViewById(radioButtonId39);

        switch (view.getId()) {
            case R.id.radio_condition_no:
                if (checked)
                    findViewById(R.id.first_group).setVisibility(View.GONE);
                break;

            case R.id.radio_condition_yes:
                if (checked)
                    findViewById(R.id.first_group).setVisibility(View.VISIBLE);
                break;
        }

        switch (view.getId()) {
            case R.id.radio_specifyHIV_no:
                if (checked)
                    findViewById(R.id.second_group).setVisibility(View.GONE);
                break;

            case R.id.radio_specifyHIV_yes:
                if (checked)
                    findViewById(R.id.second_group).setVisibility(View.VISIBLE);
                break;
        }
    }

    public void registerUserHealthData(String fullname, String date, String age, String gender, String state, String lga, String phone, String feverSymptom, String coughSymptom,
                                       String difficultyInBreathingSymptom, String sneezingSymptoms, String chestPainSymptoms,
                                       String diarrhoeaSymptoms, String fluSymptoms, String soreThroatSymptoms, String lossOfSmellSymptoms,
                                       String lossOfTasteSymptoms,
                                       String contactWithSomeoneWithSymptoms, String contactWithFever, String contactWithCough,
                                       String contactWithDifficultBreathing, String contactWithSneeze, String contactWithChestpain,
                                       String contactWithDiarrhoea, String contactWithOtherFLu, String contactWithSoreThroat,
                                       String contactWithLossOfSmell, String contactWithLossOfTaste, String underlyingConditions,
                                       String specifyKidney, String specifyPregnancy, String specifyTB, String specifyDiabetes, String specifyLiver,
                                       String specifyChronicLungDisease, String specifyCancer, String specifyHeartDisease, String specifyHIV,
                                       String treatment, String enoughDrugsForThreeMonths, String exposedToFacilityWithConfirmedCase,
                                       String someoneHelpingYouManageHIV, String covid19CareFromSomeoneInHousehold, String risk) {
        //making api calls
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending Data...");
        progressDialog.show();

        //building retrofit object
        Call<UserHealthData> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUserHealthData(
                        fullname, date, age, gender, state, lga, phone, feverSymptom, coughSymptom,
                        difficultyInBreathingSymptom, sneezingSymptoms, chestPainSymptoms,
                        diarrhoeaSymptoms, soreThroatSymptoms, fluSymptoms, lossOfSmellSymptoms, lossOfTasteSymptoms,
                        contactWithSomeoneWithSymptoms, contactWithFever, contactWithCough,
                        contactWithDifficultBreathing, contactWithSneeze, contactWithChestpain,
                        contactWithDiarrhoea, contactWithOtherFLu, contactWithSoreThroat, contactWithLossOfSmell,
                        contactWithLossOfTaste, underlyingConditions,
                        specifyKidney, specifyPregnancy, specifyTB, specifyDiabetes, specifyLiver,
                        specifyChronicLungDisease, specifyCancer, specifyHeartDisease, specifyHIV,
                        treatment, enoughDrugsForThreeMonths, exposedToFacilityWithConfirmedCase,
                        someoneHelpingYouManageHIV, covid19CareFromSomeoneInHousehold,
//                        userId,
                        risk);
        call.enqueue(new Callback<UserHealthData>() {
            @Override
            public void onResponse(Call<UserHealthData> call, Response<UserHealthData> response) {
                if (response.isSuccessful()) {
                    if (risk.equals("High Risk")) {
                        loadingBar.dismiss();
                        Toast.makeText(UserHealthDataActivity.this, "Data Saved!", Toast.LENGTH_LONG).show();
                        Intent highRiskIntent = new Intent(UserHealthDataActivity.this, HighRiskActivity.class);
                        highRiskIntent.putExtra("risk", "High risk");
                        startActivity(highRiskIntent);
                        finish();
                    } else if (risk.equals("Medium Risk")) {
                        loadingBar.dismiss();
                        Intent mediumRiskIntent = new Intent(UserHealthDataActivity.this, MediumRiskActivity.class);
                        Toast.makeText(UserHealthDataActivity.this, "Data Saved!", Toast.LENGTH_LONG).show();
                        startActivity(mediumRiskIntent);
                        finish();
                    } else {
                        loadingBar.dismiss();
                        Toast.makeText(UserHealthDataActivity.this, "Data Saved!", Toast.LENGTH_LONG).show();
                        Intent lowRiskIntent = new Intent(UserHealthDataActivity.this, LowRiskActivity.class);
                        startActivity(lowRiskIntent);
                        finish();
                    }
//                    //send push notification
//                    NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                    Notification notify = new Notification.Builder
//                            (getApplicationContext()).setContentTitle("My Notification").setContentText("You have a notification from "+ fullname).
//                            setContentTitle("Notification").setSmallIcon(R.drawable.icon).build();
//
//                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
//                    notif.notify(0, notify);

                } else {
                    loadingBar.dismiss();
                    Toast.makeText(UserHealthDataActivity.this, "Data sending failed!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserHealthData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UserHealthDataActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.main_settings:
//                settings();
                return true;
            case R.id.main_logout:
//                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewGroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
        Button ok = dialogView.findViewById(R.id.buttonOk);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStatus();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

//    private void logout() {
//        Intent intent = new Intent(UserHealthDataActivity.this, LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        finish();
//    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
//    public HashMap<String, String> getUserId() {
//        HashMap<String, String> id = new HashMap<>();
//        SharedPreferences sharedPreferences = this.getSharedPreferences("userId", Context.MODE_PRIVATE);
//        id.put("userId", sharedPreferences.getString("userId", null));
//        return id;
//    }

    public HashMap<String, String> status() {
        HashMap<String, String> user = new HashMap<>();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("status", Context.MODE_PRIVATE);
        user.put("status", sharedPreferences.getString("status", null));
        return user;
    }

    @SuppressLint("ApplySharedPref")
    public void saveStatus() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("status", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("status", "1");
        editor.commit();
    }
}