plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
//    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ai.naver.carelink"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "ai.naver.carelink"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.lifecycle:lifecycle-service:2.10.0")

    val navVersion = "2.9.6"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")

    // splash screen
    implementation("androidx.core:core-splashscreen:1.2.0")

    // DI Koin
    implementation("io.insert-koin:koin-android:3.5.3")

    // Retrofit
    val retrofitVersion = "3.0.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-scalars:${retrofitVersion}")

    // Gson
    implementation("com.google.code.gson:gson:2.13.2")

    // Glide
    val glideVersion = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    ksp("com.github.bumptech.glide:ksp:$glideVersion")

    // Room
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion") // Hỗ trợ Coroutines/Flow
    ksp("androidx.room:room-compiler:$roomVersion")

    // Coroutine
    val coroutineVersion = "1.10.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    // Shared Preferences
    val preferenceVersion = "1.2.1"
    implementation("androidx.preference:preference-ktx:$preferenceVersion")
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha05")

    // viewpager
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    // RecyclerView
    val recyclerviewVersion = "1.4.0"
    implementation("androidx.recyclerview:recyclerview:$recyclerviewVersion")
    implementation("androidx.recyclerview:recyclerview-selection:1.2.0")
}

configurations.all {
    exclude(group = "xmlpull", module = "xmlpull")
}