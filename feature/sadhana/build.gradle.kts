plugins {
    id("com.android.library")
    id("kotlin-kapt")
    kotlin("android")
}

android {
    resourcePrefix = "sadhana_"
    namespace = "com.artsam.sadhana"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.incremental", "true")
                arg("room.expandProjection", "true")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions { kotlinCompilerExtensionVersion = "1.4.7" }
}

dependencies {

    // compose
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation(libs.androidx.lifecycleCompose)

    implementation(libs.kotlin.coroutinesCore)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.google.gson)
    implementation(libs.google.accompanist)

    // database
    implementation(libs.database.roomRuntime)
    implementation(libs.database.roomKtx)
    kapt(libs.database.roomCompiler)

    // test
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutinesTest)
    testImplementation(libs.mockk)
}