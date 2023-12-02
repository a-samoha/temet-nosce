plugins {
    id("com.android.application")
    id("kotlin-kapt")
    kotlin("android")
}

apply(from = "$rootDir/gradle/build_android.gradle")

android {
    namespace = "com.artsam.temetnosce"

    defaultConfig {
        applicationId = "com.artsam.temetnosce.app"
        versionCode = Integer.parseInt(libs.versions.versionCode.get())
        versionName = libs.versions.versionName.get()
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":presentation"))
    implementation(project(":feature:sadhana"))

    implementation(libs.koin.android)
}