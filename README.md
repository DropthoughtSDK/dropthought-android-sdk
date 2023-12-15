# Dropthought SDK for Android

This repository contains all Dropthought Android SDK sources.

## Latest version

5.6.0

## Features

-   Bijliride Theme
-   Picture Choice
-   Matrix choice
-   Multiple open question
-   Matrix rating
-   Dropdown
-   Rating slider
-   Ranking

## Integrate Dropthought SDK to your project

### 1. Project setup

#### - Add repository

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

+       maven { url "https://jitpack.io" }
    }
}
```

#### - Add dependency

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

    // need to add this line to for react-native-secure-key-store
+   implementation 'androidx.security:security-crypto:1.0.0-rc03'

    // add dropthought sdk dependency
+   implementation "com.dropthought.app:dt-sdk:5.6.0"
}
```

</br>
</br>

### 2. Dropthought SDK initialization

Call `Dropthought.init()` inside your `onCreate` method of `MainActivity.java` to initialize Dropthought.

For example:

```diff
package com.dropthought.sdk.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

// import dropthought package
+import com.dropthought.app.sdk.Dropthought;

public class MainActivity extends AppCompatActivity {

    ...

    @Override
    public void onCreate() {
        super.onCreate();
        setContentView(R.layout.activity_main);

        // call Dropthought.init(Activity, YOUR_API_KEY) inside onCreate method
        // Remember: you must supply the activity (e.g. this)
+       Dropthought.init(
+               this,
+               YOUR_API_KEY
+       );

        ...

    }
}
```

_Note: You can find you API key in the web dashboard. (If you don't have permission, please contact your admin)_

<img src="https://github.com/DropthoughtSDK/dropthought-ios-sdk/raw/master/imgs/image_apiKey.jpeg">

</br>
</br>

### 3. Open Dropthought Survey Screen to collect feedback

`Dropthought.openSurveyActivity(Activity activity, String visibilityId)`

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
                // This is how you display a survey for the user to take
                // Remember: you must supply the activity (e.g. this)
+               Dropthought.openSurveyActivity(
+                   this,
+                   YOUR_VISIBILITY_ID
+               );
            }
        });
    }

    /*...*/

}
```

_Note: You can find and copy your visibility ID here in Enterprise app_

<img src="https://github.com/DropthoughtSDK/dropthought-ios-sdk/raw/master/imgs/image_visibility.jpeg">

</br>
</br>

## Additional features

### - Set Survey Metadata

`Dropthought.setSurveyMetadata(Bundle metadata)`

```java
Bundle metadata = new Bundle();
metadata.putString("name", "Barney");
metadata.putString("age", "36");
Dropthought.setSurveyMetadata(metadata);
```

</br>

### - Upload offline feedbacks

When user finishes a survey under no network or a bad network, the survey result is saved offline. Every time when `Dropthought.init` is called, Dropthought SDK would try to upload the saved results(if any) again once.

Or, you could call `Dropthought.uploadOfflineFeedbacks()` manually to try to upload the saved results once if your app has network status monitor.
</br>
</br>

## FAQ

Contact Customer Support at cs@dropthought.com to get help on how to publish your program through SDK
