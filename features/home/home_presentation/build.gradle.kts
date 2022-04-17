apply {
    from("$rootDir/compose-module.gradle")
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUtil))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.homeDomain))

    // Paging
    "implementation"(Paging.paging)
    "implementation"(Paging.pagingCompose)
}