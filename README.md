# Dropthought SDK for Android

This repository contains all Dropthought android SDK sources.

## Latest version

5.1.0

## Integrate Dropthought SDK to your project

### 1. Project setup

#### add repository

open the project's `settings.gradle` file, add Dropthought's maven repo under `repositories`

```diff
allprojects {
    repositories {
        // Add Dropthought's maven repo to repositories
+       maven {
+           url "https://dropthought-sdk.s3.amazonaws.com/releases/"
+       }

        // ...
        google()

+       mavenCentral{
+           // We don't want to fetch react-native from Maven Central as there are older versions over there.
+           content {
+               excludeGroup "com.facebook.react"
+           }
+       }
    }
}
```

#### add dependency

open your module's `app/build.gradle` file, add dropthought sdk dependency and handle appcompat:1.3.1:

```diff
dependencies {
// In RN 0.64, TextView might crash without below changes
-   implementation 'androidx.appcompat:appcompat:1.4.1'
+   implementation ("androidx.appcompat:appcompat:1.3.1") {
+       version {
+           strictly '1.3.1'
+       }
+   }
    ...

    // add dropthought sdk dependency
+   implementation "com.dropthought.app:dt-sdk:5.1.0"
}
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

        // call Dropthought.init(Activity, API_KEY) inside onCreate method
        // Remember: you must supply the activity (e.g. this)
+       Dropthought.init(
+               this,
+               DT_ACCOUNT_API_KEY
+       );
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

-   use `Dropthought.openSurveyActivity(Activity activity, String visibilityId)`

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
        // This is how you display a survey for the user to take
        // Remember: you must supply the activity (e.g. this)
+       Dropthought.openSurveyActivity(
+           this,
+           VISIBILITY_ID
+       );
    }

    /*...*/
}
```

### Additional feature: style

-   set survey theme: `Dropthought.setAppearance("system" | "light" | "dark")`
-   set survey font color: `Dropthought.setFontColor()`
-   set survey background color: `Dropthought.setBackgroundColor()`
-   set survey metadata: `Dropthought.setSurveyMetadata()`

### Additional feature: offline mode

When user finishes a survey under no network or a bad network, the survey result is saved offline. Every time when `Dropthought.init` is called, Dropthought SDK would try to upload the saved results(if any) again once.

Or, you could call `Dropthought.uploadOfflineFeedbacks()` manually to try to upload the saved results once if your app has network status monitor.

## FAQ

Contact Customer Support at cs@dropthought.com to get help on how to publish your program through SDK
