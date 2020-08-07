package mobile.fhi360.covid_19selfscreeningtool.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.model.UserHealthData;

public class HealthDetailsActivity extends AppCompatActivity {
    TextView detailFullname;
    TextView detailDate;
    TextView detailFeverSymptom;
    TextView detailCoughSymptom;
    TextView detailDifficultyInBreathingSymptom;
    TextView detailSneezingSymptoms;
    TextView detailsChestPainSymptoms;
    TextView detailsDiarrhoeaSymptoms;
    TextView detailsFluSymptoms;
    TextView detailsSoreThroatSymptoms;
    TextView detailsLossOfSmellSymptoms;
    TextView detailsContactWithSomeoneWithSymptoms;
    TextView detailsExposedToFacilityWithConfirmedCase;
    TextView detailsStateVisited;
    TextView detailsContactWithFever;
    TextView detailsContactWithCough;
    TextView detailsContactWithDifficultBreathing;
    TextView detailsContactWithSneeze;
    TextView detailsContactWithChestpain;
    TextView detailsContactWithDiarrhoea;
    TextView detailsContactWithOtherFLu;
    TextView detailsContactWithSoreThroat;
    TextView detailsUnderlyingConditions;
    TextView detailsSpecifyKidney;
    TextView detailsSpecifyPregnancy;
    TextView detailsSpecifyTB;
    TextView detailsSpecifyDiabetes;
    TextView detailsSpecifyLiver;
    TextView detailsChronicLungDisease;
    TextView detailsSpecifyCancer;
    TextView detailsHeartDisease;
    TextView detailsSpecifyHIV;
    TextView detailsTreatment;
    TextView detailsSomeoneHelpingYouManageHIV;
    TextView detailsCovid19CareFromSomeoneInHousehold;
    TextView detailsEnoughDrugsForThreeMonths;
    TextView detailsLossOfTasteSymptoms;
    TextView detailsContactWithLossOfTaste;
    TextView detailsContactWithLossOfSmell;

    TextView detailsPhone;
    TextView mDisplayOutcome;

