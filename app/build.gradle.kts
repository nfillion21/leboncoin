plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    buildFeatures {
        dataBinding = true
    }

    defaultConfig {
        applicationId = "pgm.poolp.leboncoin"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {

    implementation (Dependencies.coreAndroidX)
    implementation (Dependencies.appCompat)
    implementation (Dependencies.material)
    implementation (Dependencies.constraintLayout)
    implementation (Dependencies.swipeRefresh)

    // Tests
    testImplementation (Dependencies.junit)
    androidTestImplementation (Dependencies.testjunit)
    androidTestImplementation (Dependencies.espressoCore)
    androidTestImplementation (Dependencies.coreTesting)
    androidTestImplementation (Dependencies.truth)

    // Lifecycle
    implementation (Dependencies.liveData)
    implementation (Dependencies.viewModel)

    // Hilt
    implementation (Dependencies.hiltAndroid)
    kapt (Dependencies.hiltAndroidCompiler)

    // Coroutines
    implementation (Dependencies.workRuntime)

    // Room
    implementation (Dependencies.roomRuntine)
    implementation (Dependencies.roomKtx)
    annotationProcessor (Dependencies.roomCompiler)
    kapt(Dependencies.roomCompiler)

    // GSon
    implementation (Dependencies.gson)

    // Navigation
    implementation (Dependencies.navFragment)

    // Glide
    kapt (Dependencies.glideCompiler)
    implementation (Dependencies.glide)

    // ktor
    implementation (Dependencies.ktor)
    implementation (Dependencies.ktorCIO)
}