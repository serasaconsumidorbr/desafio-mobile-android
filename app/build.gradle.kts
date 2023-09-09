

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.desafio.marvelapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.desafio.marvelapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // should correspond to key/value pairs inside the file
        buildConfigField ("String", "PUBLIC_KEY", "\"ad5c2787080c209928c43989d6b2455e\"")
        buildConfigField ("String", "PRIVATE_KEY", "\"fcb08f9a1aeeac073e0f6401b78c43db12a92fde\"")
        buildConfigField ("String", "BASE_URL", "\"https://gateway.marvel.com/v1/public/\"")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    viewBinding {
        enable = true
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation (project(":core"))

    // AndroidX
     implementation ("androidx.core:core-ktx:1.9.0")
     implementation ("androidx.appcompat:appcompat:1.5.1")
     implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    // Material design
     implementation ("com.google.android.material:material:1.6.1")
     implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    // Navigation
    val nav_version = "2.5.2"
     implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
     implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")

    // ViewModel and LiveData
    val lifecycle_version = "2.5.1"
     implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
     implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
     implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    val coroutines = "1.6.4"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    // Dagger Hilt
    val hilt_version = 2.44
    implementation ("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    // Room
    val room_version = "2.4.3"
     implementation ("androidx.room:room-ktx:$room_version")
     implementation ("androidx.room:room-runtime:$room_version")
    //kapt "androidx.room:room-compiler:$room_version"

    // Paging3
    val paging_version = "3.1.1"
             implementation ("androidx.paging:paging-runtime-ktx:$paging_version")

    // Glide
    val glide_version = "4.12.0"
             implementation ("com.github.bumptech.glide:glide:$glide_version")
    //kapt ("com.github.bumptech.glide:compiler:$glide_version")

    // Other Libs
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    // Unit tests
    //testImplementation project(":testeutil")
    testImplementation ("androidx.room:room-testing:$room_version")

    // Instrumentation tests
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}

kapt {
    correctErrorTypes = true
}