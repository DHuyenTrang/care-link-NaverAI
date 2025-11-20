// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        flatDir {
            dirs("libs")
        }
    }
    dependencies {
        val navVersion = "2.9.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}

plugins {
    id("com.android.application") version "8.13.1" apply false
    id("org.jetbrains.kotlin.android") version "2.2.21" apply false
//    id("com.google.gms.google-services") version "4.4.4" apply false
    id("com.android.library") version "8.13.1" apply false
    id("com.google.devtools.ksp") version "2.2.20-2.0.4" apply false
}