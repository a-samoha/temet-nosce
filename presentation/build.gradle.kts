plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/gradle/build_android.gradle")

android {
    namespace = "com.temetnosce.presentation"

    buildFeatures { compose = true }

    // check version: https://developer.android.com/jetpack/androidx/releases/compose-kotlin
    composeOptions { kotlinCompilerExtensionVersion = "1.5.13" }
}

dependencies {

    implementation(project(":feature:sadhana"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycleRuntime)
    
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(libs.koin.compose)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
