import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"

}

android {
    namespace = "com.hfad.to_dolist"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hfad.to_dolist"
        minSdk = 33
        targetSdk = 35
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
        compose = true
    }
}

dependencies {
    val nav_version = "2.9.0"

    // Интеграция Jetpack Compose
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Views/Fragments представление
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Поддержка функционального модуля для фрагментов
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Тестирование навигации
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // Библиотека сериализации JSON, работает с плагином сериализации Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    implementation ("androidx.room:room-runtime:2.7.0")
    kapt ("androidx.room:room-compiler:2.7.0")
    implementation ("androidx.room:room-ktx:2.7.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.9.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}