plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/gradle/build_android.gradle")

android {
    namespace = "com.artsam.temetnosce.presentation"

    buildFeatures { compose = true }

    composeOptions { kotlinCompilerExtensionVersion = "1.4.7" }
}

dependencies {

    implementation(project(":feature:sadhana"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycleRuntime)
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation(libs.koin.compose)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
