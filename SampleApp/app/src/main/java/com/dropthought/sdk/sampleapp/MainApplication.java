package com.dropthought.sdk.sampleapp;

import android.app.Application;
import com.dropthought.app.sdk.Dropthought;

public class MainApplication extends Application {
    public static final String DT_ACCOUNT_API_KEY = "paste your api key here";
    public static final String DT_ACCOUNT_SURVEY_ID = "paste your survey id here";

    @Override
    public void onCreate() {
        super.onCreate();
        Dropthought.init(
                this,
                DT_ACCOUNT_API_KEY,
                DT_ACCOUNT_SURVEY_ID
        );
    }
}
