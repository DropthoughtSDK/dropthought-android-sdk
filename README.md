# Dropthought SDK for Android

 (...description...)

## Integrate Dropthought SDK to your project

### 1. Project setup

#### add repository

open the project's `build.gradle` file, add Dropthought's maven repo under `allprojects > repositories`

**Contact Customer Support at cs@dropthought.com to get help on how to get the accessKey and secretKey.**

```diff
allprojects {
    repositories {
+       maven {
+           // Dropthought's maven repo, the key has only read access to this url
+           url("s3://maven-repo.demo.dropthought.com/releases")
+           credentials(AwsCredentials) {
+               accessKey "paste the access key here"
+               secretKey "paste the secret key here"
+           }
+       }
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

+   implementation "com.dropthought.app:dt-sdk:+"
+   // or, if your want to specify the version
+   // implementation "com.dropthought.app:dt-sdk:1.0.0"
}
```

#### required permission

Please make sure that your setup internet permission `android.permission.INTERNET` in your `AndroidManifest.xml`

```diff
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.dropthought.sdk.sampleapp">

+   <uses-permission android:name="android.permission.INTERNET" />

</manifest>
```

### 2. Dropthought SDK initialization

calling `Dropthought.init()` inside your `onCreated` method of `MainApplication.java` to initialize Dropthought, for example:

```diff
package com.dropthought.sdk.sampleapp;

import android.app.Application;
+import com.dropthought.app.sdk.Dropthought;

public class MainApplication extends Application {
+   public static final String DT_ACCOUNT_API_KEY = "your-api-key";
+   public static final String DT_SURVEY_ID = "your survey's id";

    @Override
    public void onCreate() {
        super.onCreate();
+       Dropthought.init(
+               this,
+               DT_ACCOUNT_API_KEY,
+               DT_SURVEY_ID
        );
    }
}
```

However, if you don't have a specific survey id for your app (you could have multiple surveys for your app), it is OK to initialize Dropthought without the survey id, for example:


```diff
package com.dropthought.sdk.sampleapp;

import android.app.Application;
+import com.dropthought.app.sdk.Dropthought;

public class MainApplication extends Application {
+   public static final String DT_ACCOUNT_API_KEY = "your-api-key";

    @Override
    public void onCreate() {
        super.onCreate();
+       Dropthought.init(
+               this,
+               DT_ACCOUNT_API_KEY,
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
+import com.dropthought.app.sdk.Dropthought;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_open_survey);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
+               takeSurvey();
            }
        });
    }

+   public void takeSurvey() {
+       // This is how you display a survey for the user to take
+       // Remember: you must supply the activity (e.g. this)
+       Dropthought.startSurveyActivity(
+               this
+       );
+    }

    /*...*/
}
```

### Additional feature: offline mode

When user finishes a survey under no network or a bad network, the survey result is saved offline. Every time when `Dropthought.init` is called, Dropthought SDK would try to upload the saved results(if any) again once.

Or, you could call `Dropthought.uploadOfflineFeedbacks()` manually to try to upload the saved results once if your app has network status monitor.


## FAQ

Contact Customer Support at cs@dropthought.com to get help on how to publish your program through SDK