    Button mBtnCall, mBtnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_details);
        detailFullname = findViewById(R.id.detailsFullname);
        detailDate = findViewById(R.id.detailsDate);
        detailFeverSymptom = findViewById(R.id.detailsFever);
        detailCoughSymptom = findViewById(R.id.detailsCoughSymptom);
        detailDifficultyInBreathingSymptom = findViewById(R.id.detailsDifficultyInBreathingSymptom);
        detailSneezingSymptoms = findViewById(R.id.tvSneezingSymptoms);
        detailsChestPainSymptoms = findViewById(R.id.detailsChestPainSymptoms);
        detailsDiarrhoeaSymptoms = findViewById(R.id.detailsDiarrhoeaSymptoms);
        detailsFluSymptoms = findViewById(R.id.detailsFluSymptoms);
        detailsSoreThroatSymptoms = findViewById(R.id.detailsSoreThroatSymptoms);
        detailsLossOfSmellSymptoms = findViewById(R.id.detailsLossOfSmellSymptoms);
        detailsLossOfTasteSymptoms = findViewById(R.id.detailsLossOfTasteSymptoms);
        detailsContactWithSomeoneWithSymptoms = findViewById(R.id.detailsContactWithSomeoneWithSymptoms);
        detailsExposedToFacilityWithConfirmedCase = findViewById(R.id.detailsExposedToFacilityWithConfirmedCase);
        detailsStateVisited = findViewById(R.id.detailsStateVisited);
        detailsContactWithFever = findViewById(R.id.detailsContactWithFever);
        detailsContactWithCough = findViewById(R.id.detailsContactWithCough);
        detailsContactWithDifficultBreathing = findViewById(R.id.detailsContactWithDifficultBreathing);
        detailsContactWithSneeze = findViewById(R.id.detailsContactWithSneeze);
        detailsContactWithChestpain = findViewById(R.id.detailsContactWithChestpain);
        detailsContactWithDiarrhoea = findViewById(R.id.detailsContactWithDiarrhoea);
        detailsContactWithOtherFLu = findViewById(R.id.detailsContactWithOtherFLu);
        detailsContactWithSoreThroat = findViewById(R.id.detailsContactWithSoreThroat);
        detailsContactWithLossOfTaste = findViewById(R.id.detailsContactWithLossOfTaste);
        detailsContactWithLossOfSmell = findViewById(R.id.detailsContactWithLossOfSmell);
        detailsPhone = findViewById(R.id.detailsPhone);
        mBtnCall = findViewById(R.id.btnCall);
        mBtnText = findViewById(R.id.btnText);


        detailsUnderlyingConditions = findViewById(R.id.detailsUnderlyingConditions);
        detailsSpecifyKidney = findViewById(R.id.detailsSpecifyKidney);
        detailsSpecifyPregnancy = findViewById(R.id.detailsSpecifyPregnancy);
        detailsSpecifyTB = findViewById(R.id.detailsSpecifyTB);
        detailsSpecifyDiabetes = findViewById(R.id.detailsSpecifyDiabetes);
        detailsSpecifyLiver = findViewById(R.id.detailsSpecifyLiver);
        detailsChronicLungDisease = findViewById(R.id.detailsChronicLungDisease);
        detailsSpecifyCancer = findViewById(R.id.detailsSpecifyCancer);
        detailsHeartDisease = findViewById(R.id.detailsHeartDisease);
        detailsSpecifyHIV = findViewById(R.id.detailsSpecifyHIV);
        detailsTreatment = findViewById(R.id.detailsTreatment);
        detailsSomeoneHelpingYouManageHIV = findViewById(R.id.detailsSomeoneHelpingYouManageHIV);
        detailsEnoughDrugsForThreeMonths = findViewById(R.id.detailsEnoughDrugsForThreeMonths);
        detailsCovid19CareFromSomeoneInHousehold = findViewById(R.id.detailsCovid19CareFromSomeoneInHousehold);
        mDisplayOutcome = findViewById(R.id.displayResult);

        mBtnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms();
            }
        });

        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        UserHealthData userHealthData;
        userHealthData = (UserHealthData) getIntent().getSerializableExtra("key");
        detailFullname.setText(userHealthData.getFullname());
        detailDate.setText(userHealthData.getDate().toString());
        detailsPhone.setText(userHealthData.getPhone());
        detailFeverSymptom.setText(userHealthData.getFeverSymptom());
        detailCoughSymptom.setText(userHealthData.getCoughSymptom());
        detailDifficultyInBreathingSymptom.setText(userHealthData.getDifficultyInBreathingSymptom());
        detailSneezingSymptoms.setText(userHealthData.getSneezingSymptoms());
        detailsChestPainSymptoms.setText(userHealthData.getChestPainSymptoms());
        detailsDiarrhoeaSymptoms.setText(userHealthData.getDiarrhoeaSymptoms());
        detailsFluSymptoms.setText(userHealthData.getFluSymptoms());
        detailsSoreThroatSymptoms.setText(userHealthData.getSoreThroatSymptoms());
        detailsLossOfSmellSymptoms.setText(userHealthData.getLossOfSmellSymptoms());
        detailsLossOfTasteSymptoms.setText(userHealthData.getLossOfTasteSymptoms());
        detailsContactWithSomeoneWithSymptoms.setText(userHealthData.getContactWithSomeoneWithSymptoms());
        detailsExposedToFacilityWithConfirmedCase.setText(userHealthData.getExposedToFacilityWithConfirmedCase());
        detailsStateVisited.setText(userHealthData.getStateVisited());
        detailsContactWithFever.setText(userHealthData.getContactWithFever());
        detailsContactWithCough.setText(userHealthData.getContactWithCough());
        detailsContactWithDifficultBreathing.setText(userHealthData.getContactWithDifficultBreathing());
        detailsContactWithSneeze.setText(userHealthData.getContactWithSneeze());
        detailsContactWithChestpain.setText(userHealthData.getContactWithChestpain());
        detailsContactWithDiarrhoea.setText(userHealthData.getContactWithDiarrhoea());
        detailsContactWithOtherFLu.setText(userHealthData.getContactWithOtherFLu());
        detailsContactWithSoreThroat.setText(userHealthData.getContactWithSoreThroat());
        detailsLossOfSmellSymptoms.setText(userHealthData.getLossOfSmellSymptoms());
        detailsLossOfTasteSymptoms.setText(userHealthData.getLossOfTasteSymptoms());
        detailsUnderlyingConditions.setText(userHealthData.getUnderlyingConditions());
        detailsSpecifyKidney.setText(userHealthData.getSpecifyKidney());
        detailsSpecifyPregnancy.setText(userHealthData.getSpecifyPregnancy());
        detailsSpecifyTB.setText(userHealthData.getSpecifyTB());
        detailsSpecifyDiabetes.setText(userHealthData.getSpecifyDiabetes());
        detailsSpecifyLiver.setText(userHealthData.getSpecifyLiver());
        detailsChronicLungDisease.setText(userHealthData.getSpecifyChronicLungDisease());
        detailsSpecifyCancer.setText(userHealthData.getSpecifyCancer());
        detailsHeartDisease.setText(userHealthData.getSpecifyHeartDisease());
        detailsSpecifyHIV.setText(userHealthData.getSpecifyCancer());
        detailsTreatment.setText(userHealthData.getTreatment());
        detailsContactWithLossOfSmell.setText(userHealthData.getContactWithLossOfSmell());
        detailsContactWithLossOfTaste.setText(userHealthData.getContactWithLossOfTaste());
        detailsSomeoneHelpingYouManageHIV.setText(userHealthData.getSomeoneHelpingYouManageHIV());
        detailsEnoughDrugsForThreeMonths.setText(userHealthData.getEnoughDrugsForThreeMonths());
        detailsCovid19CareFromSomeoneInHousehold.setText(userHealthData.getCovid19CareFromSomeoneInHousehold());

        if ((detailsExposedToFacilityWithConfirmedCase.getText().toString().equalsIgnoreCase("yes")
                || !detailsStateVisited.getText().toString().equalsIgnoreCase("None")
                || (detailsSpecifyPregnancy.getText().toString().equalsIgnoreCase("yes")
                || detailsChronicLungDisease.getText().toString().equalsIgnoreCase("yes")
                || detailsSpecifyDiabetes.getText().toString().equalsIgnoreCase("yes")
                || detailsSpecifyTB.getText().toString().equalsIgnoreCase("yes")
                || detailsSpecifyLiver.getText().toString().equalsIgnoreCase("yes")
                || detailsSpecifyCancer.getText().toString().equalsIgnoreCase("yes")
                || detailsSpecifyHIV.getText().toString().equalsIgnoreCase("yes")
                || detailDifficultyInBreathingSymptom.getText().toString().equalsIgnoreCase("yes")
                || detailsSpecifyKidney.getText().toString().equalsIgnoreCase("yes")))) {
            mDisplayOutcome.setText("High risk");
            mDisplayOutcome.setBackgroundColor(Color.parseColor("#E82815"));
        }else if (detailsContactWithSomeoneWithSymptoms.getText().toString().equalsIgnoreCase("yes")
                && (detailFeverSymptom.getText().toString().equalsIgnoreCase("Yes")
                || detailCoughSymptom.getText().toString().equalsIgnoreCase("Yes")
                || detailSneezingSymptoms.getText().toString().equalsIgnoreCase("yes")
                || detailsChestPainSymptoms.getText().toString().equalsIgnoreCase("yes")
                || detailsDiarrhoeaSymptoms.getText().toString().equalsIgnoreCase("yes")
                || detailsContactWithLossOfSmell.getText().toString().equalsIgnoreCase("yes")
                || detailsFluSymptoms.getText().toString().equalsIgnoreCase("yes")
                || detailsSoreThroatSymptoms.getText().toString().equalsIgnoreCase("yes")
                || detailsLossOfSmellSymptoms.getText().toString().equalsIgnoreCase("yes")
                || detailsContactWithChestpain.getText().toString().equalsIgnoreCase("yes")
                || detailsContactWithFever.getText().toString().equalsIgnoreCase("yes")
                || detailsContactWithSneeze.getText().toString().equalsIgnoreCase("yes")
                || detailsContactWithLossOfTaste.getText().toString().equalsIgnoreCase("yes")
                || detailsContactWithDiarrhoea.getText().toString().equalsIgnoreCase("yes")
                || detailsContactWithCough.getText().toString().equalsIgnoreCase("yes")
                || detailsContactWithSoreThroat.getText().toString().equalsIgnoreCase("yes")
                || detailsContactWithOtherFLu.getText().toString().equalsIgnoreCase("yes")
        )) {
            mDisplayOutcome.setText("Medium risk");
            mDisplayOutcome.setBackgroundColor(Color.parseColor("#E88515"));

        } else {
            mDisplayOutcome.setText("Low risk");
            mDisplayOutcome.setBackgroundColor(Color.parseColor("#1BE815"));
        }
    }

    private void makeCall() {
        // TODO Auto-generated method stub
        try {
            //  Toast.makeText(mContext, "initiating call to " + phone.getText().toString(), Toast.LENGTH_LONG).show();
            Log.e("Health Details Activity", "Invoking call");
            if (ContextCompat.checkSelfPermission(HealthDetailsActivity.this, "android.permission.CALL_PHONE") == 0) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + detailsPhone.getText().toString()));
                startActivity(i);
            } else {
                ActivityCompat.requestPermissions((Activity) HealthDetailsActivity.this, new String[]{"android.permission.CALL_PHONE"}, 0);
            }

        } catch (Exception e) {
            Log.e("Health Details Activity", "Failed to invoke call", e);
        }
    }

    private void sendSms() {
        Uri uri = Uri.parse("smsto: " + detailsPhone.getText().toString());
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", " ");
        startActivity(intent);
    }
}