# Dropthought SDK for Android

 (...description...)

## Integrate Dropthought SDK to your project

### 1. Project setup

#### add repository

open the project's `build.gradle` file, add Dropthought's maven repo under `allprojects > repositories`


```diff
allprojects {
    repositories {
+       // Add Dropthought's maven repo
+       // to repositories
        maven {
            url "https://dt360-dtp-mobile.s3.us-east-1.amazonaws.com/releases"
        }

        // ...
        google()
        jcenter()

    }
}
```

#### add dependency

open your module's `build.gradle` file, add dropthought sdk dependency:

```diff
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

+   // add dropthought sdk dependency
    implementation "com.dropthought.app:dt-sdk:1.1.8"
}
```

#### set multiDexEnabled to true

open your module's `build.gradle` file, set multiDexEnabled to true

```diff
android {
    compileSdkVersion 28

    defaultConfig {
        //...
+       multiDexEnabled true
    }
}
```

#### required permission

Please make sure that your setup internet permission `android.permission.INTERNET` in your `AndroidManifest.xml`

```diff
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.dropthought.sdk.sampleapp">

+   <!-- make sure you have Internet permission  -->
    <uses-permission android:name="android.permission.INTERNET" />

</manifest>
```

### 2. Dropthought SDK initialization

calling `Dropthought.init()` inside your `onCreate` method of `MainActivity.java` to initialize Dropthought, for example:

```diff
package com.dropthought.sdk.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

+// import dropthought package
import com.dropthought.app.sdk.Dropthought;

public class MainActivity extends AppCompatActivity {
    public static final String DT_ACCOUNT_API_KEY = "your-api-key";
    public static final String DT_SURVEY_ID = "your survey's id";

    @Override
    public void onCreate() {
        super.onCreate();
        setContentView(R.layout.activity_main);

+       // call Dropthought.init(Activity, API_KEY, SURVEY_ID) inside onCreate method
+       // Remember: you must supply the activity (e.g. this)
        Dropthought.init(
                this,
                DT_ACCOUNT_API_KEY,
                DT_SURVEY_ID
        );
    }
}
```

If you have multiple surveys in your app, it is OK to initialize Dropthought without the survey id, for example:


```diff
package com.dropthought.sdk.sampleapp;

import androidx.appcompat.app.AppCompatActivity;
+// import dropthought package
import com.dropthought.app.sdk.Dropthought;

public class MainActivity extends AppCompatActivity {
    public static final String DT_ACCOUNT_API_KEY = "your-api-key";

    @Override
    public void onCreate() {
        super.onCreate();
        setContentView(R.layout.activity_main);

+       // call Dropthought.init(Activity, API_KEY) inside onCreate method
+       // Remember: you must supply the activity (e.g. this)
        Dropthought.init(
                this,
                DT_ACCOUNT_API_KEY
        );
    }
}
```

**Contact Customer Support at cs@dropthought.com to get help on how to get your api-key and survey-id.**

### 3. Open Dropthought Survey for getting feedback

You can either:
 - use `Dropthought.startSurveyActivity(Activity activity)` to open the survey that you specified during `Dropthought.init(Application app, String apiKey, String surveyId)`.
 - or, use `Dropthought.startSurveyActivity(Activity activity, String surveyId)` to open the survey with a different survey id.

For example, a button that when users click on, opens the survey,

```diff
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dropthought.init(...)

        Button button = findViewById(R.id.btn_open_survey);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeSurvey();
            }
        });
    }


    public void takeSurvey() {
+       // This is how you display a survey for the user to take
+       // Remember: you must supply the activity (e.g. this)
        Dropthought.startSurveyActivity(
                this
        );
    }

    /*...*/
}
```

### Additional feature: offline mode

When user finishes a survey under no network or a bad network, the survey result is saved offline. Every time when `Dropthought.init` is called, Dropthought SDK would try to upload the saved results(if any) again once.

Or, you could call `Dropthought.uploadOfflineFeedbacks()` manually to try to upload the saved results once if your app has network status monitor.


## FAQ

Contact Customer Support at cs@dropthought.com to get help on how to publish your program through SDK
