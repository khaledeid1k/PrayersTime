plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrains.kotlin.serialization)

}

android {
    namespace = "com.example.prayerstime"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.prayerstime"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "Base_URL", "\"https://api.aladhan.com/v1/\"")        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "Base_URL", "\"https://api.aladhan.com/v1/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.play.services.location)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    implementation(libs.datastore)
    implementation(libs.gson)
    ksp(libs.room.compiler)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.accompanist.permissions)
    implementation( libs.androidx.navigation.compose)
    implementation(libs.viewModel)
    implementation(libs.viewModelEx)
    implementation(libs.coroutine.android)
    implementation(libs.coroutine.core)
    implementation(libs.lottie)
    implementation(libs.coil)
    implementation(libs.lottie.compose)
    implementation( libs.kotlinx.serialization.core.jvm)


}