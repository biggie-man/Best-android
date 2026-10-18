plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.luaeditor"
    compileSdk = 34

   defaultConfig {
    applicationId = "com.example.luaeditor"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
  }

   buildTypes {
   release {
    isMinifyEnabled = false
    proguardFiles(
    getDefaultProguardFile("proguard-android-optimize.txt"),
    "proguard-rules.pro"
   )
   }
   }
}

dependencies {
    // Ace Editor library for Lua support and autocomplete
    implementation("com.github.AloisKnauss:ace-editor-android:2.0.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.10.0")
}
