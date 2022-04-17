apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUtil))
    "implementation"(project(Modules.homeDomain))

    // Retrofit
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)

    // Paging
    "implementation"(Paging.paging)
    "implementation"(Paging.pagingCompose)
}