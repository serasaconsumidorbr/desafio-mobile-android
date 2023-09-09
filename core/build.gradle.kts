plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    // OkHttp
    api(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    api("com.squareup.okhttp3:okhttp")
    api("com.squareup.okhttp3:logging-interceptor")

    // Retrofit
    val retrofit_version = "2.9.0"
    api ("com.squareup.retrofit2:retrofit:$retrofit_version")
    api ("com.squareup.retrofit2:converter-gson:$retrofit_version")

    //Gson
    api ("com.google.code.gson:gson:2.9.0")

    val paging_version = "3.0.1"
    //Paging3 common
    implementation ("androidx.paging:paging-common:$paging_version")

    //javax Inject
    implementation ("javax.inject:javax.inject:1")

    val coroutines_version = "1.6.4"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")

    testImplementation (project(":testeutil"))
}