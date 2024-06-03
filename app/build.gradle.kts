plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "uz.promo.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "uz.promo.movieapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {


        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "IMAGE_URL", "\"https://image.tmdb.org/t/p/original\"")
            buildConfigField("String", "API_KEY", "\"100bb96de8439f4f233ff84201cad1df\"")

        }

        release {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "IMAGE_URL", "\"https://image.tmdb.org/t/p/original\"")
            buildConfigField("String", "API_KEY", "\"100bb96de8439f4f233ff84201cad1df\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.test.junit4.android)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation("androidx.compose.material:material-icons-extended")
    implementation(libs.coil.compose)

    implementation(libs.androidx.navigation.compose)

    implementation(kotlin("stdlib-jdk8"))
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")
    ksp("androidx.hilt:hilt-compiler:1.2.0")

    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.retrofit2.gson.converter)
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)
    implementation(libs.logging.interceptor)

    implementation(libs.dexter)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)


}