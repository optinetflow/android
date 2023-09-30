import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.apollographql.apollo3").version("3.8.2")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = 34
    namespace = "com.v2ray.ang"

    defaultConfig {
        applicationId = "com.v2ray.ang"
        minSdk = 24
        targetSdk = 34
        versionCode = 495
        multiDexEnabled = true
        versionName = "1.7.33"
    }

    android {
        val props = Properties()
        val localPropertiesFile = File(rootProject.projectDir, "local.properties")
        if (localPropertiesFile.exists()) {
            FileInputStream(localPropertiesFile).use {
                props.load(it)
            }
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                isShrinkResources = false
                isZipAlignEnabled = false
                ndk {
                    abiFilters.addAll(arrayOf("x86", "x86_64", "armeabi-v7a", "arm64-v8a"))
                }
                if (props["sign"]?.equals("true") == true) {
                    signingConfig = signingConfigs.getByName("release")
                }
            }

            getByName("debug") {
                isMinifyEnabled = false
                isShrinkResources = false
                isZipAlignEnabled = false
                ndk {
                    abiFilters.addAll(arrayOf("x86", "x86_64", "armeabi-v7a", "arm64-v8a"))
                }
                if (props["sign"]?.equals("true") == true) {
                    signingConfig = signingConfigs.getByName("release")
                }
            }
        }
    }

    splits {
        abi {
            reset()
            include("x86", "x86_64", "armeabi-v7a", "arm64-v8a")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    project.ext.set(
        "versionCodes", mapOf(
            "armeabi-v7a" to 1,
            "arm64-v8a" to 2,
            "x86" to 3,
            "x86_64" to 4
        )
    )

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/kotlin")
            jniLibs.srcDirs("libs")
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))
    implementation("androidx.annotation:annotation:1.7.0")

    // Androidx
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.viewpager2:viewpager2:1.1.0-beta02")

    val nav_version = "2.7.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.hilt:hilt-navigation-fragment:1.1.0-alpha02")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Compose
    val composeBom = platform("androidx.compose:compose-bom:2023.09.01")
    implementation(composeBom)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.7.2")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Androidx ktx
    val lifecycle_version = "2.6.2"
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

    //kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Retrofit
    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    val apollo_version = "3.8.2"
    implementation("com.apollographql.apollo3:apollo-runtime:$apollo_version")
    implementation("com.apollographql.apollo3:apollo-api:$apollo_version")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    implementation("com.tencent:mmkv-static:1.3.1")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.reactivex:rxjava:1.3.8")
    implementation("io.reactivex:rxandroid:1.2.1")
    implementation("com.tbruyelle.rxpermissions:rxpermissions:0.9.4@aar")
    implementation("me.dm7.barcodescanner:core:1.9.8")
    implementation("me.dm7.barcodescanner:zxing:1.9.8")
    implementation("com.github.jorgecastilloprz:fabprogresscircle:1.01@aar")
    implementation("me.drakeet.support:toastcompat:1.1.0")
    implementation("com.blacksquircle.ui:editorkit:2.8.0")
    implementation("com.blacksquircle.ui:language-base:2.8.0")
    implementation("com.blacksquircle.ui:language-json:2.8.0")
}

apollo {
    service("myService") {
        packageName.set("com.v2ray.ang")
        generateOptionalOperationVariables.set(false)

        introspection {
            endpointUrl.set("https://arvanvpn.online/api/graphql")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
        }
    }
}

kapt {
    correctErrorTypes = true
}