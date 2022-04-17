apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUtil))

    // Paging
    "implementation"(Paging.paging)
    "implementation"(Paging.pagingCompose)
}