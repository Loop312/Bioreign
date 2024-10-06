plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "com.example.bioreign"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.bioreign"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables{
            useSupportLibrary = true
        }
    }

    compileOptions{
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

//    buildFeatures {
//        compose = true
//    }
//    composeOptions{
//        kotlinCompilerExtensionVersion = "1.5.15"
//    }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.7.3")
    implementation("androidx.compose.ui:ui-graphics:1.7.3")
}
repositories {
    google()
}
