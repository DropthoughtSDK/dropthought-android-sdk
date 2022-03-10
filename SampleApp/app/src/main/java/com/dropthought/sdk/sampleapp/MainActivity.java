package com.dropthought.sdk.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dropthought.app.sdk.Dropthought;

public class MainActivity extends AppCompatActivity {
    public static final String DT_ACCOUNT_API_KEY = "paste your api key here";
    public static final String DT_SURVEY_ID = "paste your survey id here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dropthought.init(
                this,
                DT_ACCOUNT_API_KEY,
                DT_SURVEY_ID
        );

        Button button = findViewById(R.id.btn_open_survey);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeSurvey();
            }
        });
    }

    public void takeSurvey() {
        // This is how you display a survey for the user to take
        // Remember: you must supply the activity (e.g. this)
        Dropthought.startSurveyActivity(
                this
        );

    }
}
