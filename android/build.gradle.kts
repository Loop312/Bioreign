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
    implementation(platform("androidx.compose:compose-bom:2024.09.03"))

    implementation("androidx.compose.ui:ui:1.7.3")
    implementation("androidx.compose.ui:ui-graphics:1.7.3")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
}
repositories {
    google()
}
