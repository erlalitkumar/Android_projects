plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    var isDebug = false
    flavorDimensions("default")
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.lkb.baseandroidproject"
        minSdkVersion(15)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebug = false;
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

            getByName("debug") {
                isDebug = true
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }
        productFlavors {
            create("paid") {
                applicationId = "com.lkb.baseandroidproject.paid"
                if (isDebug) {
                    resValue("string", "test_id", "changed from gradle (paid)-type debug!!")
                } else {
                    resValue("string", "test_id", "changed from gradle (paid)-type prod!!")
                }
            }
            create("free") {
                applicationId = "com.lkb.baseandroidproject.free"
                if (isDebug) {
                    resValue("string", "test_id", "changed from gradle (free)-type debug!!")
                } else {
                    resValue("string", "test_id", "changed from gradle (free)-type prod!!")
                }
            }
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.70")
    implementation("androidx.appcompat:appcompat:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0")
}
