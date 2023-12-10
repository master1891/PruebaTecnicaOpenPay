plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.nels.master.pruebaopenpay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nels.master.pruebaopenpay"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

dependencies {

    //referencia a versiones de dependencias
    val retrofit_version = "2.9.0";
    val hilt_version = "2.49"
    val room_version = "2.6.1"

    implementation("com.google.firebase:firebase-firestore:24.10.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.navigation:navigation-compose:2.7.5")
    //Hilt
    implementation("com.google.dagger:hilt-android:${hilt_version}")
    kapt("com.google.dagger:hilt-android-compiler:${hilt_version}")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:${retrofit_version}")
    implementation ("com.squareup.retrofit2:converter-gson:${retrofit_version}")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //Google Services y Mapas
    implementation ("com.google.maps.android:maps-compose:2.9.0")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    //Permisos
    implementation ("com.google.accompanist:accompanist-permissions:0.31.3-beta")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")


    implementation ("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")


}