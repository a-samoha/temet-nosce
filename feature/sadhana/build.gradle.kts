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
    implementation(libs.kotlin.coroutinesCore)
    implementation(libs.koin.android)

    implementation(libs.gson)
    implementation(libs.retrofit.core)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutinesTest)
    testImplementation(libs.mockk)
}