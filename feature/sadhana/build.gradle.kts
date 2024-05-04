plugins {
    id("com.android.library")
    id("kotlin-kapt")
    kotlin("android")
}

apply(from="$rootDir/gradle/build_android.gradle")

android {
    resourcePrefix = "sadhana_"
    namespace = "com.temetnosce.sadhana"

    defaultConfig {
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.incremental", "true")
                arg("room.expandProjection", "true")
            }
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    // check version: https://developer.android.com/jetpack/androidx/releases/compose-kotlin
    composeOptions { kotlinCompilerExtensionVersion = "1.5.13" }
}

dependencies {

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

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
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.coroutinesTest)
}