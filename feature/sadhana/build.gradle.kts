plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/gradle/build_android.gradle")

android {
    resourcePrefix = "sadhana_"
    namespace = "com.artsam.temetnosce.feature.sadhana"
}

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation(libs.kotlin.coroutinesCore)
    implementation(libs.koin.android)

    implementation(libs.gson)
    implementation(libs.retrofit.core)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutinesTest)
    testImplementation(libs.mockk)
}