plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "edu.qc.seclass.glm"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.qc.seclass.glm"
        minSdk = 21
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
tasks.register<Wrapper>("wrapper") {
    gradleVersion = "8.4"
}
tasks.register("prepareKotlinBuildScriptModel") {
    doLast {
        println("Task executed")
    }
}
